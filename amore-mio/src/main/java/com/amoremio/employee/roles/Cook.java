package com.amoremio.employee.roles;

import com.amoremio.employee.Employee;
import com.amoremio.pizza.Pizza;
import com.amoremio.pizza.PizzaState;

/**
 * Represents a cook employee. Main job is to cook pizza
 */
public class Cook extends Employee {
  private static final float CHANCE_OF_BURNT_PIZZA = 0.1f; // 10% chance of burning a pizza

  /**
   * Constructor for the cook.
   *
   * @param basePay the starting daily pay
   * @param payMultiplier the multiplier that can be set based on importance of employee
   */
  public Cook(int basePay, float payMultiplier) {
    super(basePay, payMultiplier);
  }

  /**
   * Bakes a pizza with a chance of failure.
   *
   * @param rawPizza the raw pizza received from the {@link KitchenAid}
   * @return the baked pizza
   */
  public Pizza bakePizza(Pizza rawPizza) {
    boolean burnt = Math.random() < CHANCE_OF_BURNT_PIZZA;
    if (burnt) {
      rawPizza.setPizzaState(PizzaState.BURNT);
    } else {
      rawPizza.setPizzaState(PizzaState.BAKED);
    }
    return rawPizza;
  }
}
