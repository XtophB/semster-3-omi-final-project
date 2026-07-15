package com.amoremio.employee.payroll;

public final class EmployeeManager {
  private static EmployeeManager INSTANCE;

  private EmployeeManager() {}

  public static EmployeeManager getInstance() {
    if (INSTANCE == null) {
      INSTANCE = new EmployeeManager();
    }
    return INSTANCE;
  }

}
