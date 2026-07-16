package com.amoremio.pizza.builders;

import com.amoremio.ingredients.Dough;
import com.amoremio.ingredients.Ingredient;
import com.amoremio.ingredients.Sauce;
import com.amoremio.pizza.Pizza;
import com.amoremio.pizza.PizzaState;
import com.amoremio.pizza.PizzaType;
import java.util.List;
import lombok.Getter;

public abstract class AbstractPizzaBuilder implements PizzaBuilder {
  @Getter
  protected Pizza pizza;
  private final PizzaType pizzaType;

  public AbstractPizzaBuilder(PizzaType pizzaType) {
    this.pizzaType = pizzaType;
    createPizza();
  }

  @Override
  public void buildDough(Dough dough) {
    pizza.addIngredient(dough);
  }

  @Override
  public void buildSauce(Sauce sauce) {
    pizza.addIngredient(sauce);
  }

  @Override
  public void buildToppings(List<Ingredient> toppings) {
    for (Ingredient topping : toppings) {
      pizza.addIngredient(topping);
      pizza.setPrice(pizza.getPrice() + topping.getPrice());
    }
  }

  public void createPizza(){
    pizza = new Pizza(pizzaType);
    pizza.setPizzaState(PizzaState.RAW);
  }

}