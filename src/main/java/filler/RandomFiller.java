package filler;

import ru.javarush.model.Car;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class RandomFiller implements DataFiller {
    private final Random random = new Random();
    private static final List<String> MODELS = List.of("Acura", "Alfa Romeo", "Aston Martin", "Audi",
            "Bentley", "BMW", "Skoda", "Smart", "Subaru", "Suzuki", "Tesla", "Toyota", "Volkswagen", "Volvo");
    
    @Override
    public List<Car> fill(int size) {
        return IntStream.range(0, size)
                .mapToObj(i -> new Car.CarBuilder()
                        .setModel(getRandomModel())
                        .setPower(random.nextInt(50, 301))
                        .setReleaseYear(LocalDate.now().minusDays(random.nextInt(10000)))
                        .build())
                .toList();
    }
    
    private String getRandomModel() {
        return MODELS.get(random.nextInt(MODELS.size()));
    }
}