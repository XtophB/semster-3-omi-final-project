package com.amoremio.pizza.factories;

import com.amoremio.pizza.builders.PizzaBuilder;

/** Interface to specify what methods a concrete factory must have. */
public interface BuilderFactoryInterface {
  /**
   * Creates a new pizza builder.
   *
   * @return a concrete PizzaBuilder instance
   */
  PizzaBuilder createBuilder();
}
