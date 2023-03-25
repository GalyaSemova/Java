package spaceStation.repositories;

import spaceStation.models.planets.Planet;

import java.util.*;

public class PlanetRepository implements Repository<Planet> {

    private Map<String,Planet> planets;

    public PlanetRepository() {
        planets =  new LinkedHashMap<>();
    }

    @Override
    public Collection<Planet> getModels() {
        return Collections.unmodifiableCollection(planets.values());
    }

    @Override
    public void add(Planet model) {
        planets.put(model.getName(), model);

    }

    @Override
    public boolean remove(Planet model) {
        return planets.remove(model.getName()) != null;
    }

    @Override
    public Planet findByName(String name) {
        return planets.get(name);
    }
}
