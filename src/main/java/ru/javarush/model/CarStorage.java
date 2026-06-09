package ru.javarush.model;

import model.Car;

import java.util.ArrayList;
import java.util.List;

public class CarStorage {
    private final List<model.Car> cars ;
    public CarStorage(List<model.Car> cars) {
        this.cars = new ArrayList<>(cars);
    }
    public List<model.Car> getCars() {
        return cars;
    }
    public model.Car[] toArray() {
        return cars.toArray(new Car[0]) ;
    }
}

