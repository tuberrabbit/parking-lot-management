package models;

import exceptions.NoAvailableParkingSpace;
import exceptions.NoSuchCarInParkingLot;

import java.util.UUID;

public interface ParkingBehavior {
    UUID park(Car car) throws NoAvailableParkingSpace;

    Car pick(UUID ticket) throws NoSuchCarInParkingLot;
}
