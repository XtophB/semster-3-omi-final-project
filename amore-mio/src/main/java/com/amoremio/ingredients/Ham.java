package com.amoremio.ingredients;

public abstract class Ham extends Ingredient {
  public Ham(IngredientName name, float basePrice, int expiryDayOffset) {
    super(name, basePrice, expiryDayOffset);
  }
}
