package com.comparator;

import com.model.Car;
import java.util.Comparator;

public class CarYearComparator implements Comparator<Car> {

    @Override
    public int compare(Car o1, Car o2) {
        return o1.getReleaseDate().compareTo(o2.getReleaseDate());
    }
}