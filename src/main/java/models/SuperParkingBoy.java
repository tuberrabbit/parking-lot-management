package models;

import exceptions.NoAvailableParkingSpace;
import exceptions.NoSuchCarInParkingLot;

import java.util.List;
import java.util.UUID;

public class SuperParkingBoy {
    private final List<ParkingLot> parkingLots;

    public SuperParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public UUID park(Car car) throws NoAvailableParkingSpace {
        ParkingLot parkingLotWithMaxVacancyRate = parkingLots.get(0);
        for (ParkingLot parkingLot : parkingLots) {
            if (parkingLot.getVacancyRate() > parkingLotWithMaxVacancyRate.getVacancyRate()) {
                parkingLotWithMaxVacancyRate = parkingLot;
            }
        }
        if (parkingLotWithMaxVacancyRate == null) {
            throw new NoAvailableParkingSpace();
        }
        return parkingLotWithMaxVacancyRate.park(car);
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
