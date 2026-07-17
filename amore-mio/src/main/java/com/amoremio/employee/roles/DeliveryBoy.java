package com.amoremio.employee.roles;

import com.amoremio.employee.Employee;
import com.amoremio.order.OrderProcess;
import com.amoremio.order.OrderState;
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
  public void getPaid() {
    float standardPay = getBasePay() * getPayMultiplier();
    float deliveryBonus = deliveredAmount * deliveryBonusMultiplier;
    setBalance(getBalance() + standardPay + deliveryBonus);
    this.deliveredAmount = 0;
  }

  public void deliverOrder(OrderProcess orderProcess) {
    System.out.println("Delivering order...");
    deliveredAmount++;
    orderProcess.setState(OrderState.DELIVERED);
  }
}
