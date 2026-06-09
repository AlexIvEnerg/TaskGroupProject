# TaskGroupProject - Ветка Kasyanov-Denis

## Автор
Денис Касьянов

## Описание проекта
Консольное приложение для управления массивом автомобилей. Реализовано заполнение массива (вручную, случайно, из файла), сортировка по трём полям с использованием паттерна Strategy, запись результатов в файл, ручные тесты.

---

## Технологии
- Java 17
- Maven (структура проекта)
- Git

---

## Функционал

### Основное меню
1. **Заполнить массив автомобилей** — выбор способа (ручной/случайный/из файла) и длины массива
2. **Отсортировать массив** — выбор поля для сортировки (мощность/модель/год) и алгоритма
3. **Показать текущий массив** — вывод всех автомобилей
4. **Выход** — завершение программы

### Паттерн Strategy
- Интерфейс `Comparator<Car>` — стратегия сравнения
- Конкретные стратегии: `CarPowerComparator`, `CarModelComparator`, `CarYearComparator`
- Контекст: `SelectionSort.sort(Car[] cars, Comparator<Car> comparator)`

### Дополнительные задания
| ДЗ | Описание | Реализация |
|----|----------|------------|
| 1 | Сортировка чётных/нечётных значений | `EvenOddSort.java`, пункт 4 в меню |
| 2 | Запись в файл в режиме добавления | `FileWriterHelper.java`, файл `sorted_cars.txt` |
| 3 | Заполнение через стримы | `IntStream` в `ManualFiller`, `RandomFiller` |
| 3* | Кастомная коллекция | `CarStorage.java` |

---

## Структура проекта
src/main/java/ru/javarush/
├── Main.java # Точка входа
├── Menu.java # Главное меню (цикл, выбор действий)
├── comparator/ # Стратегии сравнения
│ ├── CarModelComparator.java
│ ├── CarPowerComparator.java
│ └── CarYearComparator.java
├── filler/ # Заполнение массива
│ ├── DataFiller.java # Интерфейс
│ ├── ManualFiller.java # Ручной ввод
│ ├── RandomFiller.java # Случайные данные
│ └── FileFiller.java # Чтение из файла
├── model/ # Модели данных
│ ├── Car.java # Класс Car с Builder и валидацией
│ └── CarStorage.java # Кастомная коллекция (ДЗ-3*)
├── sort/ # Алгоритмы сортировки
│ ├── SelectionSort.java # Сортировка выбором (контекст Strategy)
│ └── EvenOddSort.java # Чётные/нечётные (ДЗ-1)
├── util/ # Утилиты
│ └── FileWriterHelper.java # Запись в файл (ДЗ-2)
└── test/
└── CarTest.java # Ручные тесты (9/9 проходят)

src/main/resources/
└── cars.txt # Пример файла для заполнения

text

---

## Запуск проекта

### Компиляция
```bash
javac -encoding UTF-8 -d . src/main/java/ru/javarush/Main.java \
    src/main/java/ru/javarush/Menu.java \
    src/main/java/ru/javarush/filler/*.java \
    src/main/java/ru/javarush/util/FileWriterHelper.java \
    src/main/java/ru/javarush/model/Car.java \
    src/main/java/ru/javarush/model/CarStorage.java \
    src/main/java/ru/javarush/comparator/*.java \
    src/main/java/ru/javarush/sort/SelectionSort.java \
    src/main/java/ru/javarush/sort/EvenOddSort.java
Запуск
bash
java -cp . ru.javarush.Main
Запуск тестов
bash
java -cp . ru.javarush.CarTest
Пример файла для заполнения (cars.txt)
Формат: модель;мощность;год-месяц-день

text
Toyota;150;2020-01-01
BMW;250;2019-05-15
Audi;200;2021-03-10
Тесты
Тест	Результат
Car создаётся корректно	✅
null model rejected	✅
low power rejected	✅
PowerComparator работает	✅
ModelComparator работает	✅
YearComparator работает	✅
SelectionSort по мощности	✅
SelectionSort по модели	✅
SelectionSort по году	✅
Результат: 9/9 тестов пройдено

GitHub
Ветка: Kasyanov-Denis
Репозиторий: https://github.com/AlexIvEnerg/TaskGroupProject

Статус выполнения требований
Требование	Статус
Приложение в цикле, выход по выбору	✅
Заполнение (ручной/рандом/файл) + длина	✅
Паттерн Strategy	✅
Паттерн Builder + валидация	✅
Сортировка по 3 полям	✅
Тесты (без JUnit)	✅
ДЗ-1 (чётные/нечётные)	✅
ДЗ-2 (запись в файл)	✅
ДЗ-3 (стримы)	✅
ДЗ-3* (кастомная коллекция)	✅

