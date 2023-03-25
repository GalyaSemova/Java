package restaurant.core;

import restaurant.common.OutputMessages;
import restaurant.core.interfaces.Controller;
import restaurant.entities.drinks.Fresh;
import restaurant.entities.drinks.Smoothie;
import restaurant.entities.healthyFoods.Food;
import restaurant.entities.healthyFoods.Salad;
import restaurant.entities.healthyFoods.VeganBiscuits;
import restaurant.entities.healthyFoods.interfaces.HealthyFood;
import restaurant.entities.drinks.interfaces.Beverages;
import restaurant.entities.tables.InGarden;
import restaurant.entities.tables.Indoors;
import restaurant.entities.tables.interfaces.Table;
import restaurant.repositories.HealthFoodRepositoryImpl;
import restaurant.repositories.interfaces.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import static restaurant.common.ExceptionMessages.*;
import static restaurant.common.OutputMessages.*;

public class ControllerImpl implements Controller {


    private HealthFoodRepository<HealthyFood> healthFoodRepository;
    private BeverageRepository<Beverages> beverageRepository;
    private TableRepository<Table> tableRepository;
    private double totalMoneyCounter;

    public ControllerImpl(HealthFoodRepository<HealthyFood> healthFoodRepository,
                          BeverageRepository<Beverages> beverageRepository, TableRepository<Table> tableRepository) {
        this.healthFoodRepository = healthFoodRepository;
        this.beverageRepository = beverageRepository;
        this.tableRepository = tableRepository;
        totalMoneyCounter = 0;

    }

    @Override
    public String addHealthyFood(String type, double price, String name) {
        HealthyFood food = type.equals("Salad")
                ? new Salad(name,price)
                : new VeganBiscuits(name, price);

        HealthyFood previouslyAddedFood = this.healthFoodRepository.foodByName(name);
        if(previouslyAddedFood == null) {
            healthFoodRepository.add(food);
            return String.format(FOOD_ADDED, name);
        }
            throw new IllegalArgumentException(String.format(FOOD_EXIST, name));
    }

    @Override
    public String addBeverage(String type, int counter, String brand, String name){
        Beverages beverage = type.equals("Fresh")
                ? new Fresh(name, counter, brand)
                : new Smoothie(name, counter, brand);
        Beverages previouslyAddedDrink = this.beverageRepository.beverageByName(name, brand);
        if(previouslyAddedDrink == null) {
            beverageRepository.add(beverage);
            return String.format(BEVERAGE_ADDED, type, brand);
        }
        throw new IllegalArgumentException(String.format(BEVERAGE_EXIST, name));
    }

    @Override
    public String addTable(String type, int tableNumber, int capacity) {
        Table table = type.equals("Indoors")
                ? new Indoors(tableNumber, capacity)
                : new InGarden(tableNumber, capacity);
        Table previouslyAddedTable = this.tableRepository.byNumber(tableNumber);
        if(previouslyAddedTable == null) {
            this.tableRepository.add(table);
            return String.format(TABLE_ADDED, tableNumber);
        }
        throw new IllegalArgumentException(String.format(TABLE_EXIST, tableNumber));
    }

    @Override
    public String reserve(int numberOfPeople) {
        Collection<Table> tables = tableRepository.getAllEntities();

        Table table = tables.stream()
                .filter(t -> !t.isReservedTable() && t.getSize() >= numberOfPeople)
                .findFirst()
                .orElse(null);

        String message = String.format(RESERVATION_NOT_POSSIBLE,numberOfPeople);

        if(table != null) {
            table.reserve(numberOfPeople);
            message = String.format(TABLE_RESERVED, table.getTableNumber(),numberOfPeople);
        }
        return message;
    }

    @Override
    public String orderHealthyFood(int tableNumber, String healthyFoodName) {

        Table table = tableRepository.byNumber(tableNumber);

        if(table == null) {
            return String.format(WRONG_TABLE_NUMBER, tableNumber);
        }
        HealthyFood food = healthFoodRepository.foodByName(healthyFoodName);
        if(food == null) {
            return String.format(NONE_EXISTENT_FOOD, healthyFoodName);
        }

        table.orderHealthy(food);

        return String.format(FOOD_ORDER_SUCCESSFUL, healthyFoodName, tableNumber);
    }

    @Override
    public String orderBeverage(int tableNumber, String name, String brand) {

        Table table = tableRepository.byNumber(tableNumber);

        if(table == null) {
            return String.format(WRONG_TABLE_NUMBER, tableNumber);
        }

        Beverages beverages = beverageRepository.beverageByName(name,brand);
        if(beverages == null) {
            return  String.format(NON_EXISTENT_DRINK, name, brand);
        }

        table.orderBeverages(beverages);

        return String.format(BEVERAGE_ORDER_SUCCESSFUL,name, tableNumber);
    }

    @Override
    public String closedBill(int tableNumber) {
        Table table = tableRepository.byNumber(tableNumber);
        double bill = table.bill();
        totalMoneyCounter+=bill;
        table.clear();
        return String.format(BILL, tableNumber, bill);
    }


    @Override
    public String totalMoney() {
        return String.format(TOTAL_MONEY, totalMoneyCounter);
    }
}
