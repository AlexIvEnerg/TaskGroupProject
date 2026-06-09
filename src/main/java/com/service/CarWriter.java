package com.service;

import com.model.Car;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Collection;

public class CarWriter {
    private static final String FIELD_SEPARATOR = ";";
    public void appendCars(Collection<Car> cars,String fileName){
        try {
            StringBuilder builder = new StringBuilder();
            for (Car car : cars) {
                builder.append(car.getModel())
                        .append(FIELD_SEPARATOR)
                        .append(car.getPower())
                        .append(FIELD_SEPARATOR)
                        .append(car.getReleaseDate())
                        .append(System.lineSeparator());

            }
            Files.writeString(Path.of(fileName),builder.toString(), StandardOpenOption.CREATE,StandardOpenOption.APPEND);

        }catch (IOException e){
            throw new RuntimeException("Cannot write file",e);
        }

    }
}
