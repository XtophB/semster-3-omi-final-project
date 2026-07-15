package com.amoremio.ingredients.cheeses;

import com.amoremio.ingredients.Cheese;
import com.amoremio.ingredients.IngredientName;

public class Mozzarella extends Cheese {
  private static final float PRICE = 1.5f;
  private static final IngredientName NAME = IngredientName.MOZZARELLA;

  public Mozzarella(int expiryDayOffset) {
    super(NAME, PRICE, expiryDayOffset);
  }
}
