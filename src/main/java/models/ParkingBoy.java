package models;

import exceptions.NoAvailableParkingSpace;
import exceptions.NoSuchCarInParkingLot;
import strategies.IdealParkingLotStrategy;

import java.util.List;
import java.util.UUID;

public class ParkingBoy {
    private final List<ParkingLot> parkingLots;
    private IdealParkingLotStrategy strategy;

    public ParkingBoy(List<ParkingLot> parkingLots, IdealParkingLotStrategy strategy) {
        this.parkingLots = parkingLots;
        this.strategy = strategy;
    }

    public UUID park(Car car) throws NoAvailableParkingSpace {
        ParkingLot idealParkingLot = strategy.getIdealParkingLot(parkingLots);
        if (idealParkingLot == null) {
            throw new NoAvailableParkingSpace();
        }
        return idealParkingLot.park(car);
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
