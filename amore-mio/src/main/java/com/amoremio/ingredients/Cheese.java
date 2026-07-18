package com.amoremio.ingredients;

/**
 * Abstract base class for cheese ingredients.
 */
public abstract class Cheese extends Ingredient {
  /**
   * Constructor for a cheese ingredient.
   *
   * @param name the specific cheese type
   * @param basePrice the base price of the cheese
   * @param expiryDayOffset the offset in days from today when the cheese expires
   */
  public Cheese(IngredientName name, float basePrice, int expiryDayOffset) {
    super(name, basePrice, expiryDayOffset);
  }
}
