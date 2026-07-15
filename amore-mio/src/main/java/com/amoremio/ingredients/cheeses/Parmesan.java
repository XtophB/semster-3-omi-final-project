package com.amoremio.ingredients.cheeses;

import com.amoremio.ingredients.Ingredient;
import com.amoremio.ingredients.IngredientName;

public class Parmesan extends Ingredient {
  private static final float PRICE = 1.8f;
  private static final IngredientName NAME = IngredientName.PARMESAN;

  public Parmesan(int expiryDayOffset) {
    super(NAME, PRICE, expiryDayOffset);
  }
}
