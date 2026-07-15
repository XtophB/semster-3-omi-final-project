package com.amoremio.ingredients.misc;

import com.amoremio.ingredients.Ingredient;
import com.amoremio.ingredients.IngredientName;

public class Onion extends Ingredient {
  private static final float PRICE = 0.6f;
  private static final IngredientName name = IngredientName.ONION;

  public Onion(int expiryDayOffset) {
    super(name, PRICE, expiryDayOffset);
  }
}
