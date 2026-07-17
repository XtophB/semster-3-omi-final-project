package com.amoremio.order;

public class Customer implements Subscriber {
  String name;
  public Customer(String name) {
    this.name = name;
  }
  @Override
  public void update(OrderProcess process) {
    System.out.println("Customer " + name + " received update for order: " + process.getState());
    if (process.getState() == OrderState.DELAYED) {
      System.out.println("Customer " + name + "'s order is being delayed.");
    }
  }
}
