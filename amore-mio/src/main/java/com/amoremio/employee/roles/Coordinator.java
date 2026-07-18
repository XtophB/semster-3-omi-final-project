package com.amoremio.employee.roles;

import com.amoremio.employee.Employee;
import com.amoremio.ingredients.Ingredient;
import com.amoremio.ingredients.IngredientName;
import com.amoremio.order.Order;
import com.amoremio.order.OrderProcess;
import com.amoremio.order.OrderState;
import com.amoremio.order.OrderedPizza;
import com.amoremio.store.Storage;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

public class Coordinator extends Employee {
  private final EnumMap<IngredientName, Integer> orderRequirements;

  public Coordinator(int basePay, float payMultiplier) {
    super(basePay, payMultiplier);
    this.orderRequirements = new EnumMap<>(IngredientName.class);
  }

  public void checkOrder(Storage storage, OrderProcess orderProcess) {
    Order order = orderProcess.getOrder();
    calculateRequiredToppings(order);
    if (!checkStorage(storage)) {
      orderProcess.setState(OrderState.DELAYED);
    }
  }

  private void calculateRequiredToppings(Order order) {
    orderRequirements.clear();
    List<OrderedPizza> orderedPizzas = order.getPizzaOrder();
    for (OrderedPizza orderedPizza : orderedPizzas) {
      List<IngredientName> toppings = orderedPizza.getToppings();
      for (IngredientName topping : toppings) {
        int currentAmount = orderRequirements.getOrDefault(topping, 0);
        orderRequirements.put(topping, currentAmount + 1);
      }
    }
  }

  private boolean checkStorage(Storage storage) {
    EnumMap<IngredientName, ArrayList<Ingredient>> inventory = storage.getInventory();
    for (IngredientName name : orderRequirements.keySet()) {
      System.out.println("Ingredient: " + name + ", Required: " + orderRequirements.get(name));
      System.out.println("Ingredient: " + name + ", In Storage: " + inventory.get(name).size());
      if (!storage.checkQuantitySufficient(name)) {
        System.out.println("Not enough ingredients");
        System.out.println("Calling supplier for " + name);
        storage.callSupplier(name);
        return false;
      }
    }
    return true;
  }
}
