package model;

import java.time.LocalDate;

public class Car{
    /*
    Класс Car имеет свой класс-строитель CarBuilder, с возможностью создания
    экземпляра Car с рандомными данными (по условию задачи "должна быть
    возможность заполнения массива из файла, рандом, вручную")
     */
    private final int power;
    private final String model;
    private final LocalDate releaseDate; // год както не в кассу у нас же полная дата?
    private Car(CarBuilder carBuilder) {
        this.power = carBuilder.power;
        this.model = carBuilder.model;
        this.releaseDate = carBuilder.releaseDate;
    }
    public int getPower() {
        return power;
    }
    public String getModel() {
        return model;
    }
    public LocalDate getReleaseDate() {
        return releaseDate;
    }
    @Override
    public String toString() {
        return "Car{" +
                "model= " + model +
                ", power= " + power +
                ", releaseYear= " + releaseDate +"}" ; // кавычки  не те  ? идея подчеркнула
    }
    public static class CarBuilder {
        private Integer power ; // не заполняем  параметры дефолтных занчений  чтобы при отсутствии данных не создавались невалидные обьекты?
        private String model ;//тоже самое соответсвенно будут нулы  и будет эксепшен .
        private LocalDate releaseDate ;//
        //  public CarBuilder() {} конструктор удалить т.к. пустой конструктор создается в джаве по умолчанию .
        public CarBuilder model(String model) { //уберу все сеты .это не метод сет поидее .нас заругать могут.да и писанины меньше при заполнении.
            //полностью переписал блок валидации там была ошибка с нулюм логикой и нет проверки на заполнение пробелами.а также ранний эксепшен
            if (model ==null || model.isBlank()) {
                throw new IllegalArgumentException("Model empty");
            }
            if (model.length()<20 && model.length()>1) {
                throw new IllegalArgumentException("Model length incorrect ");
            }
            this.model = model;
            return this;
        }
        public CarBuilder power(int power) {
            if (power < 10 || power > 300) {
                throw new IllegalArgumentException("Power input is wrong");
            }
            this.power = power;
            return this;
        }
        public CarBuilder releaseDate(LocalDate releaseDate) {
            if (releaseDate ==null) {
                throw new IllegalArgumentException("Release date empty");
            }
            if (releaseDate.isBefore(LocalDate.of(1950,1,1))) {
                throw new IllegalArgumentException("cannot be before1950") ;
            }
            if (releaseDate.isAfter(LocalDate.now())) {
                throw new IllegalArgumentException("cannot be after now") ;
            }
            this.releaseDate = releaseDate;
            return this;
        }
        public Car build() {
            if (model==null) {
                throw new IllegalArgumentException("Model is empty");
            }
            if (power==null) { // заменил изначальный инт на интеджер чтобы сделать проверку.
                throw new IllegalArgumentException("Power is empty");
            }
            if (releaseDate==null) {
                throw new IllegalArgumentException("Release date is empty");
            }
            return new Car(this);
        }
    }
}

