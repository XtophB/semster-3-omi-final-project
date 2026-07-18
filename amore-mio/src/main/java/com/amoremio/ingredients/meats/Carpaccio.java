package com.amoremio.ingredients.meats;

import com.amoremio.ingredients.IngredientName;
import com.amoremio.ingredients.Meat;

/**
 * A concrete class representing Carpaccio meat, which extends the Meat class.
 */
public class Carpaccio extends Meat {
  private static final float PRICE = 2.8f;
  private static final IngredientName NAME = IngredientName.CARPACCIO;

  /**
   * Constructor of the Carpaccio class.
   *
   * @param expiryDayOffset the offset from the current date when the ingredient will expire
   */
  public Carpaccio(int expiryDayOffset) {
    super(NAME, PRICE, expiryDayOffset);
  }
}
