package com.amoremio.pizza;

import lombok.Getter;

public abstract class AbstractPizzaBuilder {
  @Getter
  private Pizza pizza;
  private final PizzaType pizzaType;

  public AbstractPizzaBuilder(PizzaType pizzaType) {
    this.pizzaType = pizzaType;
  }

  public void createPizza(){
    pizza = new Pizza(pizzaType);
  }

}