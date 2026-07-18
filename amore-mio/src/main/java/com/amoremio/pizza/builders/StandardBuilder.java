package com.amoremio.pizza.builders;

import com.amoremio.ingredients.Dough;
import com.amoremio.ingredients.DoughStyle;
import com.amoremio.pizza.PizzaType;

/**
 * A concrete builder that builds standard pizza.
 */
public class StandardBuilder extends AbstractPizzaBuilder {

  /**
   * The constructor automatically sets the type to Standard.
   */
  public StandardBuilder() {
    super(PizzaType.STANDARD);
  }

  @Override
  public void buildDough(Dough dough) {
    pizza.addIngredient(dough);
    pizza.setDoughStyle(DoughStyle.STANDARD);
  }
}
