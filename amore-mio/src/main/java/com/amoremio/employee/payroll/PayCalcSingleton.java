package com.amoremio.employee.payroll;

public final class PayCalcSingleton {
  private static PayCalcSingleton INSTANCE;

  private PayCalcSingleton() {}

  public static PayCalcSingleton getInstance() {
    if (INSTANCE == null) {
      INSTANCE = new PayCalcSingleton();
    }
    return INSTANCE;
  }

  public float calcStandardPay(int basePay, int payMultiplier) {
    return basePay * payMultiplier;
  }

  public float calcDeliveryPay(
      int basePay, int payMultiplier, int deliveryAmount, float deliveryMultiplier) {
    return basePay * payMultiplier + deliveryAmount * deliveryMultiplier;
  }
}
