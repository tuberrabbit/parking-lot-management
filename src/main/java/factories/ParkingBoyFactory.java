package factories;

import models.ParkingBoy;
import models.ParkingLot;
import strategies.GeneralParkingBoyStrategy;
import strategies.SmartParkingBoyStrategy;
import strategies.SuperParkingBoyStrategy;

import java.util.List;

public class ParkingBoyFactory {
    public static ParkingBoy createParkingBoy(List<ParkingLot> parkingLots) {
        return new ParkingBoy(parkingLots, new GeneralParkingBoyStrategy());
    }

    public static ParkingBoy createSmartParkingBoy(List<ParkingLot> parkingLots) {
        return new ParkingBoy(parkingLots, new SmartParkingBoyStrategy());
    }

    public static ParkingBoy createSuperParkingBoy(List<ParkingLot> parkingLots) {
        return new ParkingBoy(parkingLots, new SuperParkingBoyStrategy());
    }
}