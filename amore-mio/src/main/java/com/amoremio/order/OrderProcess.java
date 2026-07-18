package com.amoremio.order;

import lombok.Getter;

// There is no interface for this class as we have a 1:1 relationship and the customers are directly
// added in the constructor
/**
 * The wrapper of the order object. This allows the observer pattern and allows observing the order.
 */
public class OrderProcess {
  @Getter private final Order order;
  private final Subscriber subscriber;
  @Getter private OrderState state;

  /**
   * THe constructor for the order process.
   *
   * @param order the order object containing the ordered pizzas
   * @param subscriber the customer who wants to observe their order
   */
  public OrderProcess(Order order, Subscriber subscriber) {
    this.order = order;
    this.subscriber = subscriber;
    this.state = OrderState.CONFIRMED;
  }

  /**
   * A method to set the state of the order, this then triggers a notification for all subscribers.
   *
   * @param state the new state of the order
   */
  public void setState(OrderState state) {
    this.state = state;
    notifySubscribers();
  }

  private void notifySubscribers() {
    if (subscriber != null) {
      subscriber.update(this);
    }
  }
}
