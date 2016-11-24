package models;

import strategies.IdealParkingLotStrategy;

import java.util.UUID;

public class ParkingBoy {
    protected final IdealParkingLotStrategy strategy;
    protected ParkingLot[] parkingLots;

    public ParkingBoy(IdealParkingLotStrategy strategy, ParkingLot... parkingLots) {
        this.strategy = strategy;
        this.parkingLots = parkingLots;
    }

    public UUID park(Car car) {
        return strategy.getIdealParkingLot(parkingLots).park(car);
    }

    public Car pick(UUID token) {
        for (ParkingLot parkingLot : parkingLots) {
            if (parkingLot.contains(token)) {
                return parkingLot.pick(token);
            }
        }
        return null;
    }
}
