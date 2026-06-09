package com.filler;

import com.model.Car;
import com.model.CarStorage;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Objects ;

public class FileFiller implements  DataFiller {

    private static final String FIELD_SEPARATOR = ";" ;
    private final String fileName ;
    public FileFiller(String fileName) {
        this.fileName = fileName ;
    }
    @Override
    public CarStorage fill(int size) {
        try (var lines=Files.lines(Path.of(fileName))) {
            return new CarStorage(
                    lines.filter(line->!line.isBlank())
                    .map(this::parseCar)
                    .filter(Objects::nonNull)
                            .limit(size)
                    .toList()
            );
        }catch (IOException e){
            throw new RuntimeException("Cannot read file :"+fileName ,e);

        }

    }
private  Car parseCar(String line){
       try {
           String[] fields = line.split(FIELD_SEPARATOR);
           if (fields.length != 3){
           throw new IllegalArgumentException("Invalid format");
           }
           return new Car.CarBuilder()
                   .model(fields[0].trim())
                   .power(Integer.parseInt(fields[1].trim()))
                   .releaseDate(LocalDate.parse(fields[2].trim()))
                   .build();
       } catch (Exception e) {
           System.out.println("Error parsing line:"+line);
           return null;
       }
}

}
