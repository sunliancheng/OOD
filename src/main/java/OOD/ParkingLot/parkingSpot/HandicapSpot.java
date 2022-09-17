package OOD.ParkingLot.parkingSpot;

import OOD.ParkingLot.ParkingLot;

public class HandicapSpot extends ParkingSpot {

    public HandicapSpot(String parkingLocation) {
        this.hourlyRatio = 2;
        this.parkingLocation = parkingLocation;
    }

    @Override
    public String getVehicleType() {
        return "handicap";
    }
}
