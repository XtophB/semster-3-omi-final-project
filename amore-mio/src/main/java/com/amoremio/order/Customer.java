package com.amoremio.order;

/** Represents the customer using the observer pattern. */
public class Customer implements Subscriber {
  String name;

  /**
   * Constructor for the Customer class.
   *
   * @param name the name of the customer
   */
  public Customer(String name) {
    this.name = name;
  }

  /**
   * A method that gets called when the order process state changes. It prints out the current state
   * of the order and notifies the customer if the order is delayed.
   *
   * @param process the object we are subscribing to
   */
  @Override
  public void update(OrderProcess process) {
    System.out.println("Customer " + name + " received update for order: " + process.getState());
    if (process.getState() == OrderState.DELAYED) {
      System.out.println("Customer " + name + "'s order is being delayed.");
    }
  }
}
