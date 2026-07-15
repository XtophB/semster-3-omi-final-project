package com.amoremio.ingredients.meats;

import com.amoremio.ingredients.IngredientName;
import com.amoremio.ingredients.Meat;

public class Chicken extends Meat {
  private static final float PRICE = 3.0f;
  private static final IngredientName NAME = IngredientName.CHICKEN;

  public Chicken(int expiryDayOffset) {
    super(NAME, PRICE, expiryDayOffset);
  }
}
