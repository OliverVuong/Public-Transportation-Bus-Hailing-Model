package UniverseP.TripFactory;

import UniverseP.Units.Trip;
import UniverseP.ScenarioSimulation.ScenarioDefinition;

import java.util.*;

/**
 * Creates a PassengerTimeTable where passengers are expected to be normally distributed in spawn location
 * over the grid, normally distributed in destination location over the grid, and normally distributed
 * in spawnTurn over the scenario total life cycle
 */

class NormalTimeTableFactory{

    private Random randNumGen;
    private int numTurns;
    private int gridLength;
    private int gridHeight;

    private int numPassengers;
    private NormalLocation spawn;
    private NormalLocation destination;
    private int spawnTimeAvg;
    private int spawnTimeStandardDeviation;


    NormalTimeTableFactory() {
        this.randNumGen = new Random();
    }

    private void updateDefinition(ScenarioDefinition myScenarioDef, NormalDistributionDefinition myPassengerDef) {
        this.numTurns = myScenarioDef.getNumTurns();
        this.gridLength = myScenarioDef.getGridLength();
        this.gridHeight = myScenarioDef.getGridHeight();
        this.numPassengers = myScenarioDef.getNumTrips();
        this.spawn = myPassengerDef.getSpawn();
        this.destination = myPassengerDef.getDestination();
        this.spawnTimeAvg = myPassengerDef.getSpawnTimeAvg();
        this.spawnTimeStandardDeviation = myPassengerDef.getSpawnTimeStandardDeviation();
    }


    TripTimeTable createTrips(ScenarioDefinition myScenarioDef,
                              NormalDistributionDefinition myPassengerDef ) {

        this.updateDefinition(myScenarioDef, myPassengerDef);

        TripTimeTable tripTimeTable = new TripTimeTable();
        Trip tripIterator;
        int spawnTurnIterator;

        for ( int i = 0; i < numPassengers; i++ ) {

            tripIterator = this.createNormalPassenger(i);
            spawnTurnIterator = tripIterator.getTimeRequested();

            if ( !tripTimeTable.containsKey(spawnTurnIterator) ){
                tripTimeTable.put(spawnTurnIterator, new ArrayList<>());
            }
            tripTimeTable.get(spawnTurnIterator).add(tripIterator);
        }

        return tripTimeTable;
    }

    private Trip createNormalPassenger(int ID ) {


        int spawnTurn = this.generateSpawnTurn();

        //generates the spawn according to a normal distribution and makes sure it's inbounds
        int x_spawn;
        int y_spawn;
        do {
            x_spawn = (int) Math.round( randNumGen.nextGaussian() * spawn.getStDev() + spawn.getX() );
            y_spawn = (int) Math.round( randNumGen.nextGaussian() * spawn.getStDev() + spawn.getY() );
        } while (!this.isInbounds(x_spawn, y_spawn));

        //generates the destination according to a normal distribution and makes sure it's inbounds and != spawn
        //since spawn == destination would be a nonsensical trip
        int x_dest;
        int y_dest;
        boolean validDestination;
        do {
            x_dest = (int) Math.round( randNumGen.nextGaussian() * destination.getStDev() + destination.getX() );
            y_dest = (int) Math.round( randNumGen.nextGaussian() * destination.getStDev() + destination.getY() );
            validDestination = this.isInbounds(x_dest, y_dest) &&
                                this.isDifferentLocation(x_spawn, y_spawn, x_dest, y_dest);
        } while (!validDestination);

        return new Trip(ID, x_spawn, y_spawn, x_dest, y_dest, spawnTurn);
    }

    private int generateSpawnTurn() {

        int spawnTurn;

        //if spawnTimeAvg is out of bounds (spawnTimeAvg==numTurns is out of bounds due to 0-indexing )or if
        // spawnTimeAvg is 0 or if spawnTimeStandardDev is 0 we generate spawnTurn by normal distribution
        if ( spawnTimeAvg <= 0 || spawnTimeAvg >= numTurns ||spawnTimeStandardDeviation == 0 ) {
            spawnTurn = randNumGen.nextInt( numTurns );
        } else {
            do {
                spawnTurn = (int) Math.round( randNumGen.nextGaussian() * spawnTimeStandardDeviation + spawnTimeAvg );
            } while (!this.isValidSpawnTurn(spawnTurn));
        }

        return spawnTurn;
    }

    private boolean isValidSpawnTurn(int spawnTurn) {
        return spawnTurn >= 0 && spawnTurn < numTurns;
    }

    private boolean isInbounds(int x_coordinate, int y_coordinate) {
        if ( x_coordinate < 0 || y_coordinate < 0 ) { return false; }
        if ( x_coordinate >= gridLength ) { return false; }
        if ( y_coordinate >= gridHeight ) { return false; }
        return true;
    }

    private boolean isDifferentLocation(int x_spawn, int y_spawn, int x_destination, int y_destination) {
        return x_spawn != x_destination || y_spawn != y_destination;
    }
}