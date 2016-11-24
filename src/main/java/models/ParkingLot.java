package models;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ParkingLot {
    private final int size;
    private Map<UUID, Car> cars;

    public ParkingLot(int size) {
        this.size = size;
        this.cars = new HashMap<>();
    }

    public UUID park(Car car) {
        if (hasEmptySpaces()) {
            UUID uuid = UUID.randomUUID();
            cars.put(uuid, car);
            return uuid;
        }
        return null;
    }

    public boolean hasEmptySpaces() {
        return getEmptySpaces() > 0;
    }

    public Car pick(UUID token) {
        return cars.containsKey(token) ? cars.remove(token) : null;
    }

    public boolean contains(UUID token) {
        return cars.containsKey(token);
    }

    public int getEmptySpaces() {
        return size - cars.size();
    }

    public double getVacancyRate() {
        return size != 0 ? getEmptySpaces() * 1.0 / size : 0;
    }
}
