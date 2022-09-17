package OOD.ParkingLot;

import OOD.ParkingLot.exceptions.InvalidTicketException;
import OOD.ParkingLot.parkingSpot.ParkingSpot;

import java.time.Duration;
import java.sql.Timestamp;
import java.time.Instant;

public class Ticket {

    private Timestamp enterTime;

    private Timestamp exitTime;

    private float chargeAmount;

    private ParkingSpot parkingSpot;

    public Ticket(ParkingSpot _parkingSpot) {
        this.chargeAmount = 0;
        this.parkingSpot = _parkingSpot;
        this.enterTime = Timestamp.from(Instant.now());
    }

    public void calculate() {
        if (this.exitTime != null) {
            throw new InvalidTicketException("");
        }
        this.exitTime = Timestamp.from(Instant.now());
        Duration duration = Duration.between(enterTime.toLocalDateTime(), exitTime.toLocalDateTime());
        this.chargeAmount = duration.toHours() * parkingSpot.hourlyRatio;
    }

    public float getChargeAmount() {
        return chargeAmount;
    }

    public ParkingSpot getParkingSpot() {
        return parkingSpot;
    }

    public Timestamp getEnterTime() {
        return enterTime;
    }

    public Timestamp getExitTime() {
        return exitTime;
    }

    public void setChargeAmount(float chargeAmount) {
        this.chargeAmount = chargeAmount;
    }

    public void setEnterTime(Timestamp enterTime) {
        this.enterTime = enterTime;
    }

    public void setExitTime(Timestamp exitTime) {
        this.exitTime = exitTime;
    }

    public void setParkingSpot(ParkingSpot parkingSpot) {
        this.parkingSpot = parkingSpot;
    }
}
