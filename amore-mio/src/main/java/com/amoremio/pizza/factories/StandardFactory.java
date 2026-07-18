package com.amoremio.pizza.factories;

import com.amoremio.pizza.builders.PizzaBuilder;
import com.amoremio.pizza.builders.StandardBuilder;

/**
 * The concrete factory that creates the Standard builder.
 */
public class StandardFactory implements BuilderFactoryInterface {
  @Override
  public PizzaBuilder createBuilder() {
    return new StandardBuilder();
  }
}
