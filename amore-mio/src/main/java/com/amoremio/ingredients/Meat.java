package com.amoremio.ingredients;

/**
 * Abstract base class for meat ingredients.
 */
public abstract class Meat extends Ingredient {
  /**
   * Constructor for a meat ingredient.
   *
   * @param name the specific meat type
   * @param basePrice the base price of the meat
   * @param expiryDayOffset the offset in days from today when the meat expires
   */
  public Meat(IngredientName name, float basePrice, int expiryDayOffset) {
    super(name, basePrice, expiryDayOffset);
  }
}
