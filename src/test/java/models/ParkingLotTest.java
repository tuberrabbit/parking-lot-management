package models;

import exceptions.NoAvailableParkingSpace;
import exceptions.NoSuchCarInParkingLot;
import org.junit.Test;

import java.util.UUID;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ParkingLotTest {
    @Test
    public void should_pick_the_original_car_when_park_my_car() throws NoAvailableParkingSpace, NoSuchCarInParkingLot {
        ParkingLot parkingLot = new ParkingLot(1);
        Car myCar = new Car();

        UUID myTicket = parkingLot.park(myCar);

        assertThat(parkingLot.pick(myTicket), is(myCar));
    }

    @Test(expected = NoAvailableParkingSpace.class)
    public void should_not_park_my_car_when_parking_lot_spaces_are_full() throws NoAvailableParkingSpace {
        ParkingLot parkingLot = new ParkingLot(0);
        Car myCar = new Car();

        parkingLot.park(myCar);
    }

    @Test(expected = NoSuchCarInParkingLot.class)
    public void should_not_pick_my_car_when_my_car_not_parking_into_parking_lot() throws NoSuchCarInParkingLot {
        ParkingLot parkingLot = new ParkingLot(0);
        UUID myTicket = UUID.randomUUID();

        parkingLot.pick(myTicket);

    }
}
