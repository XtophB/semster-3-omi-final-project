package com.amoremio.pizza.builders;

import com.amoremio.ingredients.Dough;
import com.amoremio.ingredients.Ingredient;
import com.amoremio.ingredients.Sauce;
import com.amoremio.pizza.Pizza;
import com.amoremio.pizza.PizzaState;
import com.amoremio.pizza.PizzaType;
import java.util.List;
import lombok.Getter;

/** The abstract class from which all concrete builders will inherit from. */
public abstract class AbstractPizzaBuilder implements PizzaBuilder {
  private final PizzaType pizzaType;
  @Getter protected Pizza pizza;

  /**
   * Constructor that defines the pizza type based on the builder.
   *
   * @param pizzaType the type of pizza created
   */
  public AbstractPizzaBuilder(PizzaType pizzaType) {
    this.pizzaType = pizzaType;
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

  /** The creation method that automatically sets the state to raw. */
  @Override
  public void createPizza() {
    pizza = new Pizza(pizzaType);
    pizza.setPizzaState(PizzaState.RAW);
  }
}
