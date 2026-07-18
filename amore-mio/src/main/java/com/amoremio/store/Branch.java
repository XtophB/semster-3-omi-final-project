package com.amoremio.store;

import com.amoremio.employee.Employee;
import com.amoremio.employee.roles.Cook;
import com.amoremio.employee.roles.Coordinator;
import com.amoremio.employee.roles.DeliveryBoy;
import com.amoremio.employee.roles.KitchenAid;
import com.amoremio.order.OrderProcess;
import com.amoremio.order.OrderState;
import com.amoremio.pizza.Pizza;
import com.amoremio.pizza.PizzaState;
import com.amoremio.pizza.builders.AbstractPizzaBuilder;
import com.amoremio.order.Order;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Queue;
import lombok.Setter;

public class Branch {
  Storage storage;
  @Setter AbstractPizzaBuilder pizzaBuilder;
  City city;

  private final List<OrderProcess> orderProcesses = new ArrayList<>();
  private final List<Pizza> bakedPizzas = new ArrayList<>();

  private final List<Cook> freeCooks = new ArrayList<>();
  private final List<Cook> busyCooks = new ArrayList<>();
  private final List<DeliveryBoy> freeDeliveryBoys = new ArrayList<>();
  private final List<KitchenAid> freeKitchenAids = new ArrayList<>();
  private final List<Coordinator> freeCoordinators = new ArrayList<>();

  public Branch(Storage storage, AbstractPizzaBuilder builder, City city) {
    this.storage = storage;
    this.pizzaBuilder = builder;
    this.city = city;
  }

  public void addEmployee(Employee employee) {
    switch (employee) {
      case Cook cook -> freeCooks.add(cook);
      case DeliveryBoy deliveryBoy -> freeDeliveryBoys.add(deliveryBoy);
      case KitchenAid kitchenAid -> freeKitchenAids.add(kitchenAid);
      case Coordinator coordinator -> freeCoordinators.add(coordinator);
      default -> {}
    }
  }

  public void removeEmployee(Employee employee) {
    switch (employee) {
      case Cook cook -> freeCooks.remove(cook);
      case DeliveryBoy deliveryBoy -> freeDeliveryBoys.remove(deliveryBoy);
      case KitchenAid kitchenAid -> freeKitchenAids.remove(kitchenAid);
      case Coordinator coordinator -> freeCoordinators.remove(coordinator);
      default -> {}
    }
  }

  private Coordinator findFreeCoordinator() {
    for (Coordinator coordinator : freeCoordinators) {
      return coordinator;
    }
    throw new IllegalStateException("No Coordinator available.");
  }

  private KitchenAid findFreeKitchenAid() {
    for (KitchenAid kitchenAid : freeKitchenAids) {
      return kitchenAid;
    }
    throw new IllegalStateException("No KitchenAid available.");
  }

  private Cook findFreeCook() {
    // if we have a free cook use them
    if (!freeCooks.isEmpty()) {
      Cook cook = freeCooks.removeFirst();
      cook.setBusy(true);
      busyCooks.add(cook);
      return cook;
    }
    // if freeCooks is empty, and we have a busy cook, overload them
    if (!busyCooks.isEmpty()) {
      System.out.println("All cooks are busy! Assigning an overworked cook.");
      return busyCooks.getFirst();
    }
    // both lists are empty cuz we never hired
    throw new IllegalStateException("No cooks employed at this branch.");
  }

  private void markCookFree(Cook cook) {
    if (busyCooks.remove(cook)) {
      cook.setBusy(false);
      freeCooks.add(cook);
    }
  }

  private DeliveryBoy findFreeDeliveryBoy() {
    for (DeliveryBoy deliveryBoy : freeDeliveryBoys) {
      return deliveryBoy;
    }
    throw new IllegalStateException("No DeliveryBoy available.");
  }

  public void processOrder(OrderProcess orderProcess) {
    orderProcesses.add(orderProcess);
    Order order = orderProcess.getOrder();
    Coordinator coordinator = findFreeCoordinator();
    KitchenAid aid = findFreeKitchenAid();

    coordinator.checkOrder(storage, orderProcess);
    List<Pizza> preparedPizzas = aid.preparePizza(storage, pizzaBuilder, order);
    // use a local list so pizzas from previous orders are not re-processed
    List<Pizza> pizzasToBake = new ArrayList<>(preparedPizzas);
    // use iterator for modification while iterating to avoid ConcurrentModificationException
    ListIterator<Pizza> iterator = pizzasToBake.listIterator();
    System.out.println("Prepared " + preparedPizzas.size() + " raw pizzas for this order.");
    orderProcess.setState(OrderState.PROCESSING);

    List<Cook> assignedCooks = new ArrayList<>();
    float orderTotal = 0;

    while (iterator.hasNext()) {
      Pizza pizza = iterator.next();

      Cook cook = findFreeCook();
      assignedCooks.add(cook);
      Pizza bakedPizza = cook.bakePizza(pizza);

      if (bakedPizza.getPizzaState() == PizzaState.BURNT) {
        Pizza burntPizza = aid.redoPizza(bakedPizza, storage, pizzaBuilder);
        iterator.add(burntPizza);
        System.out.println("  Pizza burnt, re-adding for re-baking.");
        orderProcess.setState(OrderState.DELAYED);
      } else {
        this.bakedPizzas.add(bakedPizza);
        orderTotal += bakedPizza.getPrice();
        System.out.println("  Baked " + bakedPizza.getPizzaType()
            + " pizza - EUR " + String.format("%.2f", bakedPizza.getPrice()));
      }
    }

    for (Cook cook : assignedCooks) {
      markCookFree(cook);
    }

    System.out.println("All pizzas baked. Order total: EUR " + String.format("%.2f", orderTotal));
    orderProcess.setState(OrderState.DELIVERING);
    System.out.println("Order is ready for delivery.");
    deliverOrder(orderProcess);
    System.out.println("Order delivered successfully.");
  }

  private void deliverOrder(OrderProcess orderProcess) {
    DeliveryBoy deliveryBoy = findFreeDeliveryBoy();
    deliveryBoy.deliverOrder(orderProcess);
  }
}
