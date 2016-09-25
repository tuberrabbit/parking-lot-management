package models;

import exceptions.NoAvailableParkingSpace;
import exceptions.NoSuchCarInParkingLot;
import factories.ParkingBoyFactory;
import org.junit.Test;

import java.util.Arrays;
import java.util.UUID;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SuperParkingBoyTest {
    @Test
    public void should_pick_the_original_car_when_park_my_car() throws NoAvailableParkingSpace, NoSuchCarInParkingLot {
        ParkingBoy superParkingBoy = ParkingBoyFactory.createSuperParkingBoy(Arrays.asList(new ParkingLot(1)));
        Car myCar = new Car();

        UUID myTicket = superParkingBoy.park(myCar);

        assertThat(superParkingBoy.pick(myTicket), is(myCar));
    }

    @Test(expected = NoAvailableParkingSpace.class)
    public void should_not_park_my_car_when_all_parking_spaces_are_full() throws NoAvailableParkingSpace {
        ParkingBoy superParkingBoy = ParkingBoyFactory.createSuperParkingBoy(Arrays.asList(new ParkingLot(0)));
        Car myCar = new Car();

        superParkingBoy.park(myCar);
    }

    @Test(expected = NoSuchCarInParkingLot.class)
    public void should_not_pick_my_car_when_my_car_not_parking_into_parking_lot() throws NoSuchCarInParkingLot {
        ParkingBoy superParkingBoy = ParkingBoyFactory.createSuperParkingBoy(Arrays.asList(new ParkingLot(0)));
        UUID fakeTicket = UUID.randomUUID();

        superParkingBoy.pick(fakeTicket);
    }

    @Test
    public void should_park_my_car_into_parking_lot_with_max_vacancy_rate() throws NoAvailableParkingSpace, NoSuchCarInParkingLot {
        ParkingLot parkingLotWithLessVacancyRate = new ParkingLot(2);
        parkingLotWithLessVacancyRate.park(new Car());
        ParkingLot parkingLotWithMaxVacancyRate = new ParkingLot(1);
        ParkingBoy superParkingBoy = ParkingBoyFactory.createSuperParkingBoy(Arrays.asList(parkingLotWithLessVacancyRate, parkingLotWithMaxVacancyRate));
        Car myCar = new Car();

        UUID myTicket = superParkingBoy.park(myCar);

        assertThat(parkingLotWithLessVacancyRate.getAvailableSpaces(), is(1));
        assertThat(parkingLotWithMaxVacancyRate.getAvailableSpaces(), is(0));
        assertThat(parkingLotWithMaxVacancyRate.pick(myTicket), is(myCar));
    }
}
