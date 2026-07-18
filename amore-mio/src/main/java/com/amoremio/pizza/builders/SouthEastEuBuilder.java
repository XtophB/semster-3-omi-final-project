package com.amoremio.pizza.builders;

import com.amoremio.ingredients.Dough;
import com.amoremio.ingredients.DoughStyle;
import com.amoremio.pizza.PizzaType;

/**
 * A concrete builder that builds Southeast European style pizza.
 */
public class SouthEastEuBuilder extends AbstractPizzaBuilder {

  /**
   * The constructor automatically sets the type to Southeast European.
   */
  public SouthEastEuBuilder() {
    super(PizzaType.SOUTH_EAST_EU);
  }

  @Override
  public void buildDough(Dough dough) {
    pizza.addIngredient(dough);
    pizza.setDoughStyle(DoughStyle.SE_EU_STYLE);
  }
}
