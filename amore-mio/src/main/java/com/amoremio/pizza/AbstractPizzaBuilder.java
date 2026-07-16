package com.amoremio.pizza;

import lombok.Getter;

public abstract class AbstractPizzaBuilder implements PizzaBuilder {
  @Getter
  protected Pizza pizza;
  private final PizzaType pizzaType;

  public AbstractPizzaBuilder(PizzaType pizzaType) {
    this.pizzaType = pizzaType;
  }

  public void createPizza(){
    pizza = new Pizza(pizzaType);
  }

}