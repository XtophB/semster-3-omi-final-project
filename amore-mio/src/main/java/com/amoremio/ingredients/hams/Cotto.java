package com.amoremio.ingredients.hams;

import com.amoremio.ingredients.Ham;
import com.amoremio.ingredients.IngredientName;

/**
 * A concrete class representing Cotto ham, which extends the Ham class.
 */
public class Cotto extends Ham {
  private static final float PRICE = 2.0f;
  private static final IngredientName NAME = IngredientName.COTTO;

  /**
   * Constructor of the Cotto class.
   *
   * @param expiryDayOffset the offset from the current date when the ingredient will expire
   */
  public Cotto(int expiryDayOffset) {
    super(NAME, PRICE, expiryDayOffset);
  }
}
