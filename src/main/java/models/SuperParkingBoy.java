package models;

import exceptions.FailToParkException;
import exceptions.FailToPickException;
import strategies.HighVacancyRateStrategy;
import strategies.IdealParkingLotStrategy;

import java.util.UUID;

public class SuperParkingBoy {
    private final ParkingLot[] parkingLots;
    private final IdealParkingLotStrategy strategy;

    public SuperParkingBoy(ParkingLot... parkingLots) {
        this.parkingLots = parkingLots;
        this.strategy = new HighVacancyRateStrategy();
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
