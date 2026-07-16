package com.amoremio.pizza.factories;

import com.amoremio.pizza.builders.PizzaBuilder;
import com.amoremio.pizza.builders.StandardBuilder;

public class StandardFactory implements BuilderFactoryInterface {
  public PizzaBuilder createBuilder() {
    return new StandardBuilder();
  }
}
