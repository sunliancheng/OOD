import OOD.ParkingLot.ParkingLot;
import OOD.ParkingLot.Ticket;

import static java.lang.Thread.sleep;

public class main {

    public static void main(String[] args) {
        ParkingLot parkingLot = new ParkingLot(20, 50, 100);
        Ticket lastTicket = null;
        try {
            Ticket ticket = parkingLot.park("large");
            sleep(3);
            parkingLot.exit(ticket);
            for (int i = 0; i < 20; ++i) {
                lastTicket = parkingLot.park("handicap");
            }
            parkingLot.exit(lastTicket);
            Ticket tt = parkingLot.park("handicap");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}