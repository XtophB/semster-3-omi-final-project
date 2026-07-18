package com.amoremio.ingredients;

/**
 * Abstract base class for ham ingredients.
 */
public abstract class Ham extends Ingredient {
  /**
   * Constructor for a ham ingredient.
   *
   * @param name the specific ham type
   * @param basePrice the base price of the ham
   * @param expiryDayOffset the offset in days from today when the ham expires
   */
  public Ham(IngredientName name, float basePrice, int expiryDayOffset) {
    super(name, basePrice, expiryDayOffset);
  }
}
