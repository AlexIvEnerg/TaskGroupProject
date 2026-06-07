package ru.javarush;

import menu.Menu;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Car.CarBuilder carBuilder = new Car.CarBuilder();

        Car car = carBuilder.setModel("NISSAN").setPower(107).setReleaseYear(LocalDate.of(1999, 1, 1)).build();

        System.out.println(car);

        Menu menu = new Menu();

        menu.start();
    }
}