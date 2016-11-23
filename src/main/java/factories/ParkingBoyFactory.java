package factories;

import models.ParkingBoy;
import models.ParkingLot;
import strategies.HighVacancyRateStrategy;
import strategies.InOrderStrategy;
import strategies.MoreEmptySpacesStrategy;

public class ParkingBoyFactory {
    public static ParkingBoy createGeneralParkingBoy(ParkingLot... parkingLots) {
        return new ParkingBoy(new InOrderStrategy(), parkingLots);
    }

    public static ParkingBoy createSmartParkingBoy(ParkingLot... parkingLots) {
        return new ParkingBoy(new MoreEmptySpacesStrategy(), parkingLots);
    }

    public static ParkingBoy createSuperParkingBoy(ParkingLot... parkingLots) {
        return new ParkingBoy(new HighVacancyRateStrategy(), parkingLots);
    }
}
