package com.amoremio.ingredients.misc;

import com.amoremio.ingredients.Ingredient;
import com.amoremio.ingredients.IngredientName;

/**
 * A concrete class representing chilli flakes.
 */
public class ChilliFlake extends Ingredient {
  private static final float PRICE = 0.5f;
  private static final IngredientName NAME = IngredientName.CHILLI_FLAKE;

  /**
   * Constructor of the chilli flake class.
   *
   * @param expiryDayOffset the offset from the current date when the ingredient will expire
   */
  public ChilliFlake(int expiryDayOffset) {
    super(NAME, PRICE, expiryDayOffset);
  }
}
