package models;

import exceptions.NoAvailableParkingSpace;
import exceptions.NoSuchCarInParkingLot;
import org.junit.Test;

import java.util.Arrays;
import java.util.UUID;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SuperParkingBoyTest {
    @Test
    public void should_pick_the_original_car_when_park_my_car() throws NoAvailableParkingSpace, NoSuchCarInParkingLot {
        SuperParkingBoy boy = new SuperParkingBoy(Arrays.asList(new ParkingLot(1)));
        Car myCar = new Car();

        UUID myTicket = boy.park(myCar);

        assertThat(boy.pick(myTicket), is(myCar));
    }

    @Test(expected = NoAvailableParkingSpace.class)
    public void should_not_park_my_car_when_all_parking_spaces_are_full() throws NoAvailableParkingSpace {
        SuperParkingBoy boy = new SuperParkingBoy(Arrays.asList(new ParkingLot(0)));
        Car myCar = new Car();

        boy.park(myCar);
    }

    @Test(expected = NoSuchCarInParkingLot.class)
    public void should_not_pick_my_car_when_my_car_not_parking_into_parking_lot() throws NoSuchCarInParkingLot {
        SuperParkingBoy boy = new SuperParkingBoy(Arrays.asList(new ParkingLot(0)));
        UUID fakeTicket = UUID.randomUUID();

        boy.pick(fakeTicket);
    }

    @Test
    public void should_park_my_car_into_parking_lot_with_max_vacancy_rate() throws NoAvailableParkingSpace, NoSuchCarInParkingLot {
        ParkingLot parkingLotWithLessVacancyRate = new ParkingLot(2);
        parkingLotWithLessVacancyRate.park(new Car());
        ParkingLot parkingLotWithMaxVacancyRate = new ParkingLot(1);
        SuperParkingBoy boy = new SuperParkingBoy(Arrays.asList(parkingLotWithLessVacancyRate, parkingLotWithMaxVacancyRate));
        Car myCar = new Car();

        UUID myTicket = boy.park(myCar);

        assertThat(parkingLotWithLessVacancyRate.getAvailableSpaces(), is(1));
        assertThat(parkingLotWithMaxVacancyRate.getAvailableSpaces(), is(0));
        assertThat(parkingLotWithMaxVacancyRate.pick(myTicket), is(myCar));
    }
}
