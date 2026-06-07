package ru.javarush.sort;

import ru.javarush.model.Car;
import java.util.Comparator;

public class SelectionSort {
    public void sort(Car[] cars, Comparator<Car> comparator) {
        for (int i = 0; i < cars.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < cars.length; j++) {
                if (comparator.compare(cars[j], cars[minIndex]) < 0) {
                    minIndex = j;
                }
            }
            Car temp = cars[i];
            cars[i] = cars[minIndex];
            cars[minIndex] = temp;
        }
    }
}