package com.amoremio.ingredients.misc;

import com.amoremio.ingredients.Ingredient;
import com.amoremio.ingredients.IngredientName;

public class Salami extends Ingredient {
  private static final float PRICE = 2.5f;
  private static final IngredientName name = IngredientName.SALAMI;

  public Salami(int expiryDayOffset) {
    super(name, PRICE, expiryDayOffset);
  }
}
