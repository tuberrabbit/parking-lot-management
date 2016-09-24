package models;

import exceptions.NoAvailableParkingSpace;
import exceptions.NoSuchCarInParkingLot;

import java.util.HashMap;
import java.util.UUID;

public class ParkingLot {
    private final HashMap<UUID, Car> cars;
    private final int size;

    public ParkingLot(int size) {
        this.size = size;
        this.cars = new HashMap<>();
    }

    public UUID park(Car car) throws NoAvailableParkingSpace {
        if (cars.size() < size) {
            UUID ticket = UUID.randomUUID();
            cars.put(ticket, car);
            return ticket;
        }
        throw new NoAvailableParkingSpace();
    }

    public Car pick(UUID ticket) throws NoSuchCarInParkingLot {
        Car car = cars.get(ticket);
        if (car == null) {
            throw new NoSuchCarInParkingLot();
        }
        cars.remove(ticket);
        return car;
    }

    public Integer getAvailableSpaces() {
        return size - cars.size();
    }
}
