package com.amoremio.store.Order;

import com.amoremio.ingredients.Ingredient;
import java.util.List;
import lombok.Getter;

@Getter
public class OrderedPizza {
  private final List<Ingredient> toppings;

  public OrderedPizza(List<Ingredient> toppings) {
    this.toppings = toppings;
  }
}
