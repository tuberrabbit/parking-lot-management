package models;

import exceptions.FailToParkException;
import exceptions.FailToPickException;
import strategies.IdealParkingLotStrategy;
import strategies.InOrderStrategy;

import java.util.UUID;

public class ParkingBoy {

    private final IdealParkingLotStrategy strategy;
    private ParkingLot[] parkingLots;

    public ParkingBoy(ParkingLot... parkingLots) {
        this.parkingLots = parkingLots;
        this.strategy = new InOrderStrategy();
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
