package com.amoremio.store;

import com.amoremio.ingredients.Ingredient;
import com.amoremio.ingredients.IngredientName;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.ListIterator;
import lombok.Getter;

/** Represents the storage system that holds all ingredients. */
public class Storage {
  private static final int TARGET_STOCK = 100;
  private static final int EXPIRY_OFFSET = 7;
  @Getter private final EnumMap<IngredientName, ArrayList<Ingredient>> inventory;
  private final Supplier supplier;

  /**
   * THe constructor where the supplier is injected.
   *
   * @param supplier the supplier responsible for magically creating ingredients
   */
  public Storage(Supplier supplier) {
    this.inventory = new EnumMap<>(IngredientName.class);
    this.supplier = supplier;

    for (IngredientName name : IngredientName.values()) {
      inventory.put(name, new ArrayList<>());
    }
  }

  /**
   * A method allowingn the restocking of missing ingredients.
   *
   * @param ingredientList the list of ingredients to restock
   */
  public void restock(List<Ingredient> ingredientList) {
    for (Ingredient ingredient : ingredientList) {
      inventory.get(ingredient.getName()).add(ingredient);
    }
  }

  /**
   * A method to request the supplier to restock the missing ingredients.
   *
   * @param name the name of the ingredient to restock
   */
  public void callSupplier(IngredientName name) {
    int orderQuantity = TARGET_STOCK - inventory.get(name).size();
    supplier.deliverIngredients(this, name, orderQuantity, EXPIRY_OFFSET);
    System.out.println("Restocked " + orderQuantity + " units of " + name + " from supplier.");
  }

  /**
   * A method to check if there are enough ingredients to fulfil the order.
   *
   * @param name the name of the ingredient to check
   * @return boolean if there are enough of that ingredient type
   */
  public boolean checkQuantitySufficient(IngredientName name) {
    checkExpiry(name);
    if (inventory.get(name).size() < TARGET_STOCK) {
      return false;
    }
    return true;
  }

  /**
   * A method to remove an ingredient from the storage to use on a pizza.
   *
   * @param name the ingredient being used
   * @return the consumed ingredient
   */
  public Ingredient consumeIngredient(IngredientName name) {
    checkExpiry(name);
    if (inventory.get(name).isEmpty()) {
      throw new IllegalStateException("Not enough " + name + " in storage. Please restock.");
    }
    return inventory.get(name).removeFirst();
  }

  private void checkExpiry(IngredientName name) {
    List<Ingredient> currentIngredient = inventory.get(name);
    ListIterator<Ingredient> iterator = currentIngredient.listIterator();
    while (iterator.hasNext()) {
      Ingredient ingredient = iterator.next();
      if (ingredient.getExpiryDate().isBefore(LocalDate.now())) {
        iterator.remove();
      }
    }
  }
}
