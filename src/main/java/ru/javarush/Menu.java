//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package ru.javarush;

import ru.javarush.filler.ManualFiller;
import ru.javarush.filler.RandomFiller;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import ru.javarush.comparator.CarModelComparator;
import ru.javarush.comparator.CarPowerComparator;
import ru.javarush.comparator.CarYearComparator;
import ru.javarush.model.Car;
import ru.javarush.sort.SelectionSort;
import ru.javarush.util.FileWriterHelper;

public class Menu {
    private Scanner scanner;
    private Car[] currentArray;

    public Menu() {
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        while(true) {
            System.out.println("\n═══════════════════════════════════");
            System.out.println("         ГЛАВНОЕ МЕНЮ");
            System.out.println("═══════════════════════════════════");
            System.out.println("1. Заполнить массив автомобилей");
            System.out.println("2. Отсортировать массив");
            System.out.println("3. Показать текущий массив");
            System.out.println("4. Выход");
            System.out.print("Ваш выбор: ");
            int var1 = this.getIntInput();
            switch (var1) {
                case 1:
                    this.fillArrayMenu();
                    break;
                case 2:
                    this.sortArrayMenu();
                    break;
                case 3:
                    this.showArray();
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
        int var1 = this.getIntInput();
        System.out.print("Введите длину массива: ");
        int var2 = this.getIntInput();
        Object var3 = null;
        List var6;
        switch (var1) {
            case 1:
                ManualFiller var4 = new ManualFiller(this.scanner);
                var6 = var4.fill(var2);
                break;
            case 2:
                RandomFiller var5 = new RandomFiller();
                var6 = var5.fill(var2);
                break;
            case 3:
                System.out.println("Заполнение из файла временно недоступно.");
                return;
            default:
                System.out.println("Неверный выбор.");
                return;
        }

        this.currentArray = (Car[])var6.toArray(new Car[0]);
        System.out.println("Массив успешно заполнен!");
    }

    private void sortArrayMenu() {
        if (this.currentArray != null && this.currentArray.length != 0) {
            System.out.println("\n--- ВЫБОР ПОЛЯ ДЛЯ СОРТИРОВКИ ---");
            System.out.println("1. По мощности");
            System.out.println("2. По модели");
            System.out.println("3. По году выпуска");
            System.out.print("Ваш выбор: ");
            int var1 = this.getIntInput();
            Object var2 = null;
            switch (var1) {
                case 1:
                    var2 = new CarPowerComparator();
                    break;
                case 2:
                    var2 = new CarModelComparator();
                    break;
                case 3:
                    var2 = new CarYearComparator();
                    break;
                default:
                    System.out.println("Неверный выбор.");
                    return;
            }

            System.out.println("\n--- ДО СОРТИРОВКИ ---");
            this.showArray();
            SelectionSort var3 = new SelectionSort();
            var3.sort(this.currentArray, (Comparator)var2);
            System.out.println("\n--- ПОСЛЕ СОРТИРОВКИ ---");
            this.showArray();
            System.out.println("\nСортировка завершена!");
            FileWriterHelper.writeCarsToFile(this.currentArray);
        } else {
            System.out.println("Массив пуст. Сначала заполните его (пункт 1).");
        }
    }

    private void showArray() {
        if (this.currentArray != null && this.currentArray.length != 0) {
            for(int var1 = 0; var1 < this.currentArray.length; ++var1) {
                System.out.println(var1 + 1 + ". " + this.currentArray[var1]);
            }

        } else {
            System.out.println("Массив пуст. Сначала заполните его (пункт 1).");
        }
    }

    private int getIntInput() {
        while(!this.scanner.hasNextInt()) {
            System.out.print("Введите число: ");
            this.scanner.next();
        }

        int var1 = this.scanner.nextInt();
        this.scanner.nextLine();
        return var1;
    }
}
