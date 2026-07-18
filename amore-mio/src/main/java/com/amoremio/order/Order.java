package com.amoremio.order;

import com.amoremio.store.City;
import java.util.List;
import lombok.Getter;

/**
 * Represents an order made by a customer, containing a list of ordered pizzas, the payment type,
 * and the city for delivery. This object is wrapped by {@link OrderProcess}.
 */
@Getter
public class Order {
  List<OrderedPizza> pizzaOrder;
  PaymentType paymentType;
  City city;

  /**
   * The constructor for the order object.
   *
   * @param pizzaOrder the list of ordered pizzas in the order
   * @param paymentType the payment type for the order
   * @param city the city for delivery
   */
  public Order(List<OrderedPizza> pizzaOrder, PaymentType paymentType, City city) {
    this.pizzaOrder = pizzaOrder;
    this.paymentType = paymentType;
    this.city = city;
  }
}
