package models;

import exceptions.NoAvailableParkingSpace;

import java.util.List;
import java.util.UUID;

public class SuperParkingBoy extends ParkingBoy {
    private final List<ParkingLot> parkingLots;

    public SuperParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
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

}
