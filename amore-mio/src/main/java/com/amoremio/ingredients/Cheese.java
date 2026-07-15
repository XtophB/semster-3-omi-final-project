package com.amoremio.ingredients;

public abstract class Cheese extends Ingredient {
  public Cheese(IngredientName name, float basePrice, int expiryDayOffset) {
    super(name, basePrice, expiryDayOffset);
  }
}
