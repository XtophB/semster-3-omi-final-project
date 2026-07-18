package com.amoremio.ingredients.misc;

import com.amoremio.ingredients.Ingredient;
import com.amoremio.ingredients.IngredientName;

/**
 * A concrete class representing corn, which extends the Ingredient class.
 */
public class Corn extends Ingredient {
  private static final float PRICE = 1.0f;
  private static final IngredientName NAME = IngredientName.CORN;

  /**
   * Constructor of the Corn class.
   *
   * @param expiryDayOffset the offset from the current date when the ingredient will expire
   */
  public Corn(int expiryDayOffset) {
    super(NAME, PRICE, expiryDayOffset);
  }
}
