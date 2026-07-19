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
import com.amoremio.pizza.builders.NeapolitanBuilder;
import com.amoremio.store.Branch;
import com.amoremio.store.City;
import com.amoremio.store.Storage;
import com.amoremio.store.Supplier;
import java.util.List;

/**
 * Demonstrates the storage management specs: stock of storage, exp date tracking, shortage
 * detection and resupply, delay of order.
 *
 * <p>JALAPENO is understock with 5. When an order requires JALAPENO, coordinator should detect it,
 * delay the order, and restock.
 */
public class StorageShortageDemo {

  /**
   * Main method.
   *
   * @param args command args
   */
  public static void main(String[] args) {
    IngredientFactory ingredientFactory = new IngredientFactory();
    Supplier supplier = new Supplier(ingredientFactory);
    Storage storage = new Storage(supplier);

    for (IngredientName name : IngredientName.values()) {
      supplier.deliverIngredients(storage, name, 200, 7);
    }

    // set JALAPENO to 5
    storage.getInventory().get(IngredientName.JALAPENO).clear();
    supplier.deliverIngredients(storage, IngredientName.JALAPENO, 5, 7);

    Branch branch = new Branch(storage, new NeapolitanBuilder(), City.VILLACH);
    OrderManagerSingleton.getInstance().addBranch(City.VILLACH, branch);

    EmployeeFactory employeeFactory = new EmployeeFactory();
    EmployeeManagerSingleton empManager = EmployeeManagerSingleton.getInstance();
    empManager.hireEmployee(branch, Position.COORDINATOR, employeeFactory);
    empManager.hireEmployee(branch, Position.KITCHEN_AID, employeeFactory);
    empManager.hireEmployee(branch, Position.COOK, employeeFactory);
    empManager.hireEmployee(branch, Position.COOK, employeeFactory);
    empManager.hireEmployee(branch, Position.DELIVERY_BOY, employeeFactory);


    Customer customer = new Customer("Customer");
    List<OrderedPizza> pizzas =
        List.of(
            new OrderedPizza(
                List.of(
                    IngredientName.MOZZARELLA, IngredientName.JALAPENO, IngredientName.CHICKEN)));
    Order order = new Order(pizzas, PaymentType.CARD, City.VILLACH);
    OrderManagerSingleton.getInstance().placeOrder(order, customer);
  }
}
