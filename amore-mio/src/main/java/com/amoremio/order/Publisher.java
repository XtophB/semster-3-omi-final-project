package com.amoremio.order;

public interface Publisher {
  void addSubscriber(Subscriber subscriber);
  void notifySubscribers();
}
