package com.amoremio.ingredients.hams;

import com.amoremio.ingredients.Ham;
import com.amoremio.ingredients.IngredientName;

/**
 * A concrete class representing Crudo ham, which extends the Ham class.
 */
public class Crudo extends Ham {
  private static final float PRICE = 2.0f;
  private static final IngredientName NAME = IngredientName.CRUDO;

  /**
   * Constructor of the Crudo class.
   *
   * @param expiryDayOffset the offset from the current date when the ingredient will expire
   */
  public Crudo(int expiryDayOffset) {
    super(NAME, PRICE, expiryDayOffset);
  }
}
