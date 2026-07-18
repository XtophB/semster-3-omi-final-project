package com.amoremio.employee;

import com.amoremio.store.Branch;
import java.util.ArrayList;
import java.util.List;

public final class EmployeeManagerSingleton {
  private static EmployeeManagerSingleton INSTANCE;
  private final List<Employee> globalEmployees = new ArrayList<>();

  private EmployeeManagerSingleton() {}

  public static EmployeeManagerSingleton getInstance() {
    if (INSTANCE == null) {
      INSTANCE = new EmployeeManagerSingleton();
    }
    return INSTANCE;
  }

  public void hireEmployee(Branch branch, Position position, EmployeeFactory employeeFactory) {
    Employee employee = employeeFactory.create(position);
    branch.addEmployee(employee);
    globalEmployees.add(employee);
  }

  public void removeEmployee(Branch branch, Employee employee) {
    branch.removeEmployee(employee);
    globalEmployees.remove(employee);
  }

  public void payEmployees() {
    for (Employee employee : globalEmployees) {
      employee.getPaid();
      employee.getBalance();
      System.out.println(employee.getClass().getSimpleName() + " paid. Current balance: " + employee.getBalance());
    }
  }
}
