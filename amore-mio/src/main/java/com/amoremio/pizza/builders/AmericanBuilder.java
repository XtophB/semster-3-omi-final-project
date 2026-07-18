package com.amoremio.pizza.builders;

import com.amoremio.ingredients.Dough;
import com.amoremio.ingredients.DoughStyle;
import com.amoremio.pizza.PizzaType;

/**
 * A concrete builder that builds American pizza.
 */
public class AmericanBuilder extends AbstractPizzaBuilder {

  /**
   * The constructor automatically sets the type to American.
   */
  public AmericanBuilder() {
    super(PizzaType.AMERICAN);
  }

  @Override
  public void buildDough(Dough dough) {
    pizza.addIngredient(dough);
    pizza.setDoughStyle(DoughStyle.THICK);
  }
}
