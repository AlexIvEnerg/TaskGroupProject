package com.util;

import com.model.Car;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class FileWriterHelper {

    private static final String FILE_NAME = "sorted_cars.txt";

    public static void writeCarsToFile(List<Car> cars) {
        if (cars == null || cars.isEmpty()) {
            System.out.println("No data to write to file.");
            return;
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME, true))) {
            writer.println("=== Sorted cars ===");

            for (Car car : cars) {
                writer.println(car);
            }

            writer.println("=== End of record ===\n");
            System.out.println("Data written to file: " + FILE_NAME);

        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public static void writeCarsToFile(Car[] cars) {
        if (cars == null || cars.length == 0) {
            System.out.println("No data to write to file.");
            return;
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME, true))) {
            writer.println("=== Sorted cars ===");

            for (Car car : cars) {
                writer.println(car);
            }

            writer.println("=== End of record ===\n");
            System.out.println("Data written to file: " + FILE_NAME);

        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}