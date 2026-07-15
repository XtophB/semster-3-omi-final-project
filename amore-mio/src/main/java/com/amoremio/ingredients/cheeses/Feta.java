package com.amoremio.ingredients.cheeses;

import com.amoremio.ingredients.Cheese;
import com.amoremio.ingredients.IngredientName;

public class Feta extends Cheese {
  private static final float PRICE = 2.0f;
  private static final IngredientName NAME = IngredientName.FETA;

  public Feta(int expiryDayOffset) {
    super(NAME, PRICE, expiryDayOffset);
  }
}
