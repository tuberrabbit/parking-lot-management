package models;

import exceptions.NoAvailableParkingSpace;
import exceptions.NoSuchCarInParkingLot;

import java.util.List;
import java.util.UUID;

public class ParkingBoy {
    private final List<ParkingLot> parkingLots;

    public ParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public UUID park(Car car) throws NoAvailableParkingSpace {
        ParkingLot idealParkingLot = getIdealParkingLot(parkingLots);
        if (idealParkingLot == null) {
            throw new NoAvailableParkingSpace();
        }
        return idealParkingLot.park(car);
    }

    private ParkingLot getIdealParkingLot(List<ParkingLot> parkingLots) {
        ParkingLot idealParkingLot = null;
        for (ParkingLot parkingLot : parkingLots) {
            if (parkingLot.getAvailableSpaces() > 0) {
                idealParkingLot = parkingLot;
                break;
            }
        }
        return idealParkingLot;
    }

    public Car pick(UUID ticket) throws NoSuchCarInParkingLot {
        for (ParkingLot parkingLot : parkingLots) {
            try {
                return parkingLot.pick(ticket);
            } catch (NoSuchCarInParkingLot noSuchCarInParkingLot) {
                noSuchCarInParkingLot.printStackTrace();
            }
        }
        throw new NoSuchCarInParkingLot();
    }
}
