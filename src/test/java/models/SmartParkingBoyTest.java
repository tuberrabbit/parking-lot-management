package models;

import factories.ParkingBoyFactory;
import org.junit.Test;

import java.util.UUID;

import static org.hamcrest.core.IsSame.sameInstance;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class SmartParkingBoyTest {
    @Test
    public void should_be_able_to_park_a_car_when_parking_lot_has_empty_spaces() {
        ParkingLot parkingLot = new ParkingLot(1);
        ParkingBoy smartBoy = ParkingBoyFactory.createSmartParkingBoy(parkingLot);
        Car car = new Car();

        UUID token = smartBoy.park(car);

        assertThat(parkingLot.pick(token), sameInstance(car));
    }

    @Test
    public void should_not_be_able_to_park_a_car_when_parking_lot_is_full() {
        ParkingBoy smartBoy = ParkingBoyFactory.createSmartParkingBoy(new ParkingLot(0));

        UUID token = smartBoy.park(new Car());

        assertNull(smartBoy.pick(token));
    }

    @Test
    public void should_be_able_to_pick_the_car_when_parked_it_in_parking_lot() {
        ParkingLot parkingLot = new ParkingLot(1);
        Car car = new Car();
        UUID token = parkingLot.park(car);
        ParkingBoy smartBoy = ParkingBoyFactory.createSmartParkingBoy(parkingLot);

        assertThat(smartBoy.pick(token), sameInstance(car));
    }

    @Test
    public void should_be_able_to_pick_the_car_when_parked_it_in_parking_lots() {
        ParkingLot parkingLot = new ParkingLot(1);
        Car car = new Car();
        UUID token = parkingLot.park(car);
        ParkingBoy smartBoy = ParkingBoyFactory.createSmartParkingBoy(new ParkingLot(0), parkingLot);

        assertThat(smartBoy.pick(token), sameInstance(car));
    }

    @Test
    public void should_not_be_able_to_pick_the_car_when_never_park_it_before() {
        ParkingBoy smartBoy = ParkingBoyFactory.createSmartParkingBoy(new ParkingLot(0));
        UUID errorToken = UUID.randomUUID();

        assertNull(smartBoy.pick(errorToken));
    }

    @Test
    public void should_not_be_able_to_pick_the_car_duplicated_when_parked_it_in_parking_lot() {
        ParkingLot parkingLot = new ParkingLot(1);
        Car car = new Car();
        UUID token = parkingLot.park(car);
        ParkingBoy smartBoy = ParkingBoyFactory.createSmartParkingBoy(parkingLot);
        smartBoy.pick(token);

        assertNull(smartBoy.pick(token));
    }

    @Test
    public void should_be_able_to_park_a_car_into_more_empty_spaces_parking_lot() {
        ParkingLot lessEmptySpaces = new ParkingLot(1);
        ParkingLot moreEmptySpaces = new ParkingLot(2);
        ParkingBoy smartBoy = ParkingBoyFactory.createSmartParkingBoy(lessEmptySpaces, moreEmptySpaces);
        Car car = new Car();

        UUID token = smartBoy.park(car);

        assertThat(moreEmptySpaces.pick(token), sameInstance(car));
    }

    @Test
    public void should_not_be_able_to_park_a_car_into_less_empty_spaces_parking_lot() {
        ParkingLot lessEmptySpaces = new ParkingLot(1);
        ParkingLot moreEmptySpaces = new ParkingLot(2);
        ParkingBoy smartBoy = ParkingBoyFactory.createSmartParkingBoy(lessEmptySpaces, moreEmptySpaces);
        Car car = new Car();

        UUID token = smartBoy.park(car);

        assertNull(lessEmptySpaces.pick(token));
    }
}
