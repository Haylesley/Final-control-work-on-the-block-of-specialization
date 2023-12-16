package animals;
import java.io.Serializable;


public class Dog extends Animal implements Serializable {
    public Dog(String name, int age) {
        super(name, age);
    }

    @Override
    public String classify() {
        return "Собака относится к классу Млекопитающие";
    }
}
