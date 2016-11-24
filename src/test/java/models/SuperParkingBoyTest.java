package models;


import factories.ParkingBoyFactory;
import org.junit.Test;

import java.util.UUID;

import static org.hamcrest.core.IsSame.sameInstance;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class SuperParkingBoyTest {
    @Test
    public void should_be_able_to_park_a_car_when_parking_lot_has_empty_spaces() {
        ParkingLot parkingLot = new ParkingLot(1);
        ParkingBoy superBoy = ParkingBoyFactory.createSuperParkingBoy(parkingLot);
        Car car = new Car();

        UUID token = superBoy.park(car);

        assertThat(parkingLot.pick(token), sameInstance(car));
    }

    @Test
    public void should_be_able_to_pick_the_car_when_parked_it_in_parking_lots() {
        ParkingLot parkingLot = new ParkingLot(1);
        Car car = new Car();
        UUID token = parkingLot.park(car);
        ParkingBoy superBoy = ParkingBoyFactory.createSuperParkingBoy(new ParkingLot(0), parkingLot);

        assertThat(superBoy.pick(token), sameInstance(car));
    }

    @Test
    public void should_not_be_able_to_park_a_car_when_parking_lot_is_full() {
        ParkingBoy superBoy = ParkingBoyFactory.createSuperParkingBoy(new ParkingLot(0));
        Car car = new Car();

        UUID token = superBoy.park(car);

        assertNull(superBoy.pick(token));
    }

    @Test
    public void should_not_be_able_to_pick_the_car_when_never_park_it_before() {
        ParkingBoy superBoy = ParkingBoyFactory.createSuperParkingBoy(new ParkingLot(0));
        UUID errorToken = UUID.randomUUID();

        assertNull(superBoy.pick(errorToken));
    }

    @Test
    public void should_not_be_able_to_pick_the_car_duplicated_when_parked_it_before() {
        ParkingLot parkingLot = new ParkingLot(1);
        UUID token = parkingLot.park(new Car());
        ParkingBoy superBoy = ParkingBoyFactory.createSuperParkingBoy(parkingLot);
        superBoy.pick(token);

        assertNull(superBoy.pick(token));
    }

    @Test
    public void should_be_able_to_park_a_car_into_high_vacancy_rate_parking_lot() {
        ParkingLot lowVacancyRate = new ParkingLot(2);
        lowVacancyRate.park(new Car());
        ParkingLot highVacancyRate = new ParkingLot(1);
        ParkingBoy superBoy = ParkingBoyFactory.createSuperParkingBoy(lowVacancyRate, highVacancyRate);
        Car car = new Car();

        UUID token = superBoy.park(car);

        assertThat(highVacancyRate.pick(token), sameInstance(car));
    }

    @Test
    public void should_not_be_able_to_park_a_car_into_low_vacancy_rate_parking_lot() {
        ParkingLot lowVacancyRate = new ParkingLot(2);
        lowVacancyRate.park(new Car());
        ParkingLot highVacancyRate = new ParkingLot(1);
        ParkingBoy superBoy = ParkingBoyFactory.createSuperParkingBoy(lowVacancyRate, highVacancyRate);
        Car car = new Car();

        UUID token = superBoy.park(car);

        assertNull(lowVacancyRate.pick(token));
    }
}
