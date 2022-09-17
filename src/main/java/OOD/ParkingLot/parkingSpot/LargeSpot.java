package OOD.ParkingLot.parkingSpot;

import OOD.ParkingLot.ParkingLot;

public class LargeSpot extends ParkingSpot {

    public LargeSpot(String parkingLocation) {
        this.hourlyRatio = 10;
        this.parkingLocation = parkingLocation;
    }

    @Override
    public String getVehicleType() {
        return "large";
    }
}
