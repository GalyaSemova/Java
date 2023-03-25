package aquarium.entities.aquariums;

import aquarium.entities.decorations.Decoration;
import aquarium.entities.fish.Fish;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import static aquarium.common.ConstantMessages.NOT_ENOUGH_CAPACITY;
import static aquarium.common.ExceptionMessages.AQUARIUM_NAME_NULL_OR_EMPTY;

public abstract class BaseAquarium implements Aquarium{
    private String name;
    private int capacity;
    private Collection<Decoration> decorations;
    private Collection<Fish> fish;

    protected BaseAquarium(String name, int capacity) {
        setName(name);
        this.capacity = capacity;
        decorations = new ArrayList<>();
        fish = new ArrayList<>();
    }

    private void setName(String name) {
        if(name == null || name.trim().isEmpty()) {
            throw new NullPointerException(AQUARIUM_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    @Override
    public int calculateComfort() {
        return decorations.stream().mapToInt(Decoration::getComfort).sum();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void addFish(Fish f) {
        if(capacity == fish.size()) {
            throw new IllegalStateException(NOT_ENOUGH_CAPACITY);
        }

        fish.add(f);

    }

    @Override
    public void removeFish(Fish f) {
        fish.remove(f);
    }

    @Override
    public void addDecoration(Decoration decoration) {
        decorations.add(decoration);

    }

    @Override
    public void feed() {
        fish.stream().forEach(Fish::eat);
    }

    @Override
    public String getInfo() {
        String fishReport = fish.isEmpty()
                ?"none"
                :fish.stream()
                .map(Fish::getName)
                .collect(Collectors.joining(" "));

        return String.format("%s (%s):%n" +
                        "Fish: %s%n" +
                        "Decorations: %d%n" +
                "Comfort: %d"
                ,name, getClass().getSimpleName()
                ,fishReport
                ,decorations.size()
                ,calculateComfort());
    }

    @Override
    public Collection<Fish> getFish() {
        return fish;
    }

    @Override
    public Collection<Decoration> getDecorations() {
        return decorations;
    }

    @Override
    public int getCapacity() {
        return capacity;
    }
}
