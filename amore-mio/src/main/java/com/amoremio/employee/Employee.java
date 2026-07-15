package com.amoremio.employee;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Employee {
  private int basePay;
  private float payMultiplier;
  private float balance;

  public Employee(int basePay, float payMultiplier) {
    this.basePay = basePay;
    this.payMultiplier = payMultiplier;
    this.balance = 0;
  }
  public void getPaid() {
    balance += basePay * payMultiplier;
  }
}
