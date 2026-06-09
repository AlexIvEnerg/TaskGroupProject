package com.comparator;

import com.model.Car;

import java.util.Comparator;

public class CarPowerComparator implements Comparator<Car> {

    @Override
    public int compare(Car o1, Car o2) {
        return Integer.compare(o1.getPower(), o2.getPower());
    }
}