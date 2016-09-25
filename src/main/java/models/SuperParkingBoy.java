package models;

import strategies.IdealParkingLotStrategy;

import java.util.List;

public class SuperParkingBoy extends ParkingBoy {

    public SuperParkingBoy(List<ParkingLot> parkingLots, IdealParkingLotStrategy strategy) {
        super(parkingLots, strategy);
    }

}
