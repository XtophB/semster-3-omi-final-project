package com.amoremio.pizza.factories;

import com.amoremio.pizza.builders.NeapolitanBuilder;
import com.amoremio.pizza.builders.PizzaBuilder;

public class NeapolitanFactory implements BuilderFactoryInterface {
  public PizzaBuilder createBuilder() {
    return new NeapolitanBuilder();
  }
}
