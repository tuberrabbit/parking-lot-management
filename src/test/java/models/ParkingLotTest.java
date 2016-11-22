package models;

import exceptions.FailToParkException;
import exceptions.FailToPickException;
import org.junit.Test;

import java.util.UUID;

import static org.hamcrest.core.IsSame.sameInstance;
import static org.junit.Assert.assertThat;

public class ParkingLotTest {
    @Test
    public void should_be_able_to_pick_the_car_when_parked_a_car_into_parking_lot() throws Exception {
        ParkingLot parkingLot = new ParkingLot(1);
        Car car = new Car();
        UUID token = parkingLot.park(car);

        assertThat(parkingLot.pick(token), sameInstance(car));
    }

    @Test(expected = FailToParkException.class)
    public void should_not_be_able_to_park_the_car_when_parking_lot_is_full() throws FailToParkException {
        ParkingLot parkingLot = new ParkingLot(0);

        parkingLot.park(new Car());
    }

    @Test(expected = FailToPickException.class)
    public void should_not_be_able_to_pick_the_car_when_never_park_a_car_into_parking_lot_before() throws FailToPickException {
        ParkingLot parkingLot = new ParkingLot(0);
        UUID errorToken = UUID.randomUUID();

        parkingLot.pick(errorToken);
    }

    @Test(expected = FailToPickException.class)
    public void should_not_be_able_to_pick_the_car_duplicated_when_parked_a_car_into_parking_lot() throws Exception {
        ParkingLot parkingLot = new ParkingLot(1);
        UUID token = parkingLot.park(new Car());
        parkingLot.pick(token);

        parkingLot.pick(token);
    }
}
