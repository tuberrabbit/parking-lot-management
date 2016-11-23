package models;

import strategies.MoreEmptySpacesStrategy;

public class SmartParkingBoy extends Boy {
    public SmartParkingBoy(ParkingLot... parkingLots) {
        super(new MoreEmptySpacesStrategy(), parkingLots);
    }
}
