package models;

import org.junit.Test;

import java.util.UUID;

import static org.hamcrest.core.IsSame.sameInstance;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class ParkingLotTest {
    @Test
    public void should_be_able_to_pick_the_car_when_parked_it_in_parking_lot() {
        ParkingLot parkingLot = new ParkingLot(1);
        Car car = new Car();
        UUID token = parkingLot.park(car);

        assertThat(parkingLot.pick(token), sameInstance(car));
    }

    @Test
    public void should_not_be_able_to_park_the_car_when_parking_lot_is_full() {
        ParkingLot parkingLot = new ParkingLot(0);

        UUID token = parkingLot.park(new Car());

        assertNull(parkingLot.pick(token));
    }

    @Test
    public void should_not_be_able_to_pick_the_car_when_never_park_it_before() {
        ParkingLot parkingLot = new ParkingLot(0);
        UUID errorToken = UUID.randomUUID();

        assertNull(parkingLot.pick(errorToken));
    }

    @Test
    public void should_not_be_able_to_pick_the_car_duplicated_when_parked_it_in_parking_lot() {
        ParkingLot parkingLot = new ParkingLot(1);
        UUID token = parkingLot.park(new Car());
        parkingLot.pick(token);

        assertNull(parkingLot.pick(token));
    }
}
