package OOD.ParkingLot.parkingSpot;

import OOD.ParkingLot.ParkingLot;

public class CompactSpot extends ParkingSpot {

    public CompactSpot(String parkingLocation) {
        this.hourlyRatio = 5;
        this.parkingLocation = parkingLocation;
    }

    @Override
    public String getVehicleType() {
        return "compact";
    }
}
