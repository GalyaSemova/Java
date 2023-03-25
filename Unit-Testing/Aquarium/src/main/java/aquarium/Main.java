package aquarium;

import aquarium.core.Controller;
import aquarium.core.ControllerImpl;
import aquarium.core.EngineImpl;
import aquarium.repositories.DecorationRepository;

public class Main {
    public static void main(String[] args) {

        DecorationRepository decorationRepository = new DecorationRepository();
        Controller controller = new ControllerImpl();
        EngineImpl engine = new EngineImpl();
        engine.run();

    }
}
