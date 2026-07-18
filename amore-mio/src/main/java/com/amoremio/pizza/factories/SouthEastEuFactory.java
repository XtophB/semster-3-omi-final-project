package com.amoremio.pizza.factories;

import com.amoremio.pizza.builders.PizzaBuilder;
import com.amoremio.pizza.builders.SouthEastEuBuilder;

/**
 * The concrete factory that creates the South-East European builder.
 */
public class SouthEastEuFactory implements BuilderFactoryInterface {
  @Override
  public PizzaBuilder createBuilder() {
    return new SouthEastEuBuilder();
  }
}
