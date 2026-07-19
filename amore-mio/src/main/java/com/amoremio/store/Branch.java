package com.amoremio.store;

import com.amoremio.employee.Employee;
import com.amoremio.employee.roles.Cook;
import com.amoremio.employee.roles.Coordinator;
import com.amoremio.employee.roles.DeliveryBoy;
import com.amoremio.employee.roles.KitchenAid;
import com.amoremio.order.Order;
import com.amoremio.order.OrderProcess;
import com.amoremio.order.OrderState;
import com.amoremio.pizza.Pizza;
import com.amoremio.pizza.PizzaState;
import com.amoremio.pizza.builders.PizzaBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/** Represents a physical store in a specific city. */
public class Branch {
  private final List<OrderProcess> orderProcesses = new ArrayList<>();
  private final List<Cook> freeCooks = new ArrayList<>();
  private final List<Cook> busyCooks = new ArrayList<>();
  private final List<DeliveryBoy> freeDeliveryBoys = new ArrayList<>();
  private final List<KitchenAid> freeKitchenAids = new ArrayList<>();
  private final List<Coordinator> freeCoordinators = new ArrayList<>();
  Storage storage;
  private PizzaBuilder pizzaBuilder;
  City city;

  /**
   * Constructor for a branch receiving dependency injections.
   *
   * @param storage the storage associated with a branch
   * @param builder the builder that defines what pizzas a branch can make
   * @param city the city where the branch is located in
   */
  public Branch(Storage storage, PizzaBuilder builder, City city) {
    this.storage = storage;
    this.pizzaBuilder = builder;
    this.city = city;
  }

  /**
   * Sets the pizza builder for this branch. Can only be changed when no orders are actively being
   * processed, preventing a mid-order style switch.
   *
   * @param pizzaBuilder the new pizza builder to use
   * @throws IllegalStateException if orders are currently being processed
   * @throws IllegalArgumentException if pizzaBuilder is null
   */
  public void setPizzaBuilder(PizzaBuilder pizzaBuilder) {
    if (!orderProcesses.isEmpty()) {
      throw new IllegalStateException(
          "Cannot change pizza builder while orders are being processed.");
    }
    this.pizzaBuilder = pizzaBuilder;
  }

  /**
   * A method exposing the ability to add employees to a branch.
   *
   * @param employee the employee to add
   */
  public void addEmployee(Employee employee) {
    switch (employee) {
      case Cook cook -> freeCooks.add(cook);
      case DeliveryBoy deliveryBoy -> freeDeliveryBoys.add(deliveryBoy);
      case KitchenAid kitchenAid -> freeKitchenAids.add(kitchenAid);
      case Coordinator coordinator -> freeCoordinators.add(coordinator);
      default -> {}
    }
  }

  /**
   * A method exposing the ability to remove employees from a branch.
   *
   * @param employee the employee to remove
   */
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

  /**
   * The main method used to process an order. This method acts as a conductor. Here all the steps
   * of the order process are executed in sequence, from preparation to baking to delivery.
   *
   * @param orderProcess the order process to handle
   */
  public void processOrder(OrderProcess orderProcess) {
    orderProcesses.add(orderProcess);
    Order order = orderProcess.getOrder();

    KitchenAid aid = findFreeKitchenAid();
    List<Pizza> preparedPizzas = prepareOrder(order, orderProcess, aid);
    List<Pizza> bakedPizzas = bakePizzas(preparedPizzas, aid, orderProcess);
    completeOrder(orderProcess, bakedPizzas);
    orderProcesses.remove(orderProcess);
  }

  private List<Pizza> prepareOrder(Order order, OrderProcess orderProcess, KitchenAid aid) {
    Coordinator coordinator = findFreeCoordinator();
    coordinator.checkOrder(storage, orderProcess);
    List<Pizza> preparedPizzas = aid.preparePizza(storage, pizzaBuilder, order);
    System.out.println("Prepared " + preparedPizzas.size() + " raw pizzas for this order.");
    orderProcess.setState(OrderState.PROCESSING);
    return preparedPizzas;
  }

  private List<Pizza> bakePizzas(
      List<Pizza> preparedPizzas, KitchenAid aid, OrderProcess orderProcess) {
    ListIterator<Pizza> iterator = preparedPizzas.listIterator();
    List<Cook> assignedCooks = new ArrayList<>();
    List<Pizza> bakedPizzas = new ArrayList<>();

    while (iterator.hasNext()) {
      Pizza pizza = iterator.next();

      Cook cook = findFreeCook();
      assignedCooks.add(cook);
      Pizza bakedPizza = cook.bakePizza(pizza);

      if (bakedPizza.getPizzaState() == PizzaState.BURNT) {
        Pizza burntPizza = aid.redoPizza(bakedPizza, storage, pizzaBuilder);
        iterator.add(burntPizza);
        iterator.previous();
        // bug where burnt pizzas did not get readded to cooking queue as iterator
        // never reached. iterator.add adds the pizzas behind the iterator.
        System.out.println("  Pizza burnt, re-adding for re-baking.");
        orderProcess.setState(OrderState.DELAYED);
      } else {
        bakedPizzas.add(bakedPizza);
        System.out.println(
            "  Baked "
                + bakedPizza.getPizzaType()
                + " pizza - EUR "
                + String.format("%.2f", bakedPizza.getPrice()));
      }
    }

    for (Cook cook : assignedCooks) {
      markCookFree(cook);
    }
    return bakedPizzas;
  }

  private void completeOrder(OrderProcess orderProcess, List<Pizza> bakedPizzas) {
    float orderTotal = 0;
    for (Pizza p : bakedPizzas) {
      orderTotal += p.getPrice();
    }
    System.out.println("All pizzas baked. Order total: EUR " + String.format("%.2f", orderTotal));
    orderProcess.setState(OrderState.DELIVERING);
    System.out.println("Order is ready for delivery.");
    deliverOrder(orderProcess, bakedPizzas);
    System.out.println("Order delivered successfully.");
  }

  private void deliverOrder(OrderProcess orderProcess, List<Pizza> pizzas) {
    DeliveryBoy deliveryBoy = findFreeDeliveryBoy();
    deliveryBoy.deliverOrder(orderProcess, pizzas);
  }
}
