package com.amoremio.pizza.builders;

import com.amoremio.ingredients.Dough;
import com.amoremio.ingredients.Ingredient;
import com.amoremio.ingredients.Sauce;
import com.amoremio.pizza.Pizza;
import java.util.List;

/** The interface that defines what methods a concrete builder must have. */
public interface PizzaBuilder {

  /**
   * A method to initialize a new pizza that is to be built.
   */
  void createPizza();

  /**
   * A method to add the dough.
   *
   * @param dough the dough containing the specific type
   */
  void buildDough(Dough dough);

  /**
   * A method to add the sauce.
   *
   * @param sauce the sauce
   */
  void buildSauce(Sauce sauce);

  /**
   * A method to add the specific toppings requested by the order.
   *
   * @param toppings list of toppings
   */
  void buildToppings(List<Ingredient> toppings);

  /**
   * A method to get the final built pizza.
   *
   * @return the built pizza
   */
  Pizza getPizza();
}
