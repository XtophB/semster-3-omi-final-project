package com.amoremio.store;

import com.amoremio.ingredients.Ingredient;
import com.amoremio.ingredients.IngredientFactory;
import com.amoremio.ingredients.IngredientName;
import java.util.ArrayList;
import java.util.List;

public class Supplier {
  private final IngredientFactory ingredientFactory;

  public Supplier(IngredientFactory ingredientFactory) {
    this.ingredientFactory = ingredientFactory;
  }

  public void deliverIngredients(
      Storage storage, IngredientName name, int quantity, int expiryDayOffset) {
    List<Ingredient> ingredientList = new ArrayList<>();
    for (int i = 0; i < quantity; i++) {
      ingredientList.add(ingredientFactory.create(name, expiryDayOffset));
    }
    storage.restock(ingredientList);
  }
}
