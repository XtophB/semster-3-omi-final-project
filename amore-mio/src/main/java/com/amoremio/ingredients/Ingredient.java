package com.amoremio.ingredients;

import java.time.LocalDate;
import lombok.Getter;

/**
 * Abstract base class for all ingredients in the system.
 * Each ingredient has a {@link IngredientName}, a price, and an expiry date.
 */
@Getter
public abstract class Ingredient {
  private float price;
  private LocalDate expiryDate;
  private IngredientName name;

  /**
   * Constructor for an ingredient.
   *
   * @param name the type of ingredient
   * @param price the base price of the ingredient
   * @param expiryDayOffset the offset in days from today when the ingredient expires
   */
  public Ingredient(IngredientName name, float price, int expiryDayOffset) {
    this.name = name;
    this.price = price;
    this.expiryDate = LocalDate.now().plusDays(expiryDayOffset);
  }
}
