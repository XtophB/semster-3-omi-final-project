package com.amoremio.employee.payroll;

public final class EmployeeManagerSingleton {
  private static EmployeeManagerSingleton INSTANCE;

  private EmployeeManagerSingleton() {}

  public static EmployeeManagerSingleton getInstance() {
    if (INSTANCE == null) {
      INSTANCE = new EmployeeManagerSingleton();
    }
    return INSTANCE;
  }

}
