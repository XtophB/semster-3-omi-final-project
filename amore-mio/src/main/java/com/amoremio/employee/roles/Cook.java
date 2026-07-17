package com.amoremio.employee.roles;

import com.amoremio.employee.Employee;
import com.amoremio.pizza.Pizza;
import com.amoremio.pizza.PizzaState;
import java.util.ArrayList;
import java.util.List;

public class Cook extends Employee {
  List<Pizza> pizzasCurrentlyWorkedOn;

  public Cook(int basePay, float payMultiplier) {
    super(basePay, payMultiplier);
    pizzasCurrentlyWorkedOn = new ArrayList<>();
  }

  public Pizza bakePizza(Pizza rawPizza) {
    boolean burnt = Math.random() < 0.1;
    if (burnt) {
      rawPizza.setPizzaState(PizzaState.BURNT);
    } else {
      rawPizza.setPizzaState(PizzaState.BAKED);
    }
    return rawPizza;
  }
}
