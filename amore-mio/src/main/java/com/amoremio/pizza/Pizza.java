package com.amoremio.pizza;

import com.amoremio.ingredients.Ingredient;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
public class Pizza {
  private float price;
  private PizzaType pizzaType;
  private List<Ingredient> ingredients;

  public Pizza (PizzaType pizzaType) {
    this.pizzaType = pizzaType;
    this.ingredients = new ArrayList<>();
    this.price = 0;
  }

  public void addIngredient(Ingredient ingredient) {
    ingredients.add(ingredient);
    price += ingredient.getPrice();
  }
  
}
