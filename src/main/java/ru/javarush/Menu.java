package ru.javarush;

import java.util.Scanner;

public class Menu {
    private Scanner scanner = new Scanner(System.in);
    private Object[] currentArray;
    private int currentLength = 0;
    
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
        int fillChoice = getIntInput();
        System.out.println("Введите длину массива: ");
        currentLength = getIntInput();
        currentArray = new Object[currentLength];

        switch (fillChoice) {
            case 1: currentArray = new ManualFiller().fill(currentLength);
            break;
            case 2: currentArray = new RandomFiller().fill(currentLength);
            break;
            case 3: currentArray = new FileFiller().fill(currentLength);
            break;
        }
        // TODO: после реализации классов Car и Filler
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
        System.out.print("Ваш выбор: ");
        
        int fieldChoice = getIntInput();
        
        // TODO: после реализации Comparator
        // Comparator<Object> comparator = null;
        // switch (fieldChoice) {
        //     case 1: comparator = new PowerComparator(); break;
        //     case 2: comparator = new ModelComparator(); break;
        //     case 3: comparator = new YearComparator(); break;
        // }
        
        System.out.println("\n--- ДО СОРТИРОВКИ ---");
        showArray();
        
        // TODO: после реализации SortStrategy
        // new SelectionSortStrategy().sort(currentArray, comparator);
        
        System.out.println("\n--- ПОСЛЕ СОРТИРОВКИ ---");
        showArray();
        System.out.println("\nСортировка завершена!");
    }
    
    private void showArray() {
        if (currentArray == null || currentArray.length == 0) {
            System.out.println("Массив пуст. Сначала заполните его (пункт 1).");
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
        int result = scanner.nextInt();
        scanner.nextLine();
        return result;
    }
}

/* class ManualFiller {
    int currentLength;
    Car[] currentArray;
    public ManualFiller(int currentLength) {
        this.currentLength = currentLength;
    }
    currentArray = new Car[]()

    public Car[] fill() {
    }
} */