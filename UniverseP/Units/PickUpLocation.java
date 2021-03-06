package UniverseP.Units;

import java.util.Collection;

/**
 * Created by EG OLIVER RC on 9/5/2017.
 */
public class PickUpLocation extends ActionableLocation {

    public PickUpLocation(int x, int y, int passengerID) {
        super(x, y, passengerID);
    }

    public PickUpLocation (int x, int y, Collection<Integer> passengerIDs) {
        super(x, y, passengerIDs);
    }

    public PickUpLocation (Location newLocation, int passengerID) {
        super(newLocation.getX(), newLocation.getY(), passengerID);
    }

    public PickUpLocation (Location newLocation, Collection<Integer> passengerIDs) {
        super(x, y, passengerIDs);
    }

    public boolean isPickUpLocation() {
        return true;
    }
    public boolean isDropOffLocation() {
        return false;
    }

    @Override
    public String toString() {
        return "PickUp- PassID: " + this.getPassengerID() +  " (" + this.getX() + "," + this.getY() + ")";
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        PickUpLocation compared = (PickUpLocation) o;

        if (this.getX() != compared.getX()) {
            return false;
        }

        if (this.getY() != compared.getY()) {
            return false;
        }

        if (this.getPassengerID() != compared.getPassengerID()) {
            return false;
        }

        return true;
    }
}
