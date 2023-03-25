package christmasRaces.core;

import christmasRaces.core.interfaces.Controller;
import christmasRaces.entities.cars.BaseCar;
import christmasRaces.entities.cars.Car;
import christmasRaces.entities.cars.MuscleCar;
import christmasRaces.entities.cars.SportsCar;
import christmasRaces.entities.drivers.Driver;
import christmasRaces.entities.drivers.DriverImpl;
import christmasRaces.entities.races.Race;
import christmasRaces.entities.races.RaceImpl;
import christmasRaces.repositories.interfaces.CarRepository;
import christmasRaces.repositories.interfaces.DriverRepository;
import christmasRaces.repositories.interfaces.RaceRepository;
import christmasRaces.repositories.interfaces.Repository;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static christmasRaces.common.ExceptionMessages.*;
import static christmasRaces.common.OutputMessages.*;

public class ControllerImpl implements Controller {
    private DriverRepository driverRepository;
    private CarRepository carRepository;
    private RaceRepository raceRepository;

    public ControllerImpl(DriverRepository driverRepository, CarRepository carRepository, RaceRepository raceRepository) {
        this.driverRepository = new DriverRepository();
        this.carRepository = new CarRepository();
        this.raceRepository = new RaceRepository();
    }

    @Override
    public String createDriver(String driver) {
        Driver driver1 = new DriverImpl(driver);
        if(driverRepository.getAll().contains(driver1)) {
            throw new IllegalArgumentException(String.format(DRIVER_EXISTS, driver));
        }

        driverRepository.add(driver1);
        return String.format(DRIVER_CREATED, driver);
    }

    @Override
    public String createCar(String type, String model, int horsePower) {
        Car car = null;
        if(type.equals("MuscleCar")) {
            car = new MuscleCar(model, horsePower);
        } else if(type.equals("SportsCar")) {
            car = new SportsCar(model, horsePower);
        }

        if(carRepository.getByName(model) == null) {
            throw new IllegalArgumentException(String.format(CAR_EXISTS, model));
        }

        carRepository.add(car);
        return String.format(CAR_CREATED, car.getClass().getSimpleName(), model);
    }

    @Override
    public String addCarToDriver(String driverName, String carModel) {
        Driver driver = driverRepository.getAll().stream().filter(d -> d.getName().equals(driverName))
                .findFirst()
                .orElseThrow(() ->  new IllegalArgumentException(String.format(DRIVER_NOT_FOUND, driverName)));

        Car car = carRepository.getAll().stream()
                .findFirst()
                .orElseThrow(() ->  new IllegalArgumentException(String.format(CAR_NOT_FOUND, carModel)));

        driver.addCar(car);

        return String.format(CAR_ADDED, driverName, carModel);
    }

    @Override
    public String addDriverToRace(String raceName, String driverName) {

        if(raceRepository.getByName(raceName) != null) {
            Race race = raceRepository.getAll().stream().filter(r -> r.getName().equals(raceName)).findFirst().orElse(null);
        } else {
            throw new IllegalArgumentException(String.format(RACE_NOT_FOUND, raceName));
        }

        if(driverRepository.getByName(driverName) != null) {
            Driver driver = driverRepository.getByName(driverName);
        } else {
            throw new IllegalArgumentException(String.format(DRIVER_NOT_FOUND, driverName));
        }

       raceRepository.getByName(raceName).addDriver(driverRepository.getByName(driverName));


        return String.format(DRIVER_ADDED, driverName, raceName);
    }

    @Override
    public String startRace(String raceName) {
        if(raceRepository.getByName(raceName) == null) {
            throw new IllegalArgumentException(String.format(RACE_NOT_FOUND, raceName));
        }
            Race race = raceRepository.getByName(raceName);

        int participants = race.getDrivers().size();
        if(participants < 3) {
            throw new IllegalArgumentException(String.format(RACE_INVALID, raceName, 3));
        }

        List<Driver> winners = race.getDrivers().stream().sorted(Comparator.comparingInt(Driver::getNumberOfWins)
                        .reversed()).limit(3)
                .collect(Collectors.toList());

        StringBuilder sb = new StringBuilder();
        sb.append(String.format(DRIVER_FIRST_POSITION, winners.get(0).getName(), raceName)).append(System.lineSeparator());
        sb.append(String.format(DRIVER_SECOND_POSITION, winners.get(1).getName(), raceName)).append(System.lineSeparator());
        sb.append(String.format(DRIVER_THIRD_POSITION, winners.get(2).getName(), raceName)).append(System.lineSeparator());


        return sb.toString();
    }

    @Override
    public String createRace(String name, int laps) {
        if (raceRepository.getByName(name) != null) {
            throw new IllegalArgumentException(String.format(RACE_EXISTS, name));
        } else {
            Race race = new RaceImpl(name, laps);
        }

        return String.format(RACE_CREATED, name);
    }
}
