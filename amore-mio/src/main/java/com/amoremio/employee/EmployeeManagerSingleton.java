package com.amoremio.employee;

import com.amoremio.store.Branch;
import java.util.ArrayList;
import java.util.List;

/** A singleton class which is responsible for managing employees. */
public final class EmployeeManagerSingleton {
  private static EmployeeManagerSingleton INSTANCE;
  private final List<Employee> globalEmployees = new ArrayList<>();

  private EmployeeManagerSingleton() {}

  /**
   * The creation and retrieval of the singleton instance of the EmployeeManagerSingleton class.
   *
   * @return the manager singleton instance
   */
  public static EmployeeManagerSingleton getInstance() {
    if (INSTANCE == null) {
      INSTANCE = new EmployeeManagerSingleton();
    }
    return INSTANCE;
  }

  /**
   * A method to hire the employee at a specified branch, using the factory pattern.
   *
   * @param branch the branch where the employee will work at
   * @param position the role of the employee
   * @param employeeFactory the factory to create the employee
   */
  public void hireEmployee(Branch branch, Position position, EmployeeFactory employeeFactory) {
    Employee employee = employeeFactory.create(position);
    branch.addEmployee(employee);
    globalEmployees.add(employee);
  }

  /**
   * A method to fire an employee at a specified branch, removing them from the global employee
   * list.
   *
   * @param branch the branch the employee works at
   * @param employee the employee to fire
   */
  public void removeEmployee(Branch branch, Employee employee) {
    branch.removeEmployee(employee);
    globalEmployees.remove(employee);
  }

  /** A method to pay all employees that are currently in the global employee list. */
  public void payEmployees() {
    for (Employee employee : globalEmployees) {
      employee.getPaid();
      employee.getBalance();
      System.out.println(
          employee.getClass().getSimpleName() + " paid. Current balance: " + employee.getBalance());
    }
  }
}
