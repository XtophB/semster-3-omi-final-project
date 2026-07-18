package com.amoremio.ingredients.cheeses;

import com.amoremio.ingredients.Cheese;
import com.amoremio.ingredients.IngredientName;

/**
 * A concrete class representing Mozzarella cheese, which extends the Cheese class.
 */
public class Mozzarella extends Cheese {
  private static final float PRICE = 1.5f;
  private static final IngredientName NAME = IngredientName.MOZZARELLA;

  /**
   * Constructor of the Mozzarella class.
   *
   * @param expiryDayOffset the offset from the current date when the ingredient will expire
   */
  public Mozzarella(int expiryDayOffset) {
    super(NAME, PRICE, expiryDayOffset);
  }
}
