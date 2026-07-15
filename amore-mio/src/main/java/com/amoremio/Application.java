package com.amoremio;

import com.amoremio.employee.Cook;

public class Application {
  public static void main(String[] args) {
    final int basePay = 100;
    final float payMultiplier = 1.5f;

    Cook cook = new Cook(basePay, payMultiplier);
    cook.setBasePay(basePay);



  }
}
