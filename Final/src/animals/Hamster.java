package animals;
import java.io.Serializable;


public class Hamster extends Animal implements Serializable {
    public Hamster(String name, int age) {
        super(name, age);
    }

    @Override
    public String classify() {
        return "Хомяк относится к классу Грызуны";
    }
}
