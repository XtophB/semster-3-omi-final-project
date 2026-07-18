package com.amoremio.ingredients.misc;

import com.amoremio.ingredients.Ingredient;
import com.amoremio.ingredients.IngredientName;

/**
 * A concrete class representing jalapeno peppers, which extends the Ingredient class.
 */
public class Jalapeno extends Ingredient {
  private static final float PRICE = 0.8f;
  private static final IngredientName NAME = IngredientName.JALAPENO;

  /**
   * Constructor of the Jalapeno class.
   *
   * @param expiryDayOffset the offset from the current date when the ingredient will expire
   */
  public Jalapeno(int expiryDayOffset) {
    super(NAME, PRICE, expiryDayOffset);
  }
}
