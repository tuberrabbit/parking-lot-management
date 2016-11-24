package strategies;

import models.ParkingLot;

public class HighVacancyRateStrategy implements IdealParkingLotStrategy {
    @Override
    public ParkingLot getIdealParkingLot(ParkingLot... parkingLots) {
        ParkingLot highVacancyRate = new ParkingLot(0);
        for (ParkingLot parkingLot : parkingLots) {
            if (calcVacancyRate(parkingLot) > calcVacancyRate(highVacancyRate)) {
                highVacancyRate = parkingLot;
            }
        }
        return highVacancyRate;
    }

    private double calcVacancyRate(ParkingLot parkingLot) {
        return parkingLot.getSize() != 0 ? parkingLot.getEmptySpaces() * 1.0 / parkingLot.getSize() : 0;
    }
}
