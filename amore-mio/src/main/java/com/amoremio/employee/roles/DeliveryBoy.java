package com.amoremio.employee.roles;

import com.amoremio.employee.Employee;
import com.amoremio.order.OrderProcess;
import com.amoremio.order.OrderState;
import com.amoremio.pizza.Pizza;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/** Represents the delivery driver. */
@Getter
@Setter
public class DeliveryBoy extends Employee {
  private int deliveredAmount = 0;
  private float deliveryBonusMultiplier;

  /**
   * Constructor for the delivery boy. Unlike other employees, delivery driver receives a multiplier
   * based on completed deliveries.
   *
   * @param basePay the base daily pay
   * @param payMultiplier the multiplier based on impotance of employee
   * @param deliveryBonusMultiplier the multiplier for the delivery bonus
   */
  public DeliveryBoy(int basePay, float payMultiplier, float deliveryBonusMultiplier) {
    super(basePay, payMultiplier);
    this.deliveryBonusMultiplier = deliveryBonusMultiplier;
  }

  /**
   * When the delivery driver gets paid, the amount delivered is set back to 0.
   */
  @Override
  public void getPaid() {
    float standardPay = getBasePay() * getPayMultiplier();
    float deliveryBonus = deliveredAmount * deliveryBonusMultiplier;
    setBalance(getBalance() + standardPay + deliveryBonus);
    this.deliveredAmount = 0;
  }

  /**
   * Delivers the order and updates the order state to DELIVERED, tracking how many deliveries have
   * been made.
   *
   * @param orderProcess the order which the driver is fulfilling
   * @param pizzas the pizzas from the order to be delivered
   */
  public void deliverOrder(OrderProcess orderProcess, List<Pizza> pizzas) {
    System.out.println("Delivering " + pizzas.size() + " pizza(s)...");
    deliveredAmount++;
    orderProcess.setState(OrderState.DELIVERED);
  }
}
