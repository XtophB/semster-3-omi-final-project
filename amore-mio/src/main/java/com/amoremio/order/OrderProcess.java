package com.amoremio.order;

import lombok.Getter;

// We don't have an interface for the publisher as there are no plans on extending the order. Each
// order is the same, while it is true that an interface is needed to follow DIP (dependency
// inversion principle -- depend on abstractions), it also violates the YANGI (you aren't gonna need
// it) principle. This was a common problem in my se2 contribution.
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
