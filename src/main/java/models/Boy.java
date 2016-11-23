package models;

import exceptions.FailToParkException;
import exceptions.FailToPickException;
import strategies.IdealParkingLotStrategy;

import java.util.UUID;

public class Boy {
    protected final IdealParkingLotStrategy strategy;
    protected ParkingLot[] parkingLots;

    public Boy(IdealParkingLotStrategy strategy, ParkingLot... parkingLots) {
        this.strategy = strategy;
        this.parkingLots = parkingLots;
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
