package ru.javarush.filler;

import model.Car;
import ru.javarush.model.CarStorage;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class RandomFiller  implements DataFiller {
    private final Random random = new Random();
    private static final List<String> MODELS=List.of("Acura", "Alfa Romeo", "Aston Martin", "Audi",
            "Bentley", "BMW","Skoda", "Smart", "Subaru", "Suzuki", "Tesla", "Toyota", "Volkswagen", "Volvo" ) ;
    @Override
    public CarStorage fill(int size) {
        List<Car> cars = IntStream.range(0,size)
                .mapToObj(i-> new Car.CarBuilder()
                        .model(getRandomModel())
                        .power(random.nextInt(50,301))
                        .releaseDate(LocalDate.now()
                                .minusDays(random.nextInt(10000)))
                        .build())
                .toList();
        return new CarStorage(cars);
    }
    private String getRandomModel() {
        return MODELS.get(random.nextInt(MODELS.size()));
    }
}
