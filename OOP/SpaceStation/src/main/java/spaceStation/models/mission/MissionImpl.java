package spaceStation.models.mission;

import spaceStation.models.astronauts.Astronaut;
import spaceStation.models.planets.Planet;

import java.util.Collection;

public class MissionImpl implements Mission{

    @Override
    public void explore(Planet planet, Collection<Astronaut> astronauts) {
        Collection<String> itemsToExplore = planet.getItems();
        for (Astronaut astronaut : astronauts) {
            while(astronaut.canBreath() && itemsToExplore.iterator().hasNext()) {
                astronaut.breath();
                String currentItem = itemsToExplore.iterator().next();
                astronaut.getBag().getItems().add(currentItem);
                itemsToExplore.remove(currentItem);

            }

        }

    }
}
