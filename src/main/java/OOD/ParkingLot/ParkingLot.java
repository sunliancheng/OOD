package OOD.ParkingLot;

import OOD.ParkingLot.exceptions.InvalidTicketException;
import OOD.ParkingLot.exceptions.NotSupportedVehicleTypeException;
import OOD.ParkingLot.exceptions.ParkingFullException;
import OOD.ParkingLot.parkingSpot.CompactSpot;
import OOD.ParkingLot.parkingSpot.HandicapSpot;
import OOD.ParkingLot.parkingSpot.LargeSpot;
import OOD.ParkingLot.parkingSpot.ParkingSpot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingLot {

    private int handicapSpotCapacity;

    private int largeSpotCapacity;

    private int compactSpotCapacity;

    private List<LargeSpot> largeSpotList;

    private List<CompactSpot> compactSpotList;

    private List<HandicapSpot> handicapSpotList;

    private Map<String /* parkingLocation */, ParkingSpot> largeSpotMap;

    private Map<String /* parkingLocation */, ParkingSpot> compactSpotMap;

    private Map<String /* parkingLocation */, ParkingSpot> handicapSpotMap;

    private Map<String /* parkingLocation */, Ticket> ticketMap;

    public ParkingLot(int _handicapSpotCapacity, int _largeSpotCapacity ,int _compactSpotCapacity) {
        handicapSpotCapacity = _handicapSpotCapacity;
        largeSpotCapacity = _largeSpotCapacity;
        compactSpotCapacity = _compactSpotCapacity;

        largeSpotList = new ArrayList<>();
        compactSpotList = new ArrayList<>();
        handicapSpotList = new ArrayList<>();

        for (int i = 0; i < handicapSpotCapacity; ++i) {
            HandicapSpot handicapSpot = new HandicapSpot("handicapSpot: " + String.valueOf(i));
            handicapSpotList.add(handicapSpot);
        }

        for (int i = 0; i < largeSpotCapacity; ++i) {
            LargeSpot largeSpot = new LargeSpot("handicapSpot: " + String.valueOf(i));
            largeSpotList.add(largeSpot);
        }

        for (int i = 0; i < compactSpotCapacity; ++i) {
            CompactSpot compactSpot = new CompactSpot("handicapSpot: " + String.valueOf(i));
            compactSpotList.add(compactSpot);
        }

        largeSpotMap = new HashMap<>();
        compactSpotMap= new HashMap<>();
        handicapSpotMap = new HashMap<>();
        ticketMap = new HashMap<>();
    }

    public Ticket park(String vehicleType) throws Exception {
        if (checkFull(vehicleType)) {
            throw new ParkingFullException(vehicleType + " full");
        }

        ParkingSpot parkingSpot = getParkingSpot(vehicleType);

        Ticket ticket = new Ticket(parkingSpot);
        ticketMap.put(parkingSpot.parkingLocation, ticket);

        System.out.println("Welcome to the parking lot, your enter time is: " + ticket.getEnterTime());
        System.out.println("Your vehicle type is: " + vehicleType);
        return ticket;
    }

    private ParkingSpot getParkingSpot(String vehicleType) {
        ParkingSpot parkingSpot = null;
        if (vehicleType.equals("large")) {
            parkingSpot = largeSpotList.remove(0);
            largeSpotMap.put(parkingSpot.parkingLocation, parkingSpot);
        } else if (vehicleType.equals("compact")) {
            parkingSpot = compactSpotList.remove(0);
            compactSpotMap.put(parkingSpot.parkingLocation, parkingSpot);
        } else if (vehicleType.equals("handicap")) {
            parkingSpot = handicapSpotList.remove(0);
            handicapSpotMap.put(parkingSpot.parkingLocation, parkingSpot);
        } else {
            throw new NotSupportedVehicleTypeException("not supported vehicle type");
        }
        return parkingSpot;
    }

    private boolean checkFull(String vehicleType) throws Exception {
        if (vehicleType.equals("large")) {
            return largeSpotList.size() <= 0;
        } else if (vehicleType.equals("compact")) {
            return compactSpotList.size() <= 0;
        } else if (vehicleType.equals("handicap")) {
            return handicapSpotList.size() <= 0;
        } else {
            throw new NotSupportedVehicleTypeException("not supported vehicle type");
        }
    }

    public void exit(Ticket ticket) {
        String location = ticket.getParkingSpot().parkingLocation;
        if (!ticketMap.containsKey(location)) {
            throw new InvalidTicketException("");
        }
        ticket.calculate();
        ticketMap.remove(location);
        String type = ticket.getParkingSpot().getVehicleType();
        if (type.equals("large")) {
            largeSpotMap.remove(location);
            largeSpotList.add((LargeSpot) ticket.getParkingSpot());
        } else if (type.equals("compact")) {
            compactSpotMap.remove(location);
            compactSpotList.add((CompactSpot) ticket.getParkingSpot());
        } else {
            handicapSpotMap.remove(location);
            handicapSpotList.add((HandicapSpot) ticket.getParkingSpot());
        }

        System.out.println("your car is parked from " + ticket.getEnterTime() + " to " + ticket.getExitTime());
        System.out.println("Your fee is " + ticket.getChargeAmount());
    }

}
