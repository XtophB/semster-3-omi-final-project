package com.amoremio.ingredients.meats;

import com.amoremio.ingredients.IngredientName;
import com.amoremio.ingredients.Meat;

public class Carpaccio extends Meat {
  private static final float PRICE = 2.8f;
  private static final IngredientName NAME = IngredientName.CARPACCIO;

  public Carpaccio(int expiryDayOffset) {
    super(NAME, PRICE, expiryDayOffset);
  }
}
