package com.amoremio.pizza.factories;

import com.amoremio.pizza.builders.PizzaBuilder;
import com.amoremio.pizza.builders.SouthEastEuBuilder;

public class SouthEastEuFactory implements BuilderFactoryInterface {
  public PizzaBuilder createBuilder() {
    return new SouthEastEuBuilder();
  }
}
