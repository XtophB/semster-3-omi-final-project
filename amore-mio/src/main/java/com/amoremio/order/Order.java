package com.amoremio.order;

import com.amoremio.store.City;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
public class Order {
  List<OrderedPizza> pizzaOrder;
  PaymentType paymentType;
  City city;

  public Order(List<OrderedPizza> pizzaOrder, PaymentType paymentType, City city) {
    this.pizzaOrder = pizzaOrder;
    this.paymentType = paymentType;
    this.city = city;
  }
}
