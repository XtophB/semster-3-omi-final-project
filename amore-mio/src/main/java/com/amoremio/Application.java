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
import com.amoremio.pizza.builders.AbstractPizzaBuilder;
import com.amoremio.pizza.builders.NeapolitanBuilder;
import com.amoremio.store.Branch;
import com.amoremio.store.City;
import com.amoremio.store.Storage;
import com.amoremio.store.Supplier;
import java.util.List;

/** A simulation class. */
public class Application {

  /**
   * The main method where the simulation happens.
   *
   * @param args the arguments if command line arguments are used
   */
  public static void main(String[] args) {
    // Dependency injections to allow easy mocking
    IngredientFactory ingredientFactory = new IngredientFactory();
    Supplier supplier = new Supplier(ingredientFactory);
    Storage storage = new Storage(supplier);

    for (IngredientName name : IngredientName.values()) {
      supplier.deliverIngredients(storage, name, 200, 7);
    }
    System.out.println("Storage stocked with all ingredients.");
    System.out.println();

    // Customizable pizza builder injections
    AbstractPizzaBuilder pizzaBuilder = new NeapolitanBuilder();
    Branch villachBranch = new Branch(storage, pizzaBuilder, City.VILLACH);

    // Singleton and factory for employees
    EmployeeFactory employeeFactory = new EmployeeFactory();
    EmployeeManagerSingleton empManager = EmployeeManagerSingleton.getInstance();

    empManager.hireEmployee(villachBranch, Position.COORDINATOR, employeeFactory);
    empManager.hireEmployee(villachBranch, Position.KITCHEN_AID, employeeFactory);
    empManager.hireEmployee(villachBranch, Position.COOK, employeeFactory); // Cook 1
    empManager.hireEmployee(villachBranch, Position.COOK, employeeFactory); // Cook 2
    empManager.hireEmployee(villachBranch, Position.DELIVERY_BOY, employeeFactory);
    System.out.println("Hired 1 coordinator, 1 kitchen aid, 2 cooks, 1 delivery boy.");
    System.out.println("Note, we can only process as many pizzas per order as we have cooks.");
    System.out.println();

    // Order manager singleton routes orders to correct branches
    OrderManagerSingleton.getInstance().addBranch(City.VILLACH, villachBranch);

    Customer personOne = new Customer("PersonOne");

    System.out.println("PersonOne places an order with 2 pizzas");
    List<OrderedPizza> personOnePizzas =
        List.of(
            new OrderedPizza(List.of(IngredientName.MOZZARELLA, IngredientName.SALAMI)),
            new OrderedPizza(
                List.of(IngredientName.MOZZARELLA, IngredientName.MUSHROOM, IngredientName.ONION)));
    Order personOneOrder = new Order(personOnePizzas, PaymentType.CARD, City.VILLACH);
    OrderManagerSingleton.getInstance().placeOrder(personOneOrder, personOne);
    System.out.println();


    Customer personTwo = new Customer("PersonTwo");

    System.out.println("PersonTwo places an order with 1 pizza");
    List<OrderedPizza> bobPizzas =
        List.of(
            new OrderedPizza(
                List.of(IngredientName.MOZZARELLA, IngredientName.COTTO, IngredientName.PARMESAN)));
    Order bobOrder = new Order(bobPizzas, PaymentType.CASH, City.VILLACH);
    OrderManagerSingleton.getInstance().placeOrder(bobOrder, personTwo);
    System.out.println();

    System.out.println("Paying employees.");
    empManager.payEmployees();
    System.out.println("All employees have been paid.");
  }
}
