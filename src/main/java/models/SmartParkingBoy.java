package models;

import exceptions.FailToParkException;
import exceptions.FailToPickException;
import strategies.IdealParkingLotStrategy;
import strategies.MoreEmptySpacesStrategy;

import java.util.UUID;

public class SmartParkingBoy {
    private final ParkingLot[] parkingLots;
    private final IdealParkingLotStrategy strategy;

    public SmartParkingBoy(ParkingLot... parkingLots) {
        this.parkingLots = parkingLots;
        this.strategy = new MoreEmptySpacesStrategy();
    }

    public UUID park(Car car) throws FailToParkException {
        return strategy.getIdealParkingLot(parkingLots).park(car);
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
