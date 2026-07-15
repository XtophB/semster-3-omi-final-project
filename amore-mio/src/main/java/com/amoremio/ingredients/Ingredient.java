package com.amoremio.ingredients;

import java.time.LocalDate;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
public abstract class Ingredient {
  private float basePrice;
  private LocalDate expiryDate;
  private IngredientName name;

  public Ingredient(IngredientName name, float basePrice, int expiryDayOffset) {
    this.name = name;
    this.basePrice = basePrice;
    this.expiryDate = LocalDate.now().plusDays(expiryDayOffset);
  }
}
