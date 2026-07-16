package com.amoremio.pizza;

import com.amoremio.ingredients.Dough;
import com.amoremio.ingredients.Ingredient;
import com.amoremio.ingredients.Sauce;
import java.util.List;

public class AmericanBuilder extends AbstractPizzaBuilder {

  public AmericanBuilder() {
    super(PizzaType.AMERICAN);
  }

  @Override
  public void buildDough(Dough dough) {
    pizza.addIngredient(dough);
  }

  @Override
  public void buildSauce(Sauce sauce) {
    pizza.addIngredient(sauce);
  }

  @Override
  public void buildToppings(List<Ingredient> toppings) {
    for (Ingredient topping : toppings) {
      pizza.addIngredient(topping);
    }
  }
}
