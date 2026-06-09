package ru.javarush;

import ru.javarush.filler.ManualFiller;
import ru.javarush.filler.RandomFiller;
import ru.javarush.model.Car;
import ru.javarush.sort.SelectionSort;
import ru.javarush.comparator.*;
import ru.javarush.util.FileWriterHelper;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Menu {

    private Scanner scanner = new Scanner(System.in);
    private Car[] currentArray;

    public void start() {
        while (true) {
            System.out.println("\n═══════════════════════════════════");
            System.out.println("         ГЛАВНОЕ МЕНЮ");
            System.out.println("═══════════════════════════════════");
            System.out.println("1. Заполнить массив автомобилей");
            System.out.println("2. Отсортировать массив");
            System.out.println("3. Показать текущий массив");
            System.out.println("4. Выход");
            System.out.print("Ваш выбор: ");

            int choice = getIntInput();

            switch (choice) {
                case 1 -> fillArrayMenu();
                case 2 -> sortArrayMenu();
                case 3 -> showArray();
                case 4 -> {
                    System.out.println("До свидания!");
                    return;
                }
                default -> System.out.println("Неверный выбор");
            }
        }
    }

    private void fillArrayMenu() {
        System.out.println("\n1. Вручную\n2. Случайно");
        int choice = getIntInput();

        System.out.print("Размер: ");
        int size = getIntInput();

        List<Car> list;

        switch (choice) {
            case 1 -> list = new ManualFiller(scanner).fill(size);
            case 2 -> list = new RandomFiller().fill(size);
            default -> {
                System.out.println("Ошибка");
                return;
            }
        }

        currentArray = list.toArray(new Car[0]);
        System.out.println("Заполнено!");
    }

    private void sortArrayMenu() {
        if (currentArray == null || currentArray.length == 0) {
            System.out.println("Пусто");
            return;
        }

        System.out.println("\n1. Power\n2. Model\n3. Year");
        int choice = getIntInput();

        Comparator<Car> comparator = switch (choice) {
            case 1 -> new CarPowerComparator();
            case 2 -> new CarModelComparator();
            case 3 -> new CarYearComparator();
            default -> null;
        };

        if (comparator == null) return;

        new SelectionSort().sort(currentArray, comparator);

        System.out.println("Отсортировано");
        FileWriterHelper.writeCarsToFile(currentArray);
    }

    private void showArray() {
        if (currentArray == null || currentArray.length == 0) {
            System.out.println("Пусто");
            return;
        }

        for (int i = 0; i < currentArray.length; i++) {
            System.out.println((i + 1) + ". " + currentArray[i]);
        }
    }

    private int getIntInput() {
        while (!scanner.hasNextInt()) {
            System.out.print("Введите число: ");
            scanner.next();
        }
        int res = scanner.nextInt();
        scanner.nextLine();
        return res;
    }
}