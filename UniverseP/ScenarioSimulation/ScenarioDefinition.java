package UniverseP.ScenarioSimulation;

import UniverseP.Units.ActionableLocation;
import UniverseP.Units.Location;

public class ScenarioDefinition {

    private int gridLength;
    private int gridHeight;

    private int numTrips;

    private int numBuses;
    private int busCapacity;
    private int numTurns;


    public ScenarioDefinition(int gridLength, int gridHeight, int numTrips, int numBuses, int busCapacity, int numTurns){

        this.gridLength = gridLength;   //this gives the grid 1000 spaces should give good density
        this.gridHeight = gridHeight;   //if there is 100 buses that's 1/10 of spaces with a bus
        this.numTrips = numTrips;
        this.numBuses = numBuses;                //1, 25, 50, 75, 100, 200
        this.busCapacity = busCapacity;
        this.numTurns = numTurns;

    }

    public static int getDistance(Location a, Location b){
        return Math.abs( a.getX() - b.getX() ) + Math.abs( a.getY() - b.getY() );
    }

    public static int getDistance(ActionableLocation a, ActionableLocation b){
        return Math.abs( a.getX() - b.getX() ) + Math.abs( a.getY() - b.getY() );
    }

    //Getters
    public int getGridLength() {
        return gridLength;
    }
    public int getGridHeight() {
        return gridHeight;
    }
    public int getNumTurns() {
        return numTurns;
    }
    public int getNumTrips() { return numTrips; }
    public int getNumBuses() { return numBuses; }
}
