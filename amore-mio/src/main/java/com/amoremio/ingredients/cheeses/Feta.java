package com.amoremio.ingredients.cheeses;

import com.amoremio.ingredients.Cheese;
import com.amoremio.ingredients.IngredientName;

/**
 * A concrete class representing Feta cheese, which extends the Cheese class.
 */
public class Feta extends Cheese {
  private static final float PRICE = 2.0f;
  private static final IngredientName NAME = IngredientName.FETA;

  /**
   * Constructor of the Feta class.
   *
   * @param expiryDayOffset the offset from the current date when the ingredient will expire
   */
  public Feta(int expiryDayOffset) {
    super(NAME, PRICE, expiryDayOffset);
  }
}
