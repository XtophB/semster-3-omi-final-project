package com.amoremio.ingredients;

public abstract class Meat extends Ingredient {
  public Meat(IngredientName name, float basePrice, int expiryDayOffset) {
    super(name, basePrice, expiryDayOffset);
  }
}
