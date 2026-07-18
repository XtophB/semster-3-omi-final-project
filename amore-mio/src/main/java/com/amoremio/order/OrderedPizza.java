package com.amoremio.order;

import com.amoremio.ingredients.IngredientName;
import java.util.List;
import lombok.Getter;

/**
 * The OrderedPizza class represents a pizza that has been ordered, containing a list of toppings.
 */
@Getter
public class OrderedPizza {
  private final List<IngredientName> toppings;

  /**
   * The constructor for the ordered pizza object.
   *
   * @param toppings the list of toppings for the pizza
   */
  public OrderedPizza(List<IngredientName> toppings) {
    this.toppings = toppings;
  }
}
