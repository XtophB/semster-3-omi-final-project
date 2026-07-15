package com.amoremio.employee;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Employee {
  private int basePay;
  private float payMultiplier;
  private float actualPay;

  public Employee(int basePay, float payMultiplier) {
    this.basePay = basePay;
    this.payMultiplier = payMultiplier;
  }
}
