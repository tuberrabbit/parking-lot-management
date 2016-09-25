package strategies;

import models.ParkingLot;

import java.util.List;

public interface IdealParkingBoyStrategy {
    ParkingLot getIdealParkingLot(List<ParkingLot> parkingLots);
}
