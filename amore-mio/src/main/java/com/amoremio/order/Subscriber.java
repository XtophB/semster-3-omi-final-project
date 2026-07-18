package com.amoremio.order;

/**
 * Interface for the subscriber pattern.
 */
public interface Subscriber {

  /**
   * A method to notify the subscriber of the order process state change.
   *
   * @param process the order process object containing the order and its state
   */
  void update(OrderProcess process);
}
