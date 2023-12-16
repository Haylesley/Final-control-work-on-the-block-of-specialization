package animals;
import java.io.Serializable;


public class Donkey extends Animal implements Serializable {
    public Donkey(String name, int age) {
        super(name, age);
    }

    @Override
    public String classify() {
        return "Осел относится к классу Насекомоядные";
    }
}
