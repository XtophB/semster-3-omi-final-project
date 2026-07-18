package com.amoremio.order;

import com.amoremio.ingredients.IngredientName;
import java.util.List;
import lombok.Getter;

@Getter
public class OrderedPizza {
  private final List<IngredientName> toppings;

  public OrderedPizza(List<IngredientName> toppings) {
    this.toppings = toppings;
  }
}
