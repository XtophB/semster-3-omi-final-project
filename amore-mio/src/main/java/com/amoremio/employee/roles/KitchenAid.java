package com.amoremio.employee.roles;

import com.amoremio.employee.Employee;
import com.amoremio.ingredients.Dough;
import com.amoremio.ingredients.Ingredient;
import com.amoremio.ingredients.IngredientName;
import com.amoremio.ingredients.Sauce;
import com.amoremio.pizza.Pizza;
import com.amoremio.pizza.builders.AbstractPizzaBuilder;
import com.amoremio.order.Order;
import com.amoremio.order.OrderedPizza;
import com.amoremio.store.Storage;
import java.util.ArrayList;
import java.util.List;

public class KitchenAid extends Employee {
  public KitchenAid(int basePay, float payMultiplier) {
    super(basePay, payMultiplier);
  }

  public List<Pizza> preparePizza(Storage storage, AbstractPizzaBuilder pizzaBuilder, Order order) {
    List<OrderedPizza> orderedPizzas = order.getPizzaOrder();
    List<Pizza> preparedPizzas = new ArrayList<>();
    for (OrderedPizza pizza : orderedPizzas) {
      pizzaBuilder.createPizza();

      Dough dough = (Dough) storage.consumeIngredient(IngredientName.DOUGH);
      Sauce sauce = (Sauce) storage.consumeIngredient(IngredientName.SAUCE);

      pizzaBuilder.buildDough(dough);
      pizzaBuilder.buildSauce(sauce);

      List<Ingredient> toppings = pizza.getToppings();
      pizzaBuilder.buildToppings(toppings);

      preparedPizzas.add(pizzaBuilder.getPizza());
    }
    return preparedPizzas;
  }
}
