package models;

import exceptions.FailToParkException;
import exceptions.FailToPickException;

import java.util.UUID;

public class SuperParkingBoy {
    private final ParkingLot[] parkingLots;

    public SuperParkingBoy(ParkingLot... parkingLots) {
        this.parkingLots = parkingLots;
    }

    public UUID park(Car car) throws FailToParkException {
        ParkingLot highVacancyRate = getHighVacancyRate(parkingLots);
        if (highVacancyRate.hasEmptySpaces()) {
            return highVacancyRate.park(car);
        }
        throw new FailToParkException();
    }

    private ParkingLot getHighVacancyRate(ParkingLot[] parkingLots) {
        ParkingLot highVacancyRate = parkingLots[0];
        for (ParkingLot parkingLot : parkingLots) {
            if (parkingLot.getVacancyRate() > highVacancyRate.getVacancyRate()) {
                highVacancyRate = parkingLot;
            }
        }
        return highVacancyRate;
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
