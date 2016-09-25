package models;

import exceptions.NoAvailableParkingSpace;
import exceptions.NoSuchCarInParkingLot;
import factories.ParkingBoyFactory;
import org.junit.Test;

import java.util.Arrays;
import java.util.UUID;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ParkingManagerTest {
    @Test
    public void should_pick_the_original_car_when_park_my_car_by_parking_lot() throws NoAvailableParkingSpace, NoSuchCarInParkingLot {
        ParkingManager manager = new ParkingManager(Arrays.asList(new ParkingLot(1)));
        Car myCar = new Car();

        UUID myTicket = manager.park(myCar);

        assertThat(manager.pick(myTicket), is(myCar));
    }

    @Test
    public void should_pick_the_original_car_when_park_my_car_by_parking_boy() throws NoAvailableParkingSpace, NoSuchCarInParkingLot {
        ParkingManager manager = new ParkingManager(Arrays.asList(ParkingBoyFactory.createParkingBoy(Arrays.asList(new ParkingLot(1)))));
        Car myCar = new Car();

        UUID myTicket = manager.park(myCar);

        assertThat(manager.pick(myTicket), is(myCar));
    }

    @Test(expected = NoAvailableParkingSpace.class)
    public void should_not_park_my_car_when_all_parking_spaces_are_full() throws NoAvailableParkingSpace {
        ParkingManager manager = new ParkingManager(Arrays.asList(new ParkingLot(0)));
        Car myCar = new Car();

        manager.park(myCar);
    }

    @Test(expected = NoSuchCarInParkingLot.class)
    public void should_not_pick_my_car_when_not_parking_my_car_before() throws NoSuchCarInParkingLot {
        ParkingManager manager = new ParkingManager(Arrays.asList(new ParkingLot(0)));
        UUID fakeTicket = UUID.randomUUID();

        manager.pick(fakeTicket);
    }
}
