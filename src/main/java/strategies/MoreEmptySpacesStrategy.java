package strategies;

import models.ParkingLot;

public class MoreEmptySpacesStrategy implements IdealParkingLotStrategy {
    @Override
    public ParkingLot getIdealParkingLot(ParkingLot... parkingLots) {
        ParkingLot moreEmptySpaces = new ParkingLot(0);
        for (ParkingLot parkingLot : parkingLots) {
            if (parkingLot.getEmptySpaces() > moreEmptySpaces.getEmptySpaces()) {
                moreEmptySpaces = parkingLot;
            }
        }
        return moreEmptySpaces;
    }
}
