package models;

import exceptions.NoAvailableParkingSpace;
import exceptions.NoSuchCarInParkingLot;

import java.util.List;
import java.util.UUID;

public class ParkingBoy {
    private final List<ParkingLot> parkingLots;

    public ParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public UUID park(Car car) throws NoAvailableParkingSpace {
        UUID ticket = null;
        for (ParkingLot parkingLot : parkingLots) {
            try {
                ticket = parkingLot.park(car);
                break;
            } catch (NoAvailableParkingSpace noAvailableParkingSpace) {
                noAvailableParkingSpace.printStackTrace();
            }
        }
        if (ticket == null) {
            throw new NoAvailableParkingSpace();
        }
        return ticket;
    }

    public Car pick(UUID ticket) throws NoSuchCarInParkingLot {
        Car car = null;
        for (ParkingLot parkingLot : parkingLots) {
            try {
                car = parkingLot.pick(ticket);
                break;
            } catch (NoSuchCarInParkingLot noSuchCarInParkingLot) {
                noSuchCarInParkingLot.printStackTrace();
            }
        }
        if (car == null) {
            throw new NoSuchCarInParkingLot();
        }
        return car;
    }
}
