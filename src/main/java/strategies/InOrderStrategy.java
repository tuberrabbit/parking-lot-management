package strategies;

import models.ParkingLot;

public class InOrderStrategy implements IdealParkingLotStrategy {
    @Override
    public ParkingLot getIdealParkingLot(ParkingLot... parkingLots) {
        ParkingLot inOrderParkingLot = new ParkingLot(0);
        for (ParkingLot parkingLot : parkingLots) {
            if (parkingLot.hasEmptySpaces()) {
                inOrderParkingLot = parkingLot;
                break;
            }
        }
        return inOrderParkingLot;
    }
}
