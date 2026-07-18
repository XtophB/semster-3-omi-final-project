package com.amoremio.pizza.factories;

import com.amoremio.pizza.builders.NeapolitanBuilder;
import com.amoremio.pizza.builders.PizzaBuilder;

/**
 * The concrete factory that creates the Neapolitan builder.
 */
public class NeapolitanFactory implements BuilderFactoryInterface {
  @Override
  public PizzaBuilder createBuilder() {
    return new NeapolitanBuilder();
  }
}
