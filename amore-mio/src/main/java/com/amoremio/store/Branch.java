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
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import lombok.Setter;

public class Branch {
  List<Employee> employees;
  Storage storage;
  @Setter
  AbstractPizzaBuilder pizzaBuilder;
  City city;

  private final List<OrderProcess> orderProcesses = new ArrayList<>();
  private final List<Pizza> rawPizzas = new ArrayList<Pizza>();
  private final List<Pizza> bakedPizzas = new ArrayList<Pizza>();

  Branch(Storage storage, AbstractPizzaBuilder builder, City city) {
    this.storage = storage;
    this.pizzaBuilder = builder;
    this.city = city;
    this.employees = new ArrayList<>();
  }

  public void addEmployee(Employee employee) {
    employees.add(employee);
  }

  public void removeEmployee(Employee employee) {
    employees.remove(employee);
  }

  public void addRawPizza(Pizza pizza) {
    rawPizzas.add(pizza);
  }

  public void addBakedPizza(Pizza pizza) {
    bakedPizzas.add(pizza);
  }

  public Pizza takeRawPizza() {
    if (rawPizzas.isEmpty()) {
      throw new IllegalStateException("No raw pizzas available.");
    }
    return rawPizzas.removeFirst();
  }

  public Pizza takeBakedPizza() {
    if (bakedPizzas.isEmpty()) {
      throw new IllegalStateException("No baked pizzas available.");
    }
    return bakedPizzas.removeFirst();
  }

  private Coordinator findFreeCoordinator(){
    for (Employee employee : employees) {
      if (employee instanceof Coordinator) {
        return (Coordinator) employee;
      }
    }
    throw new IllegalStateException("No Coordinator available.");
  }

  private KitchenAid findFreeKitchenAid(){
    for (Employee employee : employees) {
      if (employee instanceof KitchenAid) {
        return (KitchenAid) employee;
      }
    }
    throw new IllegalStateException("No KitchenAid available.");
  }

  private Cook findFreeCook(){
    for (Employee employee : employees) {
      if (employee instanceof Cook) {
        return (Cook) employee;
      }
    }
    throw new IllegalStateException("No Cook available.");
  }

  private DeliveryBoy findFreeDeliveryBoy(){
    for (Employee employee : employees) {
      if (employee instanceof DeliveryBoy) {
        return (DeliveryBoy) employee;
      }
    }
    throw new IllegalStateException("No DeliveryBoy available.");
  }

  public void processOrder(OrderProcess orderProcess) {
    orderProcesses.add(orderProcess);
    Order order = orderProcess.getOrder();
    Coordinator coordinator = findFreeCoordinator();
    KitchenAid aid = findFreeKitchenAid();
    Cook cook = findFreeCook();

    coordinator.checkOrder(storage, orderProcess);
    List<Pizza> preparedPizzas = aid.preparePizza(storage, pizzaBuilder, order);
    this.rawPizzas.addAll(preparedPizzas);
    // use iterator for modification while iterating to avoid ConcurrentModificationException
    ListIterator<Pizza> iterator = rawPizzas.listIterator();
    System.out.println("Prepared all raw pizzas for an order.");
    orderProcess.setState(OrderState.PROCESSING);

    while (iterator.hasNext()) {
      Pizza pizza = iterator.next();
      Pizza bakedPizza = cook.bakePizza(pizza);
      if (bakedPizza.getPizzaState() ==  PizzaState.BURNT) {
        iterator.add(bakedPizza);
      if (bakedPizza.getPizzaState() == PizzaState.BURNT) {
        Pizza burntPizza = aid.redoPizza(bakedPizza, storage, pizzaBuilder);
        iterator.add(burntPizza);
        System.out.println("Pizza burnt, re-adding to raw pizzas for re-baking.");
        orderProcess.setState(OrderState.DELAYED);
      } else {
        this.bakedPizzas.add(bakedPizza);
      }
    }
    System.out.println("All pizzas baked for an order.");
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
