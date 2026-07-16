package com.amoremio.ingredients;

public class Dough extends Ingredient {
  private static final float PRICE = 0.5f;
  private static final IngredientName name = IngredientName.DOUGH;
  private final DoughType doughType;

  public Dough(int expiryDayOffset, DoughType doughType) {
    super(name, PRICE, expiryDayOffset);
    this.doughType = doughType;
  }
}
