package models;

import exceptions.FailToParkException;
import exceptions.FailToPickException;
import org.junit.Test;

import java.util.UUID;

import static org.hamcrest.core.IsSame.sameInstance;
import static org.junit.Assert.assertThat;

public class ParkingBoyTest {
    @Test
    public void should_be_able_to_park_a_car_when_parking_lot_has_empty_spaces() throws FailToParkException, FailToPickException {
        ParkingLot parkingLot = new ParkingLot(1);
        Car car = new Car();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);

        UUID token = parkingBoy.park(car);

        assertThat(parkingLot.pick(token), sameInstance(car));
    }

    @Test(expected = FailToParkException.class)
    public void should_not_be_able_to_park_a_car_when_parking_lot_is_full() throws FailToParkException {
        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot(0));

        parkingBoy.park(new Car());
    }

    @Test
    public void should_be_able_to_pick_the_car_when_parked_a_car_into_parking_lot() throws Exception {
        ParkingLot parkingLot = new ParkingLot(1);
        Car car = new Car();
        UUID token = parkingLot.park(car);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);

        assertThat(parkingBoy.pick(token), sameInstance(car));
    }

    @Test(expected = FailToPickException.class)
    public void should_not_be_able_to_pick_the_car_when_never_park_a_car_into_parking_lot_before() throws FailToPickException {
        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot(0));
        UUID errorToken = UUID.randomUUID();

        parkingBoy.pick(errorToken);
    }

    @Test(expected = FailToPickException.class)
    public void should_not_pick_the_car_duplicated_when_parked_a_car_into_parking_lot_before() throws Exception {
        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot(1));
        UUID token = parkingBoy.park(new Car());
        parkingBoy.pick(token);

        parkingBoy.pick(token);
    }

    @Test
    public void should_park_a_car_into_first_parking_lot_when_the_first_parking_lot_has_empty_spaces() throws Exception {
        ParkingLot firstParkingLot = new ParkingLot(1);
        ParkingLot secondParkingLot = new ParkingLot(1);
        ParkingBoy parkingBoy = new ParkingBoy(firstParkingLot, secondParkingLot);
        Car car = new Car();

        UUID token = parkingBoy.park(car);

        assertThat(firstParkingLot.pick(token), sameInstance(car));
    }

    @Test(expected = FailToPickException.class)
    public void should_not_park_a_car_into_second_parking_lot_when_the_first_parking_lot_has_empty_spaces() throws Exception {
        ParkingLot firstParkingLot = new ParkingLot(1);
        ParkingLot secondParkingLot = new ParkingLot(1);
        ParkingBoy parkingBoy = new ParkingBoy(firstParkingLot, secondParkingLot);

        UUID token = parkingBoy.park(new Car());

        secondParkingLot.pick(token);
    }

    @Test
    public void should_park_a_car_into_second_parking_lot_when_the_first_parking_lot_is_full() throws Exception {
        Car car = new Car();
        ParkingLot parkingLotWithEmptySpaces = new ParkingLot(1);
        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot(0), parkingLotWithEmptySpaces);

        UUID token = parkingBoy.park(car);

        assertThat(parkingLotWithEmptySpaces.pick(token), sameInstance(car));
    }

    @Test
    public void should_pick_the_car_when_a_car_parked_into_second_parking_lot() throws Exception {
        ParkingLot parkingLotWithEmptySpaces = new ParkingLot(1);
        Car car = new Car();
        UUID token = parkingLotWithEmptySpaces.park(car);
        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot(0), parkingLotWithEmptySpaces);

        assertThat(parkingBoy.pick(token), sameInstance(car));
    }
}
