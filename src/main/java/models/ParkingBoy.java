package models;

import exceptions.FailToParkException;
import exceptions.FailToPickException;

import java.util.UUID;

public class ParkingBoy {

    private ParkingLot[] parkingLots;

    public ParkingBoy(ParkingLot... parkingLots) {
        this.parkingLots = parkingLots;
    }

    public UUID park(Car car) throws FailToParkException {
        for (ParkingLot parkingLot : parkingLots) {
            if (parkingLot.hasEmptySpaces()) {
                return parkingLot.park(car);
            }
        }
        throw new FailToParkException();
    }

    public Car pick(UUID token) throws FailToPickException {
        for (ParkingLot parkingLot : parkingLots) {
            if (parkingLot.contains(token)) {
                return parkingLot.pick(token);
            }
        }
        throw new FailToPickException();
    }
}
