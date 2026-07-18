package com.amoremio.store;

import com.amoremio.ingredients.Ingredient;
import com.amoremio.ingredients.IngredientFactory;
import com.amoremio.ingredients.IngredientName;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a supplier of ingredients.
 */
public class Supplier {
  private final IngredientFactory ingredientFactory;

  /**
   * Constructor that receives the ingredient factory pattern injected.
   *
   * @param ingredientFactory factory that magically creates ingredients
   */
  public Supplier(IngredientFactory ingredientFactory) {
    this.ingredientFactory = ingredientFactory;
  }

  /**
   * A method to deliver ingredients to a specific storage.
   *
   * @param storage the storage to deliver to
   * @param name the ingredients requested
   * @param quantity the amount requested
   * @param expiryDayOffset the amount of days until the ingredients expire
   */
  public void deliverIngredients(
      Storage storage, IngredientName name, int quantity, int expiryDayOffset) {
    List<Ingredient> ingredientList = new ArrayList<>();
    for (int i = 0; i < quantity; i++) {
      ingredientList.add(ingredientFactory.create(name, expiryDayOffset));
    }
    storage.restock(ingredientList);
  }
}
