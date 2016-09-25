package models;

import exceptions.NoAvailableParkingSpace;
import exceptions.NoSuchCarInParkingLot;
import factories.ParkingBoyFactory;
import org.junit.Test;

import java.util.Arrays;
import java.util.UUID;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SmartParkingBoyTest {
    @Test
    public void should_pick_the_original_car_when_park_my_car() throws NoAvailableParkingSpace, NoSuchCarInParkingLot {
        ParkingBoy smartParkingBoy = ParkingBoyFactory.createSmartParkingBoy(Arrays.asList(new ParkingLot(1)));
        Car myCar = new Car();

        UUID myTicket = smartParkingBoy.park(myCar);

        assertThat(smartParkingBoy.pick(myTicket), is(myCar));
    }

    @Test(expected = NoAvailableParkingSpace.class)
    public void should_not_park_my_car_when_all_parking_spaces_are_full() throws NoAvailableParkingSpace {
        ParkingBoy smartParkingBoy = ParkingBoyFactory.createSmartParkingBoy(Arrays.asList(new ParkingLot(0)));
        Car myCar = new Car();

        smartParkingBoy.park(myCar);
    }

    @Test(expected = NoSuchCarInParkingLot.class)
    public void should_not_pick_my_car_when_my_car_not_parking_into_parking_lot() throws NoSuchCarInParkingLot {
        ParkingBoy smartParkingBoy = ParkingBoyFactory.createSmartParkingBoy(Arrays.asList(new ParkingLot(0)));
        UUID fakeTicket = UUID.randomUUID();

        smartParkingBoy.pick(fakeTicket);
    }

    @Test
    public void should_park_my_car_into_parking_lot_with_max_available_spaces() throws NoAvailableParkingSpace, NoSuchCarInParkingLot {
        ParkingLot parkingLotWithLessAvailableSpaces = new ParkingLot(1);
        ParkingLot parkingLotWithMaxAvailableSpaces = new ParkingLot(2);
        ParkingBoy smartParkingBoy = ParkingBoyFactory.createSmartParkingBoy(Arrays.asList(parkingLotWithLessAvailableSpaces, parkingLotWithMaxAvailableSpaces));
        Car myCar = new Car();

        UUID myTicket = smartParkingBoy.park(myCar);

        assertThat(parkingLotWithLessAvailableSpaces.getAvailableSpaces(), is(1));
        assertThat(parkingLotWithMaxAvailableSpaces.getAvailableSpaces(), is(1));
        assertThat(parkingLotWithMaxAvailableSpaces.pick(myTicket), is(myCar));
    }
}
