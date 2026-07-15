package com.amoremio.employee;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeliveryBoy extends Employee {
  private int deliveredAmount = 0;
  private float deliveryBonusMultiplier;

  public DeliveryBoy(int basePay, float payMultiplier, float deliveryBonusMultiplier) {
    super(basePay, payMultiplier);
    this.deliveryBonusMultiplier = deliveryBonusMultiplier;
  }

  @Override
  public float getActualPay() {
    return super.getActualPay() + (deliveredAmount * deliveryBonusMultiplier);
  }
}
