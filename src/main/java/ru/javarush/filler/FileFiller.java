package ru.javarush.filler;

import ru.javarush.model.Car;
import ru.javarush.model.CarStorage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Objects;

public class FileFiller {

    private static final String FIELD_SEPARATOR = ";";

    public CarStorage fill(String fileName) {
        try (var lines = Files.lines(Path.of(fileName))) {
            return new CarStorage(
                    lines.filter(line -> !line.isBlank())
                            .map(this::parseCar)
                            .filter(Objects::nonNull)
                            .toList()
            );
        } catch (IOException e) {
            throw new RuntimeException("Cannot read file :" + fileName, e);
        }
    }

    private Car parseCar(String line) {
        try {
            String[] fields = line.split(FIELD_SEPARATOR);
            if (fields.length != 3) {
                throw new IllegalArgumentException("Invalid format");
            }
            return new Car.CarBuilder()
                    .setModel(fields[0].trim())
                    .setPower(Integer.parseInt(fields[1].trim()))
                    .setReleaseYear(LocalDate.parse(fields[2].trim()))
                    .build();
        } catch (Exception e) {
            System.out.println("Error parsing line: " + line);
            return null;
        }
    }
}