package com.amoremio.ingredients.misc;

import com.amoremio.ingredients.Ingredient;
import com.amoremio.ingredients.IngredientName;

public class ChilliFlake extends Ingredient{
  private static final float PRICE = 0.5f;
  private static final IngredientName NAME = IngredientName.CHILLIFLAKES;

  public ChilliFlake(int expiryDayOffset) {
    super(NAME, PRICE, expiryDayOffset);
  }
}