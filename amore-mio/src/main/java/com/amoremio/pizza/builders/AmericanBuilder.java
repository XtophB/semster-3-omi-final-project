package com.amoremio.pizza.builders;

import com.amoremio.ingredients.Dough;
import com.amoremio.ingredients.DoughStyle;
import com.amoremio.pizza.PizzaType;

public class AmericanBuilder extends AbstractPizzaBuilder {

  public AmericanBuilder() {
    super(PizzaType.AMERICAN);
  }

  @Override
  public void buildDough(Dough dough) {
    pizza.addIngredient(dough);
    pizza.setDoughStyle(DoughStyle.THICK);
  }
}
