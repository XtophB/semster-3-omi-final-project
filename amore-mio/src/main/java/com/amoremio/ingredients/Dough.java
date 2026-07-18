package com.amoremio.ingredients;

/**
 * A concrete class representing dough, which extends the Ingredient class.
 */
public class Dough extends Ingredient {
  private static final float PRICE = 0.5f;
  private static final IngredientName NAME = IngredientName.DOUGH;

  /**
   * Constructor for the dough object.
   *
   * @param expiryDayOffset the amount of days until the dough expires
   */
  public Dough(int expiryDayOffset) {
    super(NAME, PRICE, expiryDayOffset);
  }
}
