package models;

import exceptions.NoAvailableParkingSpace;
import exceptions.NoSuchCarInParkingLot;

import java.util.List;
import java.util.UUID;

public class ParkingManager implements ParkingBehavior {
    private final List<ParkingBehavior> parkingBehaviors;

    public ParkingManager(List<ParkingBehavior> parkingBehaviors) {
        this.parkingBehaviors = parkingBehaviors;
    }

    public UUID park(Car car) throws NoAvailableParkingSpace {
        for (ParkingBehavior parkingBehavior : parkingBehaviors) {
            try {
                return parkingBehavior.park(car);
            } catch (NoAvailableParkingSpace noAvailableParkingSpace) {
                noAvailableParkingSpace.printStackTrace();
            }
        }
        throw new NoAvailableParkingSpace();
    }

    public Car pick(UUID ticket) throws NoSuchCarInParkingLot {
        for (ParkingBehavior parkingBehavior : parkingBehaviors) {
            try {
                return parkingBehavior.pick(ticket);
            } catch (NoSuchCarInParkingLot noSuchCarInParkingLot) {
                noSuchCarInParkingLot.printStackTrace();
            }
        }
        throw new NoSuchCarInParkingLot();
    }
}
