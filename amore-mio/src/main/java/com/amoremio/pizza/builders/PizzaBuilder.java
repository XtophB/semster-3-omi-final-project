package com.amoremio.pizza.builders;

import com.amoremio.ingredients.Dough;
import com.amoremio.ingredients.Ingredient;
import com.amoremio.ingredients.Sauce;
import java.util.List;

public interface PizzaBuilder {
  void buildDough(Dough dough);
  void buildSauce(Sauce sauce);
  void buildToppings(List<Ingredient> toppings);
}
