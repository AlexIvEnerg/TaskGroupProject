package ru.javarush;

import ru.javarush.model.Car;
import ru.javarush.comparator.CarPowerComparator;
import ru.javarush.comparator.CarModelComparator;
import ru.javarush.comparator.CarYearComparator;
import ru.javarush.sort.SelectionSort;

import java.time.LocalDate;
import java.util.Arrays;

public class CarTest {

    private static int testsPassed = 0;
    private static int testsFailed = 0;

    public static void main(String[] args) {
        System.out.println("=== RUNNING CAR TESTS ===\n");

        testCarBuilder();
        testCarBuilderValidation();
        testPowerComparator();
        testModelComparator();
        testYearComparator();
        testSelectionSortByPower();
        testSelectionSortByModel();
        testSelectionSortByYear();

        System.out.println("\n=== TEST RESULTS ===");
        System.out.println("Passed: " + testsPassed);
        System.out.println("Failed: " + testsFailed);
    }

    // 1. Тест Car.Builder на корректное создание
    private static void testCarBuilder() {
        try {
            Car car = new Car.CarBuilder()
                    .setModel("Toyota")
                    .setPower(150)
                    .setReleaseYear(LocalDate.of(2020, 1, 1))
                    .build();
            if (car.getModel().equals("Toyota") && car.getPower() == 150 && car.getReleaseYear().equals(LocalDate.of(2020, 1, 1))) {
                System.out.println("  OK: Car created correctly");
                testsPassed++;
            } else {
                System.out.println("  FAIL: Car fields don't match");
                testsFailed++;
            }
        } catch (Exception e) {
            System.out.println("  FAIL: Exception on correct data: " + e.getMessage());
            testsFailed++;
        }
    }

    // 2. Тест валидации Car.Builder (негативные сценарии)
    private static void testCarBuilderValidation() {
        boolean failed = false;
        try {
            new Car.CarBuilder().setModel(null).build();
            System.out.println("  FAIL: null model not rejected");
            testsFailed++;
            failed = true;
        } catch (IllegalArgumentException e) {
            // expected
        }
        if (!failed) {
            System.out.println("  OK: null model rejected");
            testsPassed++;
        }

        failed = false;
        try {
            new Car.CarBuilder().setPower(5).build();
            System.out.println("  FAIL: too low power not rejected");
            testsFailed++;
            failed = true;
        } catch (IllegalArgumentException e) {
            // expected
        }
        if (!failed) {
            System.out.println("  OK: low power rejected");
            testsPassed++;
        }
    }

    // 3. Тест CarPowerComparator
    private static void testPowerComparator() {
        CarPowerComparator comp = new CarPowerComparator();
        Car car1 = new Car.CarBuilder().setModel("A").setPower(100).setReleaseYear(LocalDate.now()).build();
        Car car2 = new Car.CarBuilder().setModel("B").setPower(200).setReleaseYear(LocalDate.now()).build();
        if (comp.compare(car1, car2) < 0) {
            System.out.println("  OK: PowerComparator works");
            testsPassed++;
        } else {
            System.out.println("  FAIL: PowerComparator failed");
            testsFailed++;
        }
    }

    // 4. Тест CarModelComparator
    private static void testModelComparator() {
        CarModelComparator comp = new CarModelComparator();
        Car audi = new Car.CarBuilder().setModel("Audi").setPower(100).setReleaseYear(LocalDate.now()).build();
        Car bmw = new Car.CarBuilder().setModel("BMW").setPower(100).setReleaseYear(LocalDate.now()).build();
        if (comp.compare(audi, bmw) < 0) {
            System.out.println("  OK: ModelComparator works");
            testsPassed++;
        } else {
            System.out.println("  FAIL: ModelComparator failed");
            testsFailed++;
        }
    }

    // 5. Тест CarYearComparator
    private static void testYearComparator() {
        CarYearComparator comp = new CarYearComparator();
        Car oldCar = new Car.CarBuilder().setModel("Old").setPower(100).setReleaseYear(LocalDate.of(2000, 1, 1)).build();
        Car newCar = new Car.CarBuilder().setModel("New").setPower(100).setReleaseYear(LocalDate.of(2020, 1, 1)).build();
        if (comp.compare(oldCar, newCar) < 0) {
            System.out.println("  OK: YearComparator works");
            testsPassed++;
        } else {
            System.out.println("  FAIL: YearComparator failed");
            testsFailed++;
        }
    }

    // 6. Тест SelectionSort (по мощности)
    private static void testSelectionSortByPower() {
        Car[] cars = {
            new Car.CarBuilder().setModel("BMW").setPower(299).setReleaseYear(LocalDate.now()).build(),
            new Car.CarBuilder().setModel("Audi").setPower(100).setReleaseYear(LocalDate.now()).build(),
            new Car.CarBuilder().setModel("Toyota").setPower(200).setReleaseYear(LocalDate.now()).build()
        };
        new SelectionSort().sort(cars, new CarPowerComparator());
        if (cars[0].getPower() == 100 && cars[1].getPower() == 200 && cars[2].getPower() == 299) {
            System.out.println("  OK: SelectionSort by Power works");
            testsPassed++;
        } else {
            System.out.println("  FAIL: SelectionSort by Power failed");
            testsFailed++;
        }
    }

    // 7. Тест SelectionSort (по модели)
    private static void testSelectionSortByModel() {
        Car[] cars = {
            new Car.CarBuilder().setModel("BMW").setPower(100).setReleaseYear(LocalDate.now()).build(),
            new Car.CarBuilder().setModel("Audi").setPower(100).setReleaseYear(LocalDate.now()).build(),
            new Car.CarBuilder().setModel("Toyota").setPower(100).setReleaseYear(LocalDate.now()).build()
        };
        new SelectionSort().sort(cars, new CarModelComparator());
        if (cars[0].getModel().equals("Audi") && cars[1].getModel().equals("BMW") && cars[2].getModel().equals("Toyota")) {
            System.out.println("  OK: SelectionSort by Model works");
            testsPassed++;
        } else {
            System.out.println("  FAIL: SelectionSort by Model failed");
            testsFailed++;
        }
    }

    // 8. Тест SelectionSort (по году)
    private static void testSelectionSortByYear() {
        Car[] cars = {
            new Car.CarBuilder().setModel("BMW").setPower(100).setReleaseYear(LocalDate.of(2020, 1, 1)).build(),
            new Car.CarBuilder().setModel("Audi").setPower(100).setReleaseYear(LocalDate.of(2010, 1, 1)).build(),
            new Car.CarBuilder().setModel("Toyota").setPower(100).setReleaseYear(LocalDate.of(2015, 1, 1)).build()
        };
        new SelectionSort().sort(cars, new CarYearComparator());
        if (cars[0].getReleaseYear().equals(LocalDate.of(2010, 1, 1)) &&
            cars[1].getReleaseYear().equals(LocalDate.of(2015, 1, 1)) &&
            cars[2].getReleaseYear().equals(LocalDate.of(2020, 1, 1))) {
            System.out.println("  OK: SelectionSort by Year works");
            testsPassed++;
        } else {
            System.out.println("  FAIL: SelectionSort by Year failed");
            testsFailed++;
        }
    }
}