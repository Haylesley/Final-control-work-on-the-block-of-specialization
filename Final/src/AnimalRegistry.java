
import animals.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AnimalRegistry {
    private Map<String, Animal> animals;
    private Scanner scanner;
    private Counter counter;
    private static final String FILE_NAME = "animal_registry.txt";

    public AnimalRegistry() {
        this.animals = new HashMap<>();
        this.scanner = new Scanner(System.in);
        this.counter = new Counter();
        loadAnimalsFromFile();
    }

    public void start() {
        boolean exit = false;

        while (!exit) {
            System.out.println("Выберите действие:");
            System.out.println("1. Завести новое животное");
            System.out.println("2. Определить животное в правильный класс");
            System.out.println("3. Увидеть список команд, которые выполняет животное");
            System.out.println("4. Обучить животное новым командам");
            System.out.println("5. Выйти из программы");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    try (Counter ignored = new Counter()) {
                        addAnimal();
                    } catch (Exception e) {
                        System.err.println("Ошибка при заведении животного: " + e.getMessage());
                    }
                    break;
                case 2:
                    classifyAnimal();
                    break;
                case 3:
                    listCommands();
                    break;
                case 4:
                    teachAnimal();
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println("Неверный выбор. Попробуйте еще раз.");
            }
        }
    }

    private void addAnimal() {
        System.out.println("Введите имя животного:");
        String name = scanner.next();

        System.out.println("Введите вид животного:");
        String species = scanner.next();

        System.out.println("Введите возраст животного:");
        int age = scanner.nextInt();

        Animal animal = createAnimal(name, species, age);
        animals.put(name, animal);

        System.out.println("Животное успешно добавлено в реестр.");
        saveAnimalsToFile();
    }

    private Animal createAnimal(String name, String species, int age) {
        switch (species.toLowerCase()) {
            case "собака":
                return new Dog(name, age);
            case "кошка":
                return new Cat(name, age);
            case "хомяк":
                return new Hamster(name, age);
            case "лошадь":
                return new Horse(name, age);
            case "верблюд":
                return new Camel(name, age);
            case "осел":
                return new Donkey(name, age);
            default:
                System.out.println("Неизвестный вид животного. Животное не добавлено.");
                return null;
        }
    }

    private void classifyAnimal() {
        System.out.println("Введите имя животного, которое нужно классифицировать:");
        String name = scanner.next();

        Animal animal = animals.get(name);

        if (animal != null) {
            System.out.println("Текущий класс животного: " + animal.getClass().getSimpleName());
            System.out.println("Введите новый класс (название класса):");
            String newClass = scanner.next();

            // Пытаемся создать экземпляр животного с новым классом
            Animal newAnimal = createAnimal(name, newClass, animal.getAge());

            if (newAnimal != null) {
                animals.put(name, newAnimal);
                System.out.println("Класс животного успешно изменен.");
                saveAnimalsToFile();
            } else {
                System.out.println("Не удалось изменить класс животного. Проверьте правильность ввода.");
            }
        } else {
            System.out.println("Животное с именем " + name + " не найдено.");
        }
    }

    private void listCommands() {
        System.out.println("Введите имя животного, чтобы увидеть список команд:");
        String name = scanner.next();

        Animal animal = animals.get(name);

        if (animal != null) {
            animal.listCommands();
        } else {
            System.out.println("Животное с именем " + name + " не найдено.");
        }
    }

    private void teachAnimal() {
        System.out.println("Введите имя животного, чтобы обучить новым командам:");
        String name = scanner.next();

        Animal animal = animals.get(name);

        if (animal != null) {
            System.out.println("Введите новую команду для животного:");
            String command = scanner.next();

            animal.teach(command);

            System.out.println("Животное успешно обучено новой команде.");
            saveAnimalsToFile();
        } else {
            System.out.println("Животное с именем " + name + " не найдено.");
        }
    }

    private void loadAnimalsFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String name = parts[0];
                String species = parts[1];
                int age = Integer.parseInt(parts[2]);
                Animal animal = createAnimal(name, species, age);
                animals.put(name, animal);
            }
            System.out.println("Список животных загружен из файла.");
        } catch (FileNotFoundException e) {
            System.out.println("Файл с животными не найден. Создан новый файл.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveAnimalsToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Map.Entry<String, Animal> entry : animals.entrySet()) {
                Animal animal = entry.getValue();
                writer.println(animal.getName() + "," + animal.getClass().getSimpleName() + "," + animal.getAge());
            }
            System.out.println("Список животных сохранен в файл.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
