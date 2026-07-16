package com.amoremio.ingredients;

import java.time.LocalDate;
import lombok.Getter;

@Getter
public abstract class Ingredient {
  private float price;
  private LocalDate expiryDate;
  private IngredientName name;

  public Ingredient(IngredientName name, float price, int expiryDayOffset) {
    this.name = name;
    this.price = price;
    this.expiryDate = LocalDate.now().plusDays(expiryDayOffset);
  }
}
