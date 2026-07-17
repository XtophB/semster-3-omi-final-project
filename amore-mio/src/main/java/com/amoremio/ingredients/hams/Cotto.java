package com.amoremio.ingredients.hams;

import com.amoremio.ingredients.Ingredient;
import com.amoremio.ingredients.IngredientName;

public class Cotto extends Ham {
  private static final float PRICE = 2.0f;
  private static final IngredientName NAME = IngredientName.COTTO;

  public Cotto(int expiryDayOffset) {
    super(NAME, PRICE, expiryDayOffset);
  }
}
