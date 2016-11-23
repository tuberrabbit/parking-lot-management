package strategies;

import models.ParkingLot;

public interface IdealParkingLotStrategy {
    ParkingLot getIdealParkingLot(ParkingLot... parkingLots);
}
