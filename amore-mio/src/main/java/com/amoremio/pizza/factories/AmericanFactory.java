package com.amoremio.pizza.factories;

import com.amoremio.pizza.builders.AmericanBuilder;
import com.amoremio.pizza.builders.PizzaBuilder;

public class AmericanFactory implements BuilderFactoryInterface {
  @Override
  public PizzaBuilder createBuilder() {
    return new AmericanBuilder();
  }
}
