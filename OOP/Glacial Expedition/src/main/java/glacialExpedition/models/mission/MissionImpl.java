package glacialExpedition.models.mission;

import glacialExpedition.models.explorers.Explorer;
import glacialExpedition.models.states.State;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class MissionImpl implements Mission{
    private State state;

    public MissionImpl(State state) {
        this.state = state;
    }

    @Override
    public void explore(State state, Collection<Explorer> explorers) {
        Collection<String> exhibitsInState = state.getExhibits();
        for (Explorer explorer : explorers) {
            while(explorer.canSearch() && exhibitsInState.iterator().hasNext()){
                explorer.search();
                String currentExhibit = exhibitsInState.iterator().next();
                explorer.getSuitcase().getExhibits().add(currentExhibit);
                exhibitsInState.remove(currentExhibit);
            }

        }


    }
}
