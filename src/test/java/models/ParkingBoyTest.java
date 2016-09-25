package models;

import exceptions.NoAvailableParkingSpace;
import exceptions.NoSuchCarInParkingLot;
import factories.ParkingBoyFactory;
import org.junit.Test;

import java.util.Arrays;
import java.util.UUID;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ParkingBoyTest {
    @Test
    public void should_pick_the_original_car_when_park_my_car() throws NoAvailableParkingSpace, NoSuchCarInParkingLot {
        ParkingBoy parkingBoy = ParkingBoyFactory.createParkingBoy(Arrays.asList(new ParkingLot(1)));
        Car myCar = new Car();

        UUID myTicket = parkingBoy.park(myCar);

        assertThat(parkingBoy.pick(myTicket), is(myCar));
    }

    @Test(expected = NoAvailableParkingSpace.class)
    public void should_not_park_my_car_when_all_parking_spaces_are_full() throws NoAvailableParkingSpace {
        ParkingBoy parkingBoy = ParkingBoyFactory.createParkingBoy(Arrays.asList(new ParkingLot(0)));
        Car myCar = new Car();

        parkingBoy.park(myCar);
    }

    @Test(expected = NoSuchCarInParkingLot.class)
    public void should_not_pick_my_car_when_my_car_not_parking_into_parking_lot() throws NoSuchCarInParkingLot {
        ParkingBoy parkingBoy = ParkingBoyFactory.createParkingBoy(Arrays.asList(new ParkingLot(0)));
        UUID fakeTicket = UUID.randomUUID();

        parkingBoy.pick(fakeTicket);
    }

    @Test
    public void should_only_park_one_parking_lot_when_there_are_many_parking_lots_have_available_spaces() throws NoAvailableParkingSpace, NoSuchCarInParkingLot {
        ParkingLot parkingLot1 = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot(1);
        ParkingBoy parkingBoy = ParkingBoyFactory.createParkingBoy(Arrays.asList(parkingLot1, parkingLot2));
        Car myCar = new Car();

        parkingBoy.park(myCar);

        assertThat(parkingLot1.getAvailableSpaces(), is(0));
        assertThat(parkingLot2.getAvailableSpaces(), is(1));
    }
}
