package com.amoremio.pizza;

import com.amoremio.ingredients.DoughStyle;
import com.amoremio.ingredients.Ingredient;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/** Represents the pizza object. */
@Getter
public class Pizza {
  @Setter private float price;
  private PizzaType pizzaType;
  private List<Ingredient> ingredients;
  @Setter private DoughStyle doughStyle;
  @Setter private PizzaState pizzaState;

  /**
   * The constructor for the pizza object.
   *
   * @param pizzaType the type of pizza created, based on the specific builder
   */
  public Pizza(PizzaType pizzaType) {
    this.pizzaType = pizzaType;
    this.ingredients = new ArrayList<>();
    this.price = 0;
  }

  /**
   * A method to add an ingredient to the pizza, updating the price accordingly.
   *
   * @param ingredient the ingredient added
   */
  public void addIngredient(Ingredient ingredient) {
    ingredients.add(ingredient);
    price += ingredient.getPrice();
  }
}
