package com.amoremio.pizza.factories;

import com.amoremio.pizza.builders.AmericanBuilder;
import com.amoremio.pizza.builders.PizzaBuilder;

/**
 * The concrete factory that creates the American builder.
 */
public class AmericanFactory implements BuilderFactoryInterface {
  @Override
  public PizzaBuilder createBuilder() {
    return new AmericanBuilder();
  }
}
