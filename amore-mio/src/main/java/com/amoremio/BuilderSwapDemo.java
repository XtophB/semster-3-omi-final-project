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
import com.amoremio.pizza.factories.AmericanFactory;
import com.amoremio.pizza.factories.BuilderFactoryInterface;
import com.amoremio.pizza.factories.NeapolitanFactory;
import com.amoremio.pizza.factories.SouthEastEuFactory;
import com.amoremio.pizza.factories.StandardFactory;
import com.amoremio.store.Branch;
import com.amoremio.store.City;
import com.amoremio.store.Storage;
import com.amoremio.store.Supplier;
import java.util.List;

/**
 * Demonstrates the pizza variant spec: different pizza styles (Neapolitan, American, Southeast
 * European, Standard) created via the builder factory pattern, each branch getting its own style.
 * Also shows builder switching when no orders are active.
 */
public class BuilderSwapDemo {

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

    BuilderFactoryInterface neapolitanFactory = new NeapolitanFactory();
    BuilderFactoryInterface americanFactory = new AmericanFactory();
    BuilderFactoryInterface southEastEuFactory = new SouthEastEuFactory();

    Branch villachBranch =
        new Branch(storage, neapolitanFactory.createBuilder(), City.VILLACH);
    Branch klagenfurtBranch =
        new Branch(storage, americanFactory.createBuilder(), City.KLAGENFURT);
    Branch grazBranch =
        new Branch(storage, southEastEuFactory.createBuilder(), City.GRAZ);

    OrderManagerSingleton.getInstance().addBranch(City.VILLACH, villachBranch);
    OrderManagerSingleton.getInstance().addBranch(City.KLAGENFURT, klagenfurtBranch);
    OrderManagerSingleton.getInstance().addBranch(City.GRAZ, grazBranch);

    EmployeeFactory employeeFactory = new EmployeeFactory();
    EmployeeManagerSingleton empManager = EmployeeManagerSingleton.getInstance();

    hireStaff(empManager, employeeFactory, villachBranch);
    hireStaff(empManager, employeeFactory, klagenfurtBranch);
    hireStaff(empManager, employeeFactory, grazBranch);


    Customer customerOne = new Customer("CustomerOne");
    List<OrderedPizza> customerOnePizzas =
        List.of(
            new OrderedPizza(
                List.of(IngredientName.MOZZARELLA, IngredientName.SALAMI, IngredientName.ONION)));
    Order customerOneOrder = new Order(customerOnePizzas, PaymentType.CARD, City.VILLACH);
    OrderManagerSingleton.getInstance().placeOrder(customerOneOrder, customerOne);
    System.out.println();

    Customer customerTwo = new Customer("CustomerTwo");
    List<OrderedPizza> customerTwoPizzas =
        List.of(
            new OrderedPizza(
                List.of(IngredientName.MOZZARELLA, IngredientName.COTTO, IngredientName.CORN)));
    Order customerTwoOrder = new Order(customerTwoPizzas, PaymentType.CASH, City.KLAGENFURT);
    OrderManagerSingleton.getInstance().placeOrder(customerTwoOrder, customerTwo);
    System.out.println();

    Customer customerThree = new Customer("CustomerThree");
    List<OrderedPizza> customerThreePizzas =
        List.of(
            new OrderedPizza(
                List.of(
                    IngredientName.MOZZARELLA, IngredientName.MUSHROOM, IngredientName.PARMESAN)));
    Order customerThreeOrder = new Order(customerThreePizzas, PaymentType.CARD, City.GRAZ);
    OrderManagerSingleton.getInstance().placeOrder(customerThreeOrder, customerThree);
    System.out.println();

    // switch builder via setter, spec mandates we can switch on different days
    BuilderFactoryInterface standardFactory = new StandardFactory();
    villachBranch.setPizzaBuilder(standardFactory.createBuilder());
    System.out.println("Villach switched to Standard builder");

    Customer customerFour = new Customer("CustomerFour");
    List<OrderedPizza> customerFourPizzas =
        List.of(
            new OrderedPizza(
                List.of(IngredientName.MOZZARELLA, IngredientName.CHICKEN, IngredientName.CORN)));
    Order customerFourOrder = new Order(customerFourPizzas, PaymentType.CASH, City.VILLACH);
    OrderManagerSingleton.getInstance().placeOrder(customerFourOrder, customerFour);
  }

  private static void hireStaff(
      EmployeeManagerSingleton empManager, EmployeeFactory factory, Branch branch) {
    empManager.hireEmployee(branch, Position.COORDINATOR, factory);
    empManager.hireEmployee(branch, Position.KITCHEN_AID, factory);
    empManager.hireEmployee(branch, Position.COOK, factory);
    empManager.hireEmployee(branch, Position.COOK, factory);
    empManager.hireEmployee(branch, Position.DELIVERY_BOY, factory);
  }
}
