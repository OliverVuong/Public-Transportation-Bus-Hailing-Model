package UniverseP.PassengerFactory;

import UniverseP.Passenger;
import UniverseP.ScenarioDefinition;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by EG OLIVER RC on 1/27/2018.
 */
public class NormalTimeTableFactoryTest {

    @Test
    public void createDistribution() throws Exception {
        int length = 139;
        int height = 157;
        int numPass = 100;
        int numBuses = 10;
        int numTurns = 100;

        ScenarioDefinition myScenDef = new ScenarioDefinition( length, height, numPass, numBuses, numTurns);
        NormalLocation mySpawn = new NormalLocation(27, 32, 10);
        NormalLocation myDestination = new NormalLocation(109, 128, 15);
        NormalDistributionDefinition myDistDef = NormalDistributionDefinition.createNormalDistDef(mySpawn, myDestination, 30, 10);

        PassengerTimeTableFactory myFactory= new PassengerTimeTableFactory();

        PassengerTimeTable myTable = myFactory.createNormalDistribution(myScenDef, myDistDef);

        //check for correct number of passengers
        assertEquals(numPass, myTable.howManyPassengers());

        for ( int iterator : myTable.keySet() ) {
            for ( Passenger passIt : myTable.get(iterator) ) {

                assertTrue(passIt.getSpawn().getX() >= 0);
                assertTrue(passIt.getSpawn().getY() >= 0);
                assertTrue(passIt.getSpawn().getX() < length);
                assertTrue(passIt.getSpawn().getY() < height);

                assertTrue(passIt.getDestination().getX() >= 0);
                assertTrue(passIt.getDestination().getY() >= 0);
                assertTrue(passIt.getDestination().getX() < length);
                assertTrue(passIt.getDestination().getY() < height);

                assertTrue(passIt.getSpawnTurn() >= 0);
                assertTrue(passIt.getSpawnTurn() < numTurns);
            }
        }
    }

}