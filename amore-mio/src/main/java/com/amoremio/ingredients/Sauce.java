package com.amoremio.ingredients;

public class Sauce extends Ingredient {
  private static final float PRICE = 0.5f;
  private static final IngredientName name = IngredientName.SAUCE;

  public Sauce (int expiryDayOffset) {
    super(name, PRICE, expiryDayOffset);
  }
}
