package ru.javarush;

import ru.javarush.model.Car;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        Car.CarBuilder carBuilder = new Car.CarBuilder();

        Car car = carBuilder.setModel("").setPower(0).setReleaseYear(LocalDate.now()).build();

        System.out.println(car);

    }
}