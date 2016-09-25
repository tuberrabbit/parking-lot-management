package models;

import exceptions.NoAvailableParkingSpace;

import java.util.List;
import java.util.UUID;

public class SmartParkingBoy extends ParkingBoy {
    private final List<ParkingLot> parkingLots;

    public SmartParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
        this.parkingLots = parkingLots;
    }

    public UUID park(Car car) throws NoAvailableParkingSpace {
        ParkingLot parkingLotWithMaxAvailableSpaces = parkingLots.get(0);
        for (ParkingLot parkingLot : parkingLots) {
            if (parkingLot.getAvailableSpaces() > parkingLotWithMaxAvailableSpaces.getAvailableSpaces()) {
                parkingLotWithMaxAvailableSpaces = parkingLot;
            }
        }
        if (parkingLotWithMaxAvailableSpaces == null) {
            throw new NoAvailableParkingSpace();
        }
        return parkingLotWithMaxAvailableSpaces.park(car);
    }

}
