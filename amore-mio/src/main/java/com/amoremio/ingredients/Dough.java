package com.amoremio.ingredients;

public class Dough extends Ingredient {
  private static final float PRICE = 0.5f;
  private static final IngredientName name = IngredientName.DOUGH;

  public Dough(int expiryDayOffset) {
    super(name, PRICE, expiryDayOffset);
  }
}
