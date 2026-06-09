package ru.javarush;

import ru.javarush.util.FileWriterHelper;
import ru.javarush.model.Car;
import ru.javarush.model.CarStorage;
import ru.javarush.comparator.CarPowerComparator;
import ru.javarush.comparator.CarModelComparator;
import ru.javarush.comparator.CarYearComparator;
import ru.javarush.sort.SelectionSort;
import ru.javarush.sort.EvenOddSort;
import ru.javarush.filler.ManualFiller;
import ru.javarush.filler.RandomFiller;
import ru.javarush.filler.FileFiller;

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
                case 1:
                    fillArrayMenu();
                    break;
                case 2:
                    sortArrayMenu();
                    break;
                case 3:
                    showArray();
                    break;
                case 4:
                    System.out.println("До свидания!");
                    return;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
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
    
    CarStorage carStorage = null;
    switch (fillChoice) {
        case 1:
            ManualFiller manualFiller = new ManualFiller(scanner);
            carStorage = manualFiller.fill(length);
            break;
        case 2:
            RandomFiller randomFiller = new RandomFiller();
            carStorage = randomFiller.fill(length);
            break;
        case 3:
            System.out.print("Введите имя файла: ");
            String fileName = scanner.nextLine();
            try {
                FileFiller fileFiller = new FileFiller();
                carStorage = fileFiller.fill(fileName);
            } catch (RuntimeException e) {
                System.out.println("Ошибка: файл '" + fileName + "' не найден.");
                return;
            }
            break;
        default:
            System.out.println("Неверный выбор.");
            return;
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
        
        java.util.Comparator<Car> comparator = null;
        switch (fieldChoice) {
            case 1:
                comparator = new CarPowerComparator();
                break;
            case 2:
                comparator = new CarModelComparator();
                break;
            case 3:
                comparator = new CarYearComparator();
                break;
            case 4:
                EvenOddSort evenOddSorter = new EvenOddSort();
                evenOddSorter.evenOddSort(currentArray, new CarPowerComparator());
                System.out.println("\n--- ПОСЛЕ СОРТИРОВКИ ---");
                showArray();
                System.out.println("\nСортировка завершена!");
                FileWriterHelper.writeCarsToFile(currentArray);
                return;
            default:
                System.out.println("Неверный выбор.");
                return;
        }
        
        System.out.println("\n--- ДО СОРТИРОВКИ ---");
        showArray();
        
        SelectionSort sorter = new SelectionSort();
        sorter.sort(currentArray, comparator);
        
        System.out.println("\n--- ПОСЛЕ СОРТИРОВКИ ---");
        showArray();
        System.out.println("\nСортировка завершена!");
        
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