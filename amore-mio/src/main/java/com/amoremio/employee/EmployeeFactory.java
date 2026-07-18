package com.amoremio.employee;

import com.amoremio.employee.roles.Cook;
import com.amoremio.employee.roles.Coordinator;
import com.amoremio.employee.roles.DeliveryBoy;
import com.amoremio.employee.roles.KitchenAid;

/**
 * Implementation of a factory pattern, to create all types of employees. This class acts as a
 * central location to define all employee pay attributes and we create the employees from here.
 */
public class EmployeeFactory {

  private static final int COOK_PAY = 12;
  private static final float COOK_MULTIPLIER = 1.2f;

  private static final int COORDINATOR_PAY = 14;
  private static final float COORDINATOR_MULTIPLIER = 1.0f;

  private static final int DELIVERY_BOY_PAY = 9;
  private static final float DELIVERY_BOY_MULTIPLIER = 1.0f;
  private static final float DELIVERY_BONUS_MULTIPLIER = 0.5f;

  private static final int KITCHEN_AID_PAY = 10;
  private static final float KITCHEN_AID_MULTIPLIER = 1.1f;

  /**
   * Creates an employee based on the position passed in.
   *
   * @param position the role the employee should take on
   * @return the employee object with the specified role
   */
  public Employee create(Position position) {
    return switch (position) {
      case COOK -> new Cook(COOK_PAY, COOK_MULTIPLIER);
      case COORDINATOR -> new Coordinator(COORDINATOR_PAY, COORDINATOR_MULTIPLIER);
      case DELIVERY_BOY ->
          new DeliveryBoy(DELIVERY_BOY_PAY, DELIVERY_BOY_MULTIPLIER, DELIVERY_BONUS_MULTIPLIER);
      case KITCHEN_AID -> new KitchenAid(KITCHEN_AID_PAY, KITCHEN_AID_MULTIPLIER);
    };
  }
}
