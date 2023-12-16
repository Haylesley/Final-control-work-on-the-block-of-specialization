package animals;
import java.io.Serializable;

public class Camel extends Animal implements Serializable {
    public Camel(String name, int age) {
        super(name, age);
    }

    @Override
    public String classify() {
        return "Верблюд относится к классу Млекопитающие";
    }
}
