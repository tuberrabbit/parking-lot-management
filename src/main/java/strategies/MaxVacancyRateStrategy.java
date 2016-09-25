package strategies;

import models.ParkingLot;

import java.util.List;

public class MaxVacancyRateStrategy implements IdealParkingLotStrategy {
    public ParkingLot getIdealParkingLot(List<ParkingLot> parkingLots) {
        ParkingLot parkingLotWithMaxVacancyRate = parkingLots.get(0);
        for (ParkingLot parkingLot : parkingLots) {
            if (parkingLot.getVacancyRate() > parkingLotWithMaxVacancyRate.getVacancyRate()) {
                parkingLotWithMaxVacancyRate = parkingLot;
            }
        }
        return parkingLotWithMaxVacancyRate;
    }
}
