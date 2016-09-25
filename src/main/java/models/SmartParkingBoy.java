package models;

import strategies.IdealParkingLotStrategy;

import java.util.List;

public class SmartParkingBoy extends ParkingBoy {

    public SmartParkingBoy(List<ParkingLot> parkingLots, IdealParkingLotStrategy strategy) {
        super(parkingLots, strategy);
    }

}
