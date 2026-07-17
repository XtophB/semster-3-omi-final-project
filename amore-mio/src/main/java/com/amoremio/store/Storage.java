package com.amoremio.store;

import com.amoremio.ingredients.Ingredient;
import com.amoremio.ingredients.IngredientName;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import lombok.Getter;

public class Storage {
  @Getter
  private final EnumMap<IngredientName, ArrayList<Ingredient>> inventory;
  private final Supplier supplier;
  private static final int TARGET_STOCK = 100;
  private static final int EXPIRY_OFFSET = 7;

  public Storage(Supplier supplier) {
    this.inventory = new EnumMap<>(IngredientName.class);
    this.supplier = supplier;

    for (IngredientName name : IngredientName.values()) {
      inventory.put(name, new ArrayList<>());
    }
  }

  public void restock(List<Ingredient> ingredientList) {
    for (Ingredient ingredient : ingredientList) {
      inventory.get(ingredient.getName()).add(ingredient);
    }
  }

  public void callSupplier(IngredientName name){
    int orderQuantity = TARGET_STOCK - inventory.get(name).size();
    supplier.deliverIngredients(this, name, orderQuantity, EXPIRY_OFFSET);
    System.out.println("Restocked " + orderQuantity + " units of " + name + " from supplier.");
  }

  public boolean quantityCheck(IngredientName name) {
    if (inventory.get(name).size() < TARGET_STOCK) {
      return false;
    }
    return true;
  }

  public Ingredient consumeIngredient(IngredientName name) {
    if (inventory.get(name).isEmpty()) {
      throw new IllegalStateException("Not enough " + name + " in storage. Please restock.");
    }
    return inventory.get(name).removeFirst();
  }
}
