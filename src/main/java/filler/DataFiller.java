package filler;

import ru.javarush.model.Car;
import java.util.List;

public interface DataFiller {
    List<Car> fill(int size);
}