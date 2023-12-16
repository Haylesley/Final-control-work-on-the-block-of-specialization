package animals;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public abstract class Animal implements Serializable {
    private String name;
    private int age;
    protected Map<String, String> commands;

    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
        this.commands = new HashMap<>();
    }

    public abstract String classify();

    public void listCommands() {
        System.out.println(name + " знает следующие команды:");
        commands.forEach((key, value) -> System.out.println(key + ": " + value));
    }

    public void teach(String command) {
        commands.put(command, "Выполняет команду: " + command);
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }
}
