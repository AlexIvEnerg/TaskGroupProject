package com.sort;

import com.model.Car;
import java.util.Comparator;

public class EvenOddSort {

    public void evenOddSort(Car[] cars, Comparator<Car> comparator) {
        for (int i = 0; i < cars.length - 1; i++) {
            if (cars[i].getPower() % 2 != 0) {
                continue;
            } else {
                int minIndex = i;

                for (int j = i + 1; j < cars.length; j++) {
                    if (cars[j].getPower() % 2 == 0) {
                        if (comparator.compare(cars[j], cars[minIndex]) < 0) {
                            minIndex = j;
                        }
                    }
                }

                if (minIndex != i) {
                    Car temp = cars[i];
                    cars[i] = cars[minIndex];
                    cars[minIndex] = temp;
                }
            }
        }
    }
}