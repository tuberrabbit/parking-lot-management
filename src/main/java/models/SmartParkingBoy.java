package models;

import exceptions.FailToParkException;
import exceptions.FailToPickException;

import java.util.UUID;

public class SmartParkingBoy {
    private final ParkingLot[] parkingLots;

    public SmartParkingBoy(ParkingLot... parkingLots) {
        this.parkingLots = parkingLots;
    }

    public UUID park(Car car) throws FailToParkException {
        ParkingLot moreEmptySpaces = getMoreEmptySpacesParkingLot(parkingLots);
        if (!moreEmptySpaces.hasEmptySpaces()) {
            throw new FailToParkException();
        }
        return moreEmptySpaces.park(car);
    }

    private ParkingLot getMoreEmptySpacesParkingLot(ParkingLot[] parkingLots) {
        ParkingLot moreEmptySpaces = parkingLots[0];
        for (ParkingLot parkingLot : parkingLots) {
            if (moreEmptySpaces.getEmptySpaces() < parkingLot.getEmptySpaces()) {
                moreEmptySpaces = parkingLot;
            }
        }
        return moreEmptySpaces;
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
