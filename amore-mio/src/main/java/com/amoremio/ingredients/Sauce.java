package com.amoremio.ingredients;

/** A concrete class representing sauce, which extends the Ingredient class. */
public class Sauce extends Ingredient {
  private static final float PRICE = 0.5f;
  private static final IngredientName NAME = IngredientName.SAUCE;

  /**
   * Constructor of the sauce class.
   *
   * @param expiryDayOffset the offset from the current date when the ingredient will expire
   */
  public Sauce(int expiryDayOffset) {
    super(NAME, PRICE, expiryDayOffset);
  }
}
