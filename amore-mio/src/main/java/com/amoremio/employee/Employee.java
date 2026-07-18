package com.amoremio.employee;

import lombok.Getter;
import lombok.Setter;

/**
 * A base abstract employees a pizza branch can hire, from which specific concrete employees must be
 * made.
 */
@Getter
@Setter
public abstract class Employee {
  private int basePay;
  private float payMultiplier;
  private float balance;
  private boolean busy = false;

  /**
   * Constructor for an employee.
   *
   * @param basePay the base daily pay
   * @param payMultiplier the multiplier for the pay based on employee importance
   */
  public Employee(int basePay, float payMultiplier) {
    this.basePay = basePay;
    this.payMultiplier = payMultiplier;
    this.balance = 0;
  }

  /** A method to pay an employee. */
  public void getPaid() {
    balance += basePay * payMultiplier;
  }
}
