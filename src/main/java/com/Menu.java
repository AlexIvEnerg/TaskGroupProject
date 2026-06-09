package com;

import com.util.FileWriterHelper;
import com.model.Car;
import com.model.CarStorage;
import com.comparator.CarPowerComparator;
import com.comparator.CarModelComparator;
import com.comparator.CarYearComparator;
import com.sort.SelectionSort;
import com.sort.EvenOddSort;
import com.filler.ManualFiller;
import com.filler.RandomFiller;
import com.filler.FileFiller;

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
                default -> System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
    }

    private void fillArrayMenu() {
        System.out.println("\n--- ВЫБОР СПОСОБА ЗАПОЛНЕНИЯ ---");
        System.out.println("1. Вручную");
        System.out.println("2. Случайно");
        System.out.println("3. Из файла");
        System.out.print("Ваш выбор: ");

        int fillChoice = scanner.nextInt();
        System.out.print("Введите длину массива: ");
        int length = scanner.nextInt();
        scanner.nextLine();

        CarStorage carStorage;

        switch (fillChoice) {
            case 1 -> {
                ManualFiller manualFiller = new ManualFiller(scanner);
                carStorage = manualFiller.fill(length);
            }
            case 2 -> {
                RandomFiller randomFiller = new RandomFiller();
                carStorage = randomFiller.fill(length);
            }
            case 3 -> {
                System.out.print("Введите имя файла: ");
                String fileName = scanner.nextLine();
                try {
                    FileFiller fileFiller = new FileFiller(fileName);
                    carStorage = fileFiller.fill(length);
                } catch (RuntimeException e) {
                    System.out.println("Ошибка: файл '" + fileName + "' не найден.");
                    return;
                }
            }
            default -> {
                System.out.println("Неверный выбор.");
                return;
            }
        }

        currentArray = carStorage.getCars().toArray(new Car[0]);
        System.out.println("Массив успешно заполнен!");
    }

    private void sortArrayMenu() {
        if (currentArray == null || currentArray.length == 0) {
            System.out.println("Массив пуст. Сначала заполните его (пункт 1).");
            return;
        }

        System.out.println("\n--- ВЫБОР ПОЛЯ ДЛЯ СОРТИРОВКИ ---");
        System.out.println("1. По мощности");
        System.out.println("2. По модели");
        System.out.println("3. По году выпуска");
        System.out.println("4. По мощности (чётные/нечётные)");
        System.out.print("Ваш выбор: ");

        int fieldChoice = getIntInput();

        java.util.Comparator<Car> comparator;

        switch (fieldChoice) {
            case 1 -> comparator = new CarPowerComparator();
            case 2 -> comparator = new CarModelComparator();
            case 3 -> comparator = new CarYearComparator();
            case 4 -> {
                EvenOddSort evenOddSorter = new EvenOddSort();
                evenOddSorter.evenOddSort(currentArray, new CarPowerComparator());

                System.out.println("\n--- ПОСЛЕ СОРТИРОВКИ ---");
                showArray();

                FileWriterHelper.writeCarsToFile(currentArray);
                return;
            }
            default -> {
                System.out.println("Неверный выбор.");
                return;
            }
        }

        System.out.println("\n--- ДО СОРТИРОВКИ ---");
        showArray();

        SelectionSort sorter = new SelectionSort();
        sorter.sort(currentArray, comparator);

        System.out.println("\n--- ПОСЛЕ СОРТИРОВКИ ---");
        showArray();

        FileWriterHelper.writeCarsToFile(currentArray);
    }

    private void showArray() {
        if (currentArray == null || currentArray.length == 0) {
            System.out.println("Массив пуст.");
            return;
        }

        for (int i = 0; i < currentArray.length; i++) {
            System.out.println((i + 1) + ". " + currentArray[i]);
        }
    }

    private int getIntInput() {
        return scanner.nextInt();
    }
}