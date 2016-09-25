package strategies;

import models.ParkingLot;

import java.util.List;

public class SmartParkingBoyStrategy implements IdealParkingBoyStrategy {
    public ParkingLot getIdealParkingLot(List<ParkingLot> parkingLots) {
        ParkingLot parkingLotWithMaxAvailableSpaces = parkingLots.get(0);
        for (ParkingLot parkingLot : parkingLots) {
            if (parkingLot.getAvailableSpaces() > parkingLotWithMaxAvailableSpaces.getAvailableSpaces()) {
                parkingLotWithMaxAvailableSpaces = parkingLot;
            }
        }
        return parkingLotWithMaxAvailableSpaces;
    }
}
