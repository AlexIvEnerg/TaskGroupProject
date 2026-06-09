package ru.javarush.filler;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;
import ru.javarush.model.Car;

public class ManualFiller implements DataFiller {
    private final Scanner scanner;

    public ManualFiller(Scanner var1) {
        this.scanner = var1;
    }

    public List<Car> fill(int var1) {
        return IntStream.range(0, var1).mapToObj((var1x) -> this.createCar()).toList();
    }

    private Car createCar() {
        while(true) {
            try {
                System.out.println("Model:");
                String var1 = this.scanner.nextLine();
                System.out.println("Power:");
                int var2 = Integer.parseInt(this.scanner.nextLine());
                System.out.println("Release date (yyyy-MM-dd):");
                LocalDate var3 = LocalDate.parse(this.scanner.nextLine());
                return (new Car.CarBuilder()).setModel(var1).setPower(var2).setReleaseYear(var3).build();
            } catch (DateTimeParseException var4) {
                System.out.println("Date must be in yyyy-MM-dd format");
            } catch (NumberFormatException var5) {
                System.out.println("Power must be a number");
            } catch (Exception var6) {
                System.out.println(var6.getMessage());
            }
        }
    }
}