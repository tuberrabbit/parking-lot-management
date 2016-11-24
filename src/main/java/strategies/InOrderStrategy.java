package strategies;

import models.ParkingLot;

public class InOrderStrategy implements IdealParkingLotStrategy {
    @Override
    public ParkingLot getIdealParkingLot(ParkingLot... parkingLots) {
        ParkingLot inOrderParkingLot = new ParkingLot(0);
        for (ParkingLot parkingLot : parkingLots) {
            if (hasEmptySpaces(parkingLot)) {
                inOrderParkingLot = parkingLot;
                break;
            }
        }
        return inOrderParkingLot;
    }

    private boolean hasEmptySpaces(ParkingLot parkingLot) {
        return parkingLot.getEmptySpaces() > 0;
    }

}
