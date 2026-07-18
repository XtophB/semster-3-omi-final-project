package com.amoremio.ingredients.misc;

import com.amoremio.ingredients.Ingredient;
import com.amoremio.ingredients.IngredientName;

/**
 * A concrete class representing onions, which extends the Ingredient class.
 */
public class Onion extends Ingredient {
  private static final float PRICE = 0.6f;
  private static final IngredientName NAME = IngredientName.ONION;

  /**
   * Constructor of the Onion class.
   *
   * @param expiryDayOffset the offset from the current date when the ingredient will expire
   */
  public Onion(int expiryDayOffset) {
    super(NAME, PRICE, expiryDayOffset);
  }
}
