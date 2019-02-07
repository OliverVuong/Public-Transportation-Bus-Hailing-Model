package UniverseP.ScenarioSimulation;

import UniverseP.Units.Trip;
import java.util.List;
import java.util.Optional;

/**
 * Created by EG OLIVER RC on 2/16/2018.
 */
public interface PassengerSource {
    Optional<List<Trip>> getPassengers(int time);
    ScenarioDefinition getScenarioDef();
}
