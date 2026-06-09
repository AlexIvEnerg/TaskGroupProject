package ru.javarush.model;

import java.util.List;

public class CarStorage {
    private final List<Car> cars;

    public CarStorage(List<Car> cars) {
        this.cars = cars;
    }

    public List<Car> getCars() {
        return cars;
    }

    @Override
    public String toString() {
        return cars.toString();
    }
}