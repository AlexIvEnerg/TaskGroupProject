package model;

import java.util.ArrayList;
import java.util.List;

public class CarStorage {
    private final List<Car> cars ;
    public CarStorage(List<Car> cars) {
        this.cars = new ArrayList<>(cars);
    }
    public List<Car> getCars() {
        return cars;
    }
    public Car[] toArray() {
        return cars.toArray(new Car[0]) ;
    }
}

