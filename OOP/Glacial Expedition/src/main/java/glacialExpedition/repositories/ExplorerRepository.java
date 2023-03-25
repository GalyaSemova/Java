package glacialExpedition.repositories;

import glacialExpedition.models.explorers.Explorer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class ExplorerRepository implements Repository<Explorer>{
    private Collection<Explorer> explorers;

    public ExplorerRepository() {
        explorers = new ArrayList<>();
    }

    @Override
    public Collection<Explorer> getCollection() {
        return Collections.unmodifiableCollection(explorers);
    }

    @Override
    public void add(Explorer explorer) {
        explorers.add(explorer);

    }

    @Override
    public boolean remove(Explorer explorer) {
        return explorers.remove(explorer);
    }

    @Override
    public Explorer byName(String name) {
        return explorers.stream()
                .filter(e -> e.getName().equals(name))
                .findFirst()
                .orElse(null);
    }
}
