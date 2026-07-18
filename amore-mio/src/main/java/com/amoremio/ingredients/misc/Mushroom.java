package com.amoremio.ingredients.misc;

import com.amoremio.ingredients.Ingredient;
import com.amoremio.ingredients.IngredientName;

/**
 * A concrete class representing mushrooms, which extends the Ingredient class.
 */
public class Mushroom extends Ingredient {
  private static final float PRICE = 1.2f;
  private static final IngredientName NAME = IngredientName.MUSHROOM;

  /**
   * Constructor of the Mushroom class.
   *
   * @param expiryDayOffset the offset from the current date when the ingredient will expire
   */
  public Mushroom(int expiryDayOffset) {
    super(NAME, PRICE, expiryDayOffset);
  }
}
