package strategies;

import models.ParkingLot;

import java.util.List;

public class GeneralParkingBoyStrategy implements IdealParkingBoyStrategy {
    public ParkingLot getIdealParkingLot(List<ParkingLot> parkingLots) {
        ParkingLot idealParkingLot = null;
        for (ParkingLot parkingLot : parkingLots) {
            if (parkingLot.getAvailableSpaces() > 0) {
                idealParkingLot = parkingLot;
                break;
            }
        }
        return idealParkingLot;
    }
}
