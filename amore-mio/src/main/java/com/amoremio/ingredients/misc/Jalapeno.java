package com.amoremio.ingredients.misc;

import com.amoremio.ingredients.Ingredient;
import com.amoremio.ingredients.IngredientName;

public class Jalapeno extends Ingredient {
  private static final float PRICE = 0.8f;
  private static final IngredientName name = IngredientName.JALAPENO;

  public Jalapeno(int expiryDayOffset) {
    super(name, PRICE, expiryDayOffset);
  }
}
