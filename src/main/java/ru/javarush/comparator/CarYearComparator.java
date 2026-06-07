package ru.javarush.comparator;

import ru.javarush.model.Car;
import java.util.Comparator;

public class CarYearComparator implements Comparator<Car> {

    @Override
    public int compare(Car o1, Car o2) {
        return o1.getReleaseYear().compareTo(o2.getReleaseYear());
    }
}