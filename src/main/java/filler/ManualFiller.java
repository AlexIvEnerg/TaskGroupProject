package filler;

import model.Car ;
import model.CarStorage;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class ManualFiller implements DataFiller {
    private  final Scanner scanner ;
    public  ManualFiller(Scanner scanner)
    {this.scanner=scanner;
    }
    @Override
    public CarStorage fill(int size) {
        List<Car> cars = IntStream.range(0, size)
                .mapToObj(i -> createCar())
                .toList();
        return new CarStorage(cars);
    }
    private  Car createCar(){
        while (true){
            try {
                System.out.println("Model:");
                String model = scanner.nextLine();
                System.out.println("Power:");
                int power = Integer.parseInt(scanner.nextLine());
                System.out.println("Release date(yyyy-MM-dd):");
                LocalDate date = LocalDate.parse(scanner.nextLine());
                return new Car.CarBuilder()
                        .model(model)
                        .power(power)
                        .releaseDate(date)
                        .build();
            }catch(DateTimeParseException e){
                System.out.println("Date must be in yyyy-MM-dd format");
            }catch(NumberFormatException e){
                System.out.println("Power must be a number");

            }catch (Exception e){
                System.out.println(e.getMessage());
                scanner.nextLine();

            }
        }
    }
}
