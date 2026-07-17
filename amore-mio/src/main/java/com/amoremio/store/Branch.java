package com.amoremio.store;

import com.amoremio.employee.Employee;
import com.amoremio.employee.roles.Cook;
import com.amoremio.employee.roles.KitchenAid;
import com.amoremio.pizza.Pizza;
import com.amoremio.pizza.builders.AbstractPizzaBuilder;
import com.amoremio.order.Order;
import java.util.ArrayList;
import java.util.List;
import lombok.Setter;

public class Branch {
  List<Employee> employees;
  Storage storage;
  @Setter
  AbstractPizzaBuilder pizzaBuilder;
  City city;

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

  public void processOrder(Order order) {
    KitchenAid aid = findFreeKitchenAid();
    Cook cook = findFreeCook();

    List<Pizza> rawPizzas = aid.preparePizza(storage, pizzaBuilder, order);
    this.rawPizzas.addAll(rawPizzas);

    Pizza bakedPizza = cook.bakePizza(rawPizzas.getFirst());
    this.bakedPizzas.add(bakedPizza);

  }


}
