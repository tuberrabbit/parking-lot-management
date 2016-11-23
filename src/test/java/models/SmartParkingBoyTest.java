package models;

import exceptions.FailToParkException;
import exceptions.FailToPickException;
import factories.ParkingBoyFactory;
import org.junit.Test;

import java.util.UUID;

import static org.hamcrest.core.IsSame.sameInstance;
import static org.junit.Assert.assertThat;

public class SmartParkingBoyTest {
    @Test
    public void should_be_able_to_park_a_car_when_parking_lot_has_empty_spaces() throws Exception {
        ParkingLot parkingLot = new ParkingLot(1);
        ParkingBoy smartBoy = ParkingBoyFactory.createSmartParkingBoy(parkingLot);
        Car car = new Car();

        UUID token = smartBoy.park(car);

        assertThat(parkingLot.pick(token), sameInstance(car));
    }

    @Test(expected = FailToParkException.class)
    public void should_not_be_able_to_park_a_car_when_parking_lot_is_full() throws FailToParkException {
        ParkingBoy smartBoy = ParkingBoyFactory.createSmartParkingBoy(new ParkingLot(0));

        smartBoy.park(new Car());
    }

    @Test
    public void should_be_able_to_pick_the_car_when_parked_it_in_parking_lot() throws Exception {
        ParkingLot parkingLot = new ParkingLot(1);
        Car car = new Car();
        UUID token = parkingLot.park(car);
        ParkingBoy smartBoy = ParkingBoyFactory.createSmartParkingBoy(parkingLot);

        assertThat(smartBoy.pick(token), sameInstance(car));
    }

    @Test
    public void should_be_able_to_pick_the_car_when_parked_it_in_parking_lots() throws Exception {
        ParkingLot parkingLot = new ParkingLot(1);
        Car car = new Car();
        UUID token = parkingLot.park(car);
        ParkingBoy smartBoy = ParkingBoyFactory.createSmartParkingBoy(new ParkingLot(0), parkingLot);

        assertThat(smartBoy.pick(token), sameInstance(car));
    }

    @Test(expected = FailToPickException.class)
    public void should_not_be_able_to_pick_the_car_when_never_park_it_before() throws FailToPickException {
        ParkingBoy smartBoy = ParkingBoyFactory.createSmartParkingBoy(new ParkingLot(0));
        UUID errorToken = UUID.randomUUID();

        smartBoy.pick(errorToken);
    }

    @Test(expected = FailToPickException.class)
    public void should_not_be_able_to_pick_the_car_duplicated_when_parked_it_in_parking_lot() throws Exception {
        ParkingLot parkingLot = new ParkingLot(1);
        Car car = new Car();
        UUID token = parkingLot.park(car);
        ParkingBoy smartBoy = ParkingBoyFactory.createSmartParkingBoy(parkingLot);
        smartBoy.pick(token);

        smartBoy.pick(token);
    }

    @Test
    public void should_be_able_to_park_a_car_into_more_empty_spaces_parking_lot() throws Exception {
        ParkingLot lessEmptySpaces = new ParkingLot(1);
        ParkingLot moreEmptySpaces = new ParkingLot(2);
        ParkingBoy smartBoy = ParkingBoyFactory.createSmartParkingBoy(lessEmptySpaces, moreEmptySpaces);
        Car car = new Car();

        UUID token = smartBoy.park(car);

        assertThat(moreEmptySpaces.pick(token), sameInstance(car));
    }

    @Test(expected = FailToPickException.class)
    public void should_not_be_able_to_park_a_car_into_less_empty_spaces_parking_lot() throws Exception {
        ParkingLot lessEmptySpaces = new ParkingLot(1);
        ParkingLot moreEmptySpaces = new ParkingLot(2);
        ParkingBoy smartBoy = ParkingBoyFactory.createSmartParkingBoy(lessEmptySpaces, moreEmptySpaces);
        Car car = new Car();

        UUID token = smartBoy.park(car);

        lessEmptySpaces.pick(token);
    }
}
