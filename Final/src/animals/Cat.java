package animals;
import java.io.Serializable;


public class Cat extends Animal implements Serializable {
    public Cat(String name, int age) {
        super(name, age);
    }

    @Override
    public String classify() {
        return "Кошка относится к классу Млекопитающие";
    }
}
