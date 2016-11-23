package strategies;

import models.ParkingLot;

public class HighVacancyRateStrategy implements IdealParkingLotStrategy {
    @Override
    public ParkingLot getIdealParkingLot(ParkingLot... parkingLots) {
        ParkingLot highVacancyRate = new ParkingLot(0);
        for (ParkingLot parkingLot : parkingLots) {
            if (parkingLot.getVacancyRate() > highVacancyRate.getVacancyRate()) {
                highVacancyRate = parkingLot;
            }
        }
        return highVacancyRate;
    }
}
