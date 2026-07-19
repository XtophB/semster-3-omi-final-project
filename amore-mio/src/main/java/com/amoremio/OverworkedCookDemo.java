package com.amoremio;

import com.amoremio.employee.EmployeeFactory;
import com.amoremio.employee.EmployeeManagerSingleton;
import com.amoremio.employee.Position;
import com.amoremio.ingredients.IngredientFactory;
import com.amoremio.ingredients.IngredientName;
import com.amoremio.order.Customer;
import com.amoremio.order.Order;
import com.amoremio.order.OrderManagerSingleton;
import com.amoremio.order.OrderedPizza;
import com.amoremio.order.PaymentType;
import com.amoremio.pizza.builders.AmericanBuilder;
import com.amoremio.store.Branch;
import com.amoremio.store.City;
import com.amoremio.store.Storage;
import com.amoremio.store.Supplier;
import java.util.List;

/**
 * Demonstrates the order-processing edge cases: overloaded cook when there are more pizzas than
 * cooks, both payment types, and the full order lifecycle observable notifications (CONFIRMED ->
 * PROCESSING -> DELIVERING -> DELIVERED).
 */
public class OverworkedCookDemo {

  /**
   * Main method.
   *
   * @param args command line args
   */
  public static void main(String[] args) {
    IngredientFactory ingredientFactory = new IngredientFactory();
    Supplier supplier = new Supplier(ingredientFactory);
    Storage storage = new Storage(supplier);

    for (IngredientName name : IngredientName.values()) {
      supplier.deliverIngredients(storage, name, 200, 7);
    }

    Branch branch = new Branch(storage, new AmericanBuilder(), City.KLAGENFURT);
    OrderManagerSingleton.getInstance().addBranch(City.KLAGENFURT, branch);

    EmployeeFactory employeeFactory = new EmployeeFactory();
    EmployeeManagerSingleton empManager = EmployeeManagerSingleton.getInstance();
    empManager.hireEmployee(branch, Position.COORDINATOR, employeeFactory);
    empManager.hireEmployee(branch, Position.KITCHEN_AID, employeeFactory);
    // 2 cooks, 3 pizzas -> one will be overworked
    empManager.hireEmployee(branch, Position.COOK, employeeFactory);
    empManager.hireEmployee(branch, Position.COOK, employeeFactory);
    empManager.hireEmployee(branch, Position.DELIVERY_BOY, employeeFactory);

    System.out.println("Overworked employee starts here");
    System.out.println("2 cooks but 3 pizzas, one cook will be overworked.");

    Customer customerOne = new Customer("CustomerOne");
    List<OrderedPizza> customerOnePizzas =
        List.of(
            new OrderedPizza(
                List.of(IngredientName.MOZZARELLA, IngredientName.SALAMI, IngredientName.ONION)),
            new OrderedPizza(
                List.of(IngredientName.MOZZARELLA, IngredientName.COTTO, IngredientName.CORN)),
            new OrderedPizza(
                List.of(
                    IngredientName.MOZZARELLA,
                    IngredientName.MUSHROOM,
                    IngredientName.CHILLI_FLAKE)));
    Order customerOneOrder = new Order(customerOnePizzas, PaymentType.CARD, City.KLAGENFURT);
    OrderManagerSingleton.getInstance().placeOrder(customerOneOrder, customerOne);
    System.out.println();

    Customer customerTwo = new Customer("CustomerTwo");
    List<OrderedPizza> customerTwoPizzas =
        List.of(
            new OrderedPizza(
                List.of(IngredientName.MOZZARELLA, IngredientName.PARMESAN, IngredientName.CRUDO)));
    Order customerTwoOrder = new Order(customerTwoPizzas, PaymentType.CASH, City.KLAGENFURT);
    OrderManagerSingleton.getInstance().placeOrder(customerTwoOrder, customerTwo);
    System.out.println();

    empManager.payEmployees();
  }
}
