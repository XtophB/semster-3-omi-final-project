package com.amoremio.employee.roles;

import com.amoremio.employee.Employee;
import com.amoremio.ingredients.Dough;
import com.amoremio.ingredients.Ingredient;
import com.amoremio.ingredients.IngredientName;
import com.amoremio.ingredients.Sauce;
import com.amoremio.order.Order;
import com.amoremio.order.OrderedPizza;
import com.amoremio.pizza.Pizza;
import com.amoremio.pizza.builders.AbstractPizzaBuilder;
import com.amoremio.store.Storage;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the kitchen aid in a restaurant, sole job is to prepare pizzas.
 */
public class KitchenAid extends Employee {

  /**
   * Constructor for the kitchen aid.
   *
   * @param basePay daily base pay
   * @param payMultiplier multiplier based on employee importance
   */
  public KitchenAid(int basePay, float payMultiplier) {
    super(basePay, payMultiplier);
  }

  /**
   * Method to prepare a pizza in a specific style based on injected builder pattern.
   *
   * @param storage the storage to pull ingredients from
   * @param pizzaBuilder the builder used to create a pizza in a specific style
   * @param order the order containing the pizzas to be created
   * @return a list of pizzas that have been prepared
   */
  public List<Pizza> preparePizza(Storage storage, AbstractPizzaBuilder pizzaBuilder, Order order) {
    List<OrderedPizza> orderedPizzas = order.getPizzaOrder();
    List<Pizza> preparedPizzas = new ArrayList<>();
    for (OrderedPizza pizza : orderedPizzas) {
      pizzaBuilder.createPizza();

      Dough dough = (Dough) storage.consumeIngredient(IngredientName.DOUGH);
      Sauce sauce = (Sauce) storage.consumeIngredient(IngredientName.SAUCE);

      pizzaBuilder.buildDough(dough);
      pizzaBuilder.buildSauce(sauce);

      // list of enums to map used to map to proper objects from storage retrieval
      List<IngredientName> toppingNames = pizza.getToppings();
      List<Ingredient> toppings = new ArrayList<>();
      for (IngredientName name : toppingNames) {
        toppings.add(storage.consumeIngredient(name));
      }
      pizzaBuilder.buildToppings(toppings);

      preparedPizzas.add(pizzaBuilder.getPizza());
    }
    return preparedPizzas;
  }

  /**
   * The method to redo a burnt pizza.
   *
   * @param burntPizza the burnt pizza containing the list of toppings used
   * @param storage the storage where to pull the new ingredients from
   * @param pizzaBuilder the same pizza builder used to create the original pizza
   * @return a new raw pizza object
   */
  public Pizza redoPizza(Pizza burntPizza, Storage storage, AbstractPizzaBuilder pizzaBuilder) {
    pizzaBuilder.createPizza();

    Dough dough = (Dough) storage.consumeIngredient(IngredientName.DOUGH);
    Sauce sauce = (Sauce) storage.consumeIngredient(IngredientName.SAUCE);

    pizzaBuilder.buildDough(dough);
    pizzaBuilder.buildSauce(sauce);

    List<Ingredient> toppings = new ArrayList<>();
    for (Ingredient ingredient : burntPizza.getIngredients()) {
      IngredientName name = ingredient.getName();
      if (name != IngredientName.DOUGH && name != IngredientName.SAUCE) {
        toppings.add(storage.consumeIngredient(name));
      }
    }
    pizzaBuilder.buildToppings(toppings);

    return pizzaBuilder.getPizza();
  }
}
