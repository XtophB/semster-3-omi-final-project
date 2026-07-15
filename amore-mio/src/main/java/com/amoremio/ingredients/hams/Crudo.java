package com.amoremio.ingredients.hams;

import com.amoremio.ingredients.Ham;
import com.amoremio.ingredients.IngredientName;

public class Crudo extends Ham {
  private static final float PRICE = 2.0f;
  private static final IngredientName NAME = IngredientName.CRUDO;

  public Crudo(int expiryDayOffset) {
    super(NAME, PRICE, expiryDayOffset);
  }
}
