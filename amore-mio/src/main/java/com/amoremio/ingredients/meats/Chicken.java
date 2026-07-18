package com.amoremio.ingredients.meats;

import com.amoremio.ingredients.IngredientName;
import com.amoremio.ingredients.Meat;

/**
 * A concrete class representing Chicken meat, which extends the Meat class.
 */
public class Chicken extends Meat {
  private static final float PRICE = 3.0f;
  private static final IngredientName NAME = IngredientName.CHICKEN;

  /**
   * Constructor of the Chicken class.
   *
   * @param expiryDayOffset the offset from the current date when the ingredient will expire
   */
  public Chicken(int expiryDayOffset) {
    super(NAME, PRICE, expiryDayOffset);
  }
}
