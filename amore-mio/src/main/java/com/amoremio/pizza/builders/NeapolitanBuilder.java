package com.amoremio.pizza.builders;

import com.amoremio.ingredients.Dough;
import com.amoremio.ingredients.DoughStyle;
import com.amoremio.pizza.PizzaType;

/** A concrete builder that builds Neapolitan pizza. */
public class NeapolitanBuilder extends AbstractPizzaBuilder {

  /** The constructor automatically sets the type to Neapolitan. */
  public NeapolitanBuilder() {
    super(PizzaType.NEAPOLITAN);
  }

  @Override
  public void buildDough(Dough dough) {
    pizza.addIngredient(dough);
    pizza.setDoughStyle(DoughStyle.THIN);
  }
}
