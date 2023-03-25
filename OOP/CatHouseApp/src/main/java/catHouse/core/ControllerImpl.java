package catHouse.core;

import catHouse.entities.cat.BaseCat;
import catHouse.entities.cat.Cat;
import catHouse.entities.cat.LonghairCat;
import catHouse.entities.cat.ShorthairCat;
import catHouse.entities.houses.House;
import catHouse.entities.houses.LongHouse;
import catHouse.entities.houses.ShortHouse;
import catHouse.entities.toys.Ball;
import catHouse.entities.toys.Mouse;
import catHouse.entities.toys.Toy;
import catHouse.repositories.ToyRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static catHouse.common.ConstantMessages.*;
import static catHouse.common.ExceptionMessages.*;

public class ControllerImpl implements Controller{

    private ToyRepository toys;
    private Map<String,House> houses;

    public ControllerImpl() {
        toys = new ToyRepository();
        houses = new LinkedHashMap<>();
    }

    @Override
    public String addHouse(String type, String name) {
        House house;
        if(type.equals("ShortHouse")) {
            house = new ShortHouse(name);
        } else if(type.equals("LongHouse")) {
            house = new LongHouse(name);
        } else {
            throw new NullPointerException(INVALID_HOUSE_TYPE);
        }

        houses.put(name, house);
        return String.format(SUCCESSFULLY_ADDED_HOUSE_TYPE, type);
    }

    @Override
    public String buyToy(String type) {
        Toy toy;
        if(type.equals("Ball")) {
            toy = new Ball();
        } else if(type.equals("Mouse")) {
            toy = new Mouse();
        } else {
            throw new IllegalArgumentException(INVALID_TOY_TYPE);
        }

        toys.buyToy(toy);
        return String.format(SUCCESSFULLY_ADDED_TOY_TYPE, type);
    }

    @Override
    public String toyForHouse(String houseName, String toyType) {
        Toy toyToAdd = toys.findFirst(toyType);
        if(toyToAdd == null) {
            throw new IllegalArgumentException(String.format(NO_TOY_FOUND, toyType));
        }

        House house = houses.get(houseName);
        house.buyToy(toyToAdd);
        toys.removeToy(toyToAdd);
        return String.format(SUCCESSFULLY_ADDED_TOY_IN_HOUSE, toyType, houseName);
    }

    @Override
    public String addCat(String houseName, String catType, String catName, String catBreed, double price) {
        Cat cat;
        if(catType.equals("ShorthairCat")) {
            cat = new ShorthairCat(catName, catBreed, price);
        } else if(catType.equals("LonghairCat")) {
            cat = new LonghairCat(catName, catBreed, price);
        } else {
            throw new IllegalArgumentException(INVALID_CAT_TYPE);
        }
        House house = houses.get(houseName);
        String output;
        if(!catCanLiveInTheHouse(catType, house)) {
            output = UNSUITABLE_HOUSE;
        } else {
            house.addCat(cat);
            output = String.format(SUCCESSFULLY_ADDED_CAT_IN_HOUSE, catType, houseName);
        }
        return output;
    }

    private boolean catCanLiveInTheHouse(String catType, House house) {
        boolean canLive = house.getClass().getSimpleName().equals("ShortHouse") && catType.equals("ShorthairCat");
        if(!canLive ) {
            canLive = house.getClass().getSimpleName().equals("LongHouse") && catType.equals("LonghairCat");
        }
        return canLive;
    }

    @Override
    public String feedingCat(String houseName) {
        houses.get(houseName).getCats().forEach(Cat::eating);

        return String.format(FEEDING_CAT, houses.get(houseName).getCats().size());
    }

    @Override
    public String sumOfAll(String houseName) {
        House house = houses.get(houseName);
        double catsValues = house.getCats().stream().mapToDouble(Cat::getPrice).sum();
        double toysValues = house.getToys().stream().mapToDouble(Toy::getPrice).sum();
        double value = catsValues + toysValues;
        return String.format(VALUE_HOUSE, houseName, value);
    }

    @Override
    public String getStatistics() {
        return houses.values().stream()
                .map(House::getStatistics)
                .collect(Collectors.joining(System.lineSeparator()));
    }
}
