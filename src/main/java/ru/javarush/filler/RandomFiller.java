package ru.javarush.filler;

import ru.javarush.model.Car;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class RandomFiller implements DataFiller {
    private final Random random = new Random();
    private static final List<String> MODELS = List.of("Acura", "Alfa Romeo", "Aston Martin", "Audi", "Bentley", "BMW", "Skoda", "Smart", "Subaru", "Suzuki", "Tesla", "Toyota", "Volkswagen", "Volvo");

    public List<Car> fill(int var1) {
        return IntStream.range(0, var1).mapToObj((var1x) -> (new Car.CarBuilder()).setModel(this.getRandomModel()).setPower(this.random.nextInt(50, 301)).setReleaseYear(LocalDate.now().minusDays((long)this.random.nextInt(10000))).build()).toList();
    }

    private String getRandomModel() {
        return (String)MODELS.get(this.random.nextInt(MODELS.size()));
    }
}

