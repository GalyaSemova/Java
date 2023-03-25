package restaurant.entities.tables;

import restaurant.entities.drinks.BaseBeverage;
import restaurant.entities.drinks.interfaces.Beverages;
import restaurant.entities.healthyFoods.interfaces.HealthyFood;
import restaurant.entities.tables.interfaces.Table;

import java.util.*;
import java.util.stream.Collectors;

import static restaurant.common.ExceptionMessages.INVALID_NUMBER_OF_PEOPLE;
import static restaurant.common.ExceptionMessages.INVALID_TABLE_SIZE;

public abstract class BaseTable implements Table {

    private Collection<HealthyFood> healthyFood;
    private List<Beverages> beverages;
    private int number;
    private int size;
    private int numberOfPeople;
    private double pricePerPerson;
    private boolean isReservedTable;
    private double allPeople;


    protected BaseTable(int number, int size, double pricePerPerson) {
        setNumber(number);
        setSize(size);
        setPricePerPerson(pricePerPerson);
        healthyFood = new ArrayList<>();
        beverages = new ArrayList<>();

    }

    private void setNumber(int number) {
        this.number = number;
    }

    private void setPricePerPerson(double pricePerPerson) {
        this.pricePerPerson = pricePerPerson;
    }

    private void setSize(int size) {
        if(size <= 0) {
            throw new IllegalArgumentException(INVALID_TABLE_SIZE);
        }
        this.size = size;
    }

    @Override
    public int getTableNumber() {
        return number;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public int numberOfPeople() {
        return numberOfPeople;
    }

    @Override
    public double pricePerPerson() {
        return pricePerPerson;
    }

    @Override
    public boolean isReservedTable() {
        return false;
    }

    @Override
    public double allPeople() {
        return allPeople;
    }

    @Override
    public void reserve(int numberOfPeople) {
        if(numberOfPeople <= 0) {
            throw new IllegalArgumentException(INVALID_NUMBER_OF_PEOPLE);
        }
        this.numberOfPeople = numberOfPeople;
        isReservedTable = true;
    }

    @Override
    public void orderHealthy(HealthyFood food) {
        this.healthyFood.add(food);

    }

    @Override
    public void orderBeverages(Beverages beverages) {
        this.beverages.add(beverages);


    }

    @Override
    public double bill() {
        double foodPrice = healthyFood.stream().mapToDouble(HealthyFood::getPrice).sum();
        double beveragesPrice = beverages.stream().mapToDouble(Beverages::getPrice).sum();

        return foodPrice + beveragesPrice + (numberOfPeople * pricePerPerson);
    }

    @Override
    public void clear() {
        isReservedTable = false;
        numberOfPeople = 0;
        healthyFood.clear();
        beverages.clear();

    }

    @Override
    public String tableInformation() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Table - %d", number)).append(System.lineSeparator());
        sb.append(String.format("Size - %d", size)).append(System.lineSeparator());
        sb.append(String.format("Type - %s",getClass().getSimpleName())).append(System.lineSeparator());
        sb.append(String.format("All price - %.2f",pricePerPerson));
        return sb.toString();
    }
}
