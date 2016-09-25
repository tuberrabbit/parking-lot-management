package strategies;

import models.ParkingLot;

import java.util.List;

public interface IdealParkingLotStrategy {
    ParkingLot getIdealParkingLot(List<ParkingLot> parkingLots);
}
