package ru.javarush.model;

import java.time.LocalDate;

public class Car {
    /* Класс Car имеет свой класс-строитель, валидация вводимых данных,
    необходимая по условию задачи, реализована в сеттерах CarBuilder */

    private final int power;
    private final String model;
    private final LocalDate releaseYear;

    private Car(CarBuilder carBuilder) {
        this.power = carBuilder.power;
        this.model = carBuilder.model;
        this.releaseYear = carBuilder.releaseYear;
    }

    public int getPower() {
        return power;
    }

    public String getModel() {
        return model;
    }

    public LocalDate getReleaseYear() {
        return releaseYear;
    }

    @Override
    public String toString() {
        return "Car{" +
                "model= " + model +
                ", power= " + power +
                ", releaseYear= " + releaseYear +
                '}';
    }


    public static class CarBuilder {

        private int power = 100;
        private String model = "Default_model";
        private LocalDate releaseYear = LocalDate.now();


        public CarBuilder setModel(String model) {
            if (model == null || model.isBlank()) {
                throw new IllegalArgumentException("model is empty/null");
            } else if (model.length() < 1 || model.length() > 20) {
                throw new IllegalArgumentException("Model length is shorter than 1 or more than 20 symbols");
            } else {
                this.model = model;
            }
            return this;
        }

        public CarBuilder setPower(int power) {
            if (power > 10 && power < 300) {
                this.power = power;
            } else {
                throw new IllegalArgumentException("Power value is few than 10 or more than 300");
            }
            return this;
        }

        public CarBuilder setReleaseYear(LocalDate releaseYear) {
            if (releaseYear == null) {
                throw new IllegalArgumentException("Date of release can't to be null");
            } else if (releaseYear.isBefore(LocalDate.parse("1950-01-01")) ||
                    releaseYear.isAfter(LocalDate.now())) {
                throw new IllegalArgumentException("Date of release is before than 1950-01-01 or after current time");
            } else {
                this.releaseYear = releaseYear;
            }
            return this;
        }

        public Car build() {
            return new Car(this);
        }
    }
}
