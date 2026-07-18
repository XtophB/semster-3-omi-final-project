package com.amoremio.ingredients.misc;

import com.amoremio.ingredients.Ingredient;
import com.amoremio.ingredients.IngredientName;

/**
 * A concrete class representing salami, which extends the Ingredient class.
 */
public class Salami extends Ingredient {
  private static final float PRICE = 2.5f;
  private static final IngredientName NAME = IngredientName.SALAMI;

  /**
   * Constructor of the Salami class.
   *
   * @param expiryDayOffset the offset from the current date when the ingredient will expire
   */
  public Salami(int expiryDayOffset) {
    super(NAME, PRICE, expiryDayOffset);
  }
}
