package models;

import exceptions.FailToParkException;
import exceptions.FailToPickException;
import org.junit.Test;

import java.util.UUID;

import static org.hamcrest.core.IsSame.sameInstance;
import static org.junit.Assert.assertThat;

public class SmartParkingBoyTest {
    @Test
    public void should_be_able_to_park_a_car_when_parking_lot_has_empty_spaces() throws Exception {
        ParkingLot parkingLot = new ParkingLot(1);
        SmartParkingBoy smartBoy = new SmartParkingBoy(parkingLot);
        Car car = new Car();

        UUID token = smartBoy.park(car);

        assertThat(parkingLot.pick(token), sameInstance(car));
    }

    @Test(expected = FailToParkException.class)
    public void should_not_be_able_to_park_a_car_when_parking_lot_is_full() throws FailToParkException {
        SmartParkingBoy smartBoy = new SmartParkingBoy(new ParkingLot(0));

        smartBoy.park(new Car());
    }

    @Test
    public void should_be_able_to_pick_the_car_when_parked_a_car_into_parking_lot() throws Exception {
        ParkingLot parkingLot = new ParkingLot(1);
        Car car = new Car();
        UUID token = parkingLot.park(car);
        SmartParkingBoy smartBoy = new SmartParkingBoy(parkingLot);

        assertThat(smartBoy.pick(token), sameInstance(car));
    }

    @Test
    public void should_be_able_to_pick_the_car_when_a_car_parked_into_second_parking_lot() throws Exception {
        ParkingLot firstParkingLot = new ParkingLot(0);
        ParkingLot secondParkingLot = new ParkingLot(1);
        Car car = new Car();
        UUID token = secondParkingLot.park(car);
        SmartParkingBoy smartBoy = new SmartParkingBoy(firstParkingLot, secondParkingLot);

        assertThat(smartBoy.pick(token), sameInstance(car));
    }

    @Test(expected = FailToPickException.class)
    public void should_not_pick_the_car_when_never_park_a_car_into_parking_lot_before() throws FailToPickException {
        SmartParkingBoy smartBoy = new SmartParkingBoy(new ParkingLot(0));
        UUID errorToken = UUID.randomUUID();

        smartBoy.pick(errorToken);
    }

    @Test(expected = FailToPickException.class)
    public void should_not_pick_the_car_duplicated_when_parked_a_car_into_parking_lot_before() throws Exception {
        ParkingLot parkingLot = new ParkingLot(1);
        Car car = new Car();
        UUID token = parkingLot.park(car);
        SmartParkingBoy smartBoy = new SmartParkingBoy(parkingLot);
        smartBoy.pick(token);

        smartBoy.pick(token);
    }

    @Test
    public void should_park_a_car_into_parking_lot_with_more_empty_spaces_when_two_parking_lot_both_have_empty_spaces() throws Exception {
        ParkingLot lessEmptySpaces = new ParkingLot(1);
        ParkingLot moreEmptySpaces = new ParkingLot(2);
        SmartParkingBoy smartBoy = new SmartParkingBoy(lessEmptySpaces, moreEmptySpaces);
        Car car = new Car();

        UUID token = smartBoy.park(car);

        assertThat(moreEmptySpaces.pick(token), sameInstance(car));
    }

    @Test(expected = FailToPickException.class)
    public void should_not_park_a_car_into_parking_lot_with_less_empty_spaces_when_two_parking_lot_both_have_empty_spaces() throws Exception {
        ParkingLot lessEmptySpaces = new ParkingLot(1);
        ParkingLot moreEmptySpaces = new ParkingLot(2);
        SmartParkingBoy smartBoy = new SmartParkingBoy(lessEmptySpaces, moreEmptySpaces);
        Car car = new Car();

        UUID token = smartBoy.park(car);

        lessEmptySpaces.pick(token);
    }
}
