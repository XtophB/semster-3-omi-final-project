package com.amoremio.pizza.builders;

import com.amoremio.ingredients.Dough;
import com.amoremio.ingredients.DoughStyle;
import com.amoremio.pizza.PizzaType;

public class SouthEastEuBuilder extends AbstractPizzaBuilder {

  public SouthEastEuBuilder() {
    super(PizzaType.SOUTH_EAST_EU);
  }

  @Override
  public void buildDough(Dough dough) {
    pizza.addIngredient(dough);
    pizza.setDoughStyle(DoughStyle.SE_EU_STYLE);
  }
}
