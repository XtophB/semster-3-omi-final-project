package com.amoremio.order;

import com.amoremio.store.Branch;
import com.amoremio.store.City;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderManagerSingleton {
  private static OrderManagerSingleton INSTANCE;
  private final Map<City, Branch> branches = new HashMap<>();
  private final List<OrderProcess> orderProcesses = new ArrayList<>();

  private OrderManagerSingleton() {}

  public static OrderManagerSingleton getInstance() {
    if (INSTANCE == null) {
      INSTANCE = new OrderManagerSingleton();
    }
    return INSTANCE;
  }

  public void addBranch(City city, Branch branch) {
    branches.computeIfAbsent(city, k -> branch);
  }

  public void placeOrder(Order order, Customer customer) {
    OrderProcess orderProcess = new OrderProcess(order, customer);
    orderProcesses.add(orderProcess);
    City city = order.getCity();
    Branch branch = branches.get(city);
    branch.processOrder(orderProcess);
  }

}
