package com.amoremio.ingredients.cheeses;

import com.amoremio.ingredients.Cheese;
import com.amoremio.ingredients.IngredientName;

/** A concrete class representing Parmesan cheese, which extends the Cheese class. */
public class Parmesan extends Cheese {
  private static final float PRICE = 1.8f;
  private static final IngredientName NAME = IngredientName.PARMESAN;

  /**
   * Constructor of the Parmesan class.
   *
   * @param expiryDayOffset the offset from the current date when the ingredient will expire
   */
  public Parmesan(int expiryDayOffset) {
    super(NAME, PRICE, expiryDayOffset);
  }
}
