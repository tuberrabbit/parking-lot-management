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
        ParkingLot idealParkingLot = getIdealParkingLot(parkingLots);
        if (idealParkingLot == null) {
            throw new NoAvailableParkingSpace();
        }
        return idealParkingLot.park(car);
    }

    private ParkingLot getIdealParkingLot(List<ParkingLot> parkingLots) {
        ParkingLot parkingLotWithMaxAvailableSpaces = parkingLots.get(0);
        for (ParkingLot parkingLot : parkingLots) {
            if (parkingLot.getAvailableSpaces() > parkingLotWithMaxAvailableSpaces.getAvailableSpaces()) {
                parkingLotWithMaxAvailableSpaces = parkingLot;
            }
        }
        return parkingLotWithMaxAvailableSpaces;
    }

}
