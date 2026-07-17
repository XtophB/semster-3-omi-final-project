package com.amoremio.order;

import lombok.Getter;

public class OrderProcess implements Publisher {
  @Getter
  private Order order;
  private Subscriber subscriber;
  @Getter private OrderState state;

  public OrderProcess(Order order, Subscriber subscriber) {
    this.order = order;
    this.subscriber = subscriber;
    this.state = OrderState.CONFIRMED;
  }

  public void setState(OrderState state) {
    this.state = state;
    notifySubscribers();
  }

  @Override
  public void notifySubscribers() {
    if(subscriber != null) {
      subscriber.update(this);
    }
  }

  @Override
  public void addSubscriber(Subscriber subscriber) {
    this.subscriber = subscriber;
  }

}
