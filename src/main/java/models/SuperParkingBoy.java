package models;

import strategies.HighVacancyRateStrategy;

public class SuperParkingBoy extends Boy {
    public SuperParkingBoy(ParkingLot... parkingLots) {
        super(new HighVacancyRateStrategy(), parkingLots);
    }
}
