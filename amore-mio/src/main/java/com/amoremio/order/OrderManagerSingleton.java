package com.amoremio.order;

import com.amoremio.store.Branch;
import com.amoremio.store.City;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The manager for all orders. This singleton acts as a global ordering system that routes all
 * orders to their respective branches.
 */
public class OrderManagerSingleton {
  private static OrderManagerSingleton INSTANCE;
  private final Map<City, Branch> branches = new HashMap<>();
  private final List<OrderProcess> orderProcesses = new ArrayList<>();

  private OrderManagerSingleton() {}

  /**
   * A method to create and return the singleton instance.
   *
   * @return the manager singleton instance
   */
  public static OrderManagerSingleton getInstance() {
    if (INSTANCE == null) {
      INSTANCE = new OrderManagerSingleton();
    }
    return INSTANCE;
  }

  /**
   * A method to register a branch to the manager instance, allowing routing to that branch.
   *
   * @param city the city where the branch is located in
   * @param branch the branch to register
   */
  public void addBranch(City city, Branch branch) {
    branches.computeIfAbsent(city, k -> branch);
  }

  /**
   * A method to place an order, which then gets routed to the correct branch.
   *
   * @param order the order containing the pizzas
   * @param customer the customer responsible for the order
   */
  public void placeOrder(Order order, Customer customer) {
    OrderProcess orderProcess = new OrderProcess(order, customer);
    orderProcesses.add(orderProcess);
    City city = order.getCity();
    Branch branch = branches.get(city);
    branch.processOrder(orderProcess);
  }
}
