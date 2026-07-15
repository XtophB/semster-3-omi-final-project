package com.amoremio.ingredients.misc;

import com.amoremio.ingredients.Ingredient;
import com.amoremio.ingredients.IngredientName;

public class Corn extends Ingredient {
  private static final float PRICE = 1.0f;
  private static final IngredientName name = IngredientName.CORN;

  public Corn(int expiryDayOffset) {
    super(name, PRICE, expiryDayOffset);
  }
}
