package glacialExpedition.core;

import glacialExpedition.models.explorers.AnimalExplorer;
import glacialExpedition.models.explorers.Explorer;
import glacialExpedition.models.explorers.GlacierExplorer;
import glacialExpedition.models.explorers.NaturalExplorer;
import glacialExpedition.models.mission.Mission;
import glacialExpedition.models.mission.MissionImpl;
import glacialExpedition.models.states.State;
import glacialExpedition.models.states.StateImpl;
import glacialExpedition.repositories.ExplorerRepository;
import glacialExpedition.repositories.StateRepository;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static glacialExpedition.common.ConstantMessages.*;
import static glacialExpedition.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {
    private ExplorerRepository explorerRepository;
    private StateRepository stateRepository;
    private int stateCount;

    public ControllerImpl() {
        explorerRepository = new ExplorerRepository();
        stateRepository = new StateRepository();
        stateCount = 0;
    }

    @Override
    public String addExplorer(String type, String explorerName) {
        Explorer explorer;
        if(type.equals("AnimalExplorer")) {
            explorer = new AnimalExplorer(explorerName);
        } else if (type.equals("GlacierExplorer")) {
            explorer = new GlacierExplorer(explorerName);
        } else if (type.equals("NaturalExplorer")) {
            explorer = new NaturalExplorer(explorerName);
        } else {
            throw new IllegalArgumentException(EXPLORER_INVALID_TYPE);
        }
        explorerRepository.add(explorer);

        return String.format(EXPLORER_ADDED, type, explorerName);
    }

    @Override
    public String addState(String stateName, String... exhibits) {
        State state = new StateImpl(stateName);
        for (String exhibit : exhibits) {
            state.getExhibits().add(exhibit);

        }
        stateRepository.add(state);

        return String.format(STATE_ADDED, stateName);
    }

    @Override
    public String retireExplorer(String explorerName) {
       Explorer explorer = explorerRepository.byName(explorerName);
       if(explorer == null) {
           throw new IllegalArgumentException(String.format(EXPLORER_DOES_NOT_EXIST, explorerName));
       }
       explorerRepository.remove(explorer);

        return String.format(EXPLORER_RETIRED, explorerName);
    }

    @Override
    public String exploreState(String stateName) {
        List<Explorer> validExplorers = explorerRepository.getCollection().stream()
                .filter(ex -> ex.getEnergy() > 50)
                .collect(Collectors.toList());
        if( validExplorers.isEmpty()) {
            throw new IllegalArgumentException(STATE_EXPLORERS_DOES_NOT_EXISTS);
        }
        Mission mission = new MissionImpl(stateRepository.byName(stateName));
        mission.explore(stateRepository.byName(stateName), validExplorers);

        long retiredExplorers = validExplorers.stream()
                .filter(e -> e.getEnergy() == 0)
                .count();

        stateCount++;


        return String.format(STATE_EXPLORER, stateName, retiredExplorers);
    }

    @Override
    public String finalResult() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(FINAL_STATE_EXPLORED, stateCount)).append(System.lineSeparator());
        sb.append(FINAL_EXPLORER_INFO).append(System.lineSeparator());
        for (Explorer explorer : explorerRepository.getCollection()) {
            sb.append(String.format(FINAL_EXPLORER_NAME, explorer.getName())).append(System.lineSeparator());
            sb.append(String.format(FINAL_EXPLORER_ENERGY, explorer.getEnergy())).append(System.lineSeparator());
            Collection<String> explorerExhibits = explorer.getSuitcase().getExhibits();
            if(explorerExhibits.isEmpty()){
                sb.append(String.format(FINAL_EXPLORER_SUITCASE_EXHIBITS, "None"));
            } else {
                String allExhibits = String.join(FINAL_EXPLORER_SUITCASE_EXHIBITS_DELIMITER, explorerExhibits);
                sb.append(String.format(FINAL_EXPLORER_SUITCASE_EXHIBITS, allExhibits));
            }
            sb.append(System.lineSeparator());
        }

        return sb.toString().trim();
    }
}
