package models;

import exceptions.FailToParkException;
import exceptions.FailToPickException;
import factories.ParkingBoyFactory;
import org.junit.Test;

import java.util.UUID;

import static org.hamcrest.core.IsSame.sameInstance;
import static org.junit.Assert.assertThat;

public class ParkingBoyTest {
    @Test
    public void should_be_able_to_park_a_car_when_parking_lot_has_empty_spaces() throws Exception {
        ParkingLot parkingLot = new ParkingLot(1);
        Car car = new Car();
        ParkingBoy parkingBoy = ParkingBoyFactory.createGeneralParkingBoy(parkingLot);

        UUID token = parkingBoy.park(car);

        assertThat(parkingLot.pick(token), sameInstance(car));
    }

    @Test(expected = FailToParkException.class)
    public void should_not_be_able_to_park_a_car_when_parking_lot_is_full() throws FailToParkException {
        ParkingBoy parkingBoy = ParkingBoyFactory.createGeneralParkingBoy(new ParkingLot(0));

        parkingBoy.park(new Car());
    }

    @Test
    public void should_be_able_to_pick_the_car_when_parked_it_in_parking_lots() throws Exception {
        ParkingLot parkingLot = new ParkingLot(1);
        Car car = new Car();
        UUID token = parkingLot.park(car);
        ParkingBoy parkingBoy = ParkingBoyFactory.createGeneralParkingBoy(new ParkingLot(0), parkingLot);

        assertThat(parkingBoy.pick(token), sameInstance(car));
    }

    @Test(expected = FailToPickException.class)
    public void should_not_be_able_to_pick_the_car_when_never_park_it_before() throws FailToPickException {
        ParkingBoy parkingBoy = ParkingBoyFactory.createGeneralParkingBoy(new ParkingLot(0));
        UUID errorToken = UUID.randomUUID();

        parkingBoy.pick(errorToken);
    }

    @Test(expected = FailToPickException.class)
    public void should_not_be_able_to_pick_the_car_duplicated_when_parked_it_in_parking_lot() throws Exception {
        ParkingBoy parkingBoy = ParkingBoyFactory.createGeneralParkingBoy(new ParkingLot(1));
        UUID token = parkingBoy.park(new Car());
        parkingBoy.pick(token);

        parkingBoy.pick(token);
    }

    @Test
    public void should_be_able_to_park_a_car_into_first_parking_lot_when_the_first_parking_lot_has_empty_spaces() throws Exception {
        ParkingLot firstParkingLot = new ParkingLot(1);
        ParkingLot secondParkingLot = new ParkingLot(1);
        ParkingBoy parkingBoy = ParkingBoyFactory.createGeneralParkingBoy(firstParkingLot, secondParkingLot);
        Car car = new Car();

        UUID token = parkingBoy.park(car);

        assertThat(firstParkingLot.pick(token), sameInstance(car));
    }

    @Test(expected = FailToPickException.class)
    public void should_not_be_able_to_park_a_car_into_second_parking_lot_when_the_first_parking_lot_has_empty_spaces() throws Exception {
        ParkingLot firstParkingLot = new ParkingLot(1);
        ParkingLot secondParkingLot = new ParkingLot(1);
        ParkingBoy parkingBoy = ParkingBoyFactory.createGeneralParkingBoy(firstParkingLot, secondParkingLot);

        UUID token = parkingBoy.park(new Car());

        secondParkingLot.pick(token);
    }

    @Test
    public void should_be_able_to_park_a_car_into_second_parking_lot_when_the_first_parking_lot_is_full() throws Exception {
        Car car = new Car();
        ParkingLot parkingLotWithEmptySpaces = new ParkingLot(1);
        ParkingBoy parkingBoy = ParkingBoyFactory.createGeneralParkingBoy(new ParkingLot(0), parkingLotWithEmptySpaces);

        UUID token = parkingBoy.park(car);

        assertThat(parkingLotWithEmptySpaces.pick(token), sameInstance(car));
    }
}
