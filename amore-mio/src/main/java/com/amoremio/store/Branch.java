package com.amoremio.store;

import com.amoremio.employee.Employee;
import com.amoremio.ingredients.IngredientFactory;
import java.util.List;

public class Branch {
  List<Employee> employees;
  Storage storage;
  Supplier supplier;
  IngredientFactory ingredientFactory;
  City city;

  Branch(Storage storage, Supplier supplier, IngredientFactory ingredientFactory, City city) {
    this.storage = storage;
    this.supplier = supplier;
    this.ingredientFactory = ingredientFactory;
    this.city = city;
  }

  public void addEmployee(Employee employee) {
    employees.add(employee);
  }

  public void removeEmployee(Employee employee) {
    employees.remove(employee);
  }
}
