package com.amoremio.store;

import com.amoremio.ingredients.Ingredient;
import com.amoremio.ingredients.IngredientFactory;
import com.amoremio.ingredients.IngredientName;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

public class Storage {
  private final EnumMap<IngredientName, ArrayList<Ingredient>> inventory;
  private final Supplier supplier;

  public Storage(Supplier supplier) {
    this.inventory = new EnumMap<>(IngredientName.class);
    this.supplier = supplier;

    for (IngredientName name : IngredientName.values()) {
      inventory.put(name, new ArrayList<Ingredient>());
    }
  }

  public void restock(List<Ingredient> ingredientList) {
    for (Ingredient ingredient : ingredientList) {
      inventory.get(ingredient.getName()).add(ingredient);
    }
  }

  public Ingredient getIngredient(IngredientName name) {
    if (inventory.get(name).size() < 5) {
      supplier.deliverIngredients(this, name, 10, 7);
    }
    Ingredient retrievedItem = inventory.get(name).getFirst();
    inventory.get(name).removeFirst();
    return retrievedItem;
  }
}
