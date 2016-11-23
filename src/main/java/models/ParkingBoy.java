package models;

import strategies.InOrderStrategy;

public class ParkingBoy extends Boy {
    public ParkingBoy(ParkingLot... parkingLots) {
        super(new InOrderStrategy(), parkingLots);
    }
}
