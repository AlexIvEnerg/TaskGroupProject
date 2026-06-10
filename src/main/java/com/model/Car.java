package com.model;


import java.time.LocalDate;

public class Car {
    /* Класс Car имеет свой класс-строитель, валидация вводимых данных,
    необходимая по условию задачи, реализована в сеттерах CarBuilder */

    private final int power;
    private final String model;
    private final LocalDate releaseDate;

    private Car(CarBuilder carBuilder) {
        this.power = carBuilder.power;
        this.model = carBuilder.model;
        this.releaseDate = carBuilder.releaseDate;
    }

    public int getPower() {
        return power;
    }

    public String getModel() {
        return model;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    @Override
    public String toString() {
        return "Car{" +
                "com.model= " + model +
                ", power= " + power +
                ", releaseYear= " + releaseDate +
                '}';
    }


    public static class CarBuilder {
        /* Билдер задает значения по умолчанию для полей, которым значение не задано сеттером. При вызове
        сеттеров есть возможность пользоваться цепочкой вызовов: ..setModel().setPower(int power).build(); */

        private int power = 100;
        private String model = "Default_model";
        private LocalDate releaseDate = LocalDate.now();


        public CarBuilder model(String model) {
            if (model == null || model.isBlank()) {
                throw new IllegalArgumentException("com.model is empty/null");
            } else if (model.length() < 1 || model.length() > 20) {
                throw new IllegalArgumentException("Model length is shorter than 1 or more than 20 symbols");
            } else {
                this.model = model;
            }
            return this;
        }

        public CarBuilder power(int power) {
            if (power > 10 && power < 300) {
                this.power = power;
            } else {
                throw new IllegalArgumentException("Power value is few than 10 or more than 300");
            }
            return this;
        }

        public CarBuilder releaseDate(LocalDate releaseDate) {
            if (releaseDate == null) {
                throw new IllegalArgumentException("Date of release can't to be null");
            } else if (releaseDate.isBefore(LocalDate.parse("1950-01-01")) ||
                    releaseDate.isAfter(LocalDate.now())) {
                throw new IllegalArgumentException("Date of release is before than 1950-01-01 or after current time");
            } else {
                this.releaseDate = releaseDate;
            }
            return this;
        }

        public Car build() {
            return new Car(this);
        }
    }
}