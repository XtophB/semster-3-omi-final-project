package com.amoremio.ingredients.misc;

import com.amoremio.ingredients.Ingredient;
import com.amoremio.ingredients.IngredientName;

public class Mushroom extends Ingredient {
  private static final float PRICE = 1.2f;
  private static final IngredientName name = IngredientName.MUSHROOM;

  public Mushroom(int expiryDayOffset) {
    super(name, PRICE, expiryDayOffset);
  }
}
