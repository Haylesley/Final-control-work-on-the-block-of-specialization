package animals;

import java.io.Serializable;

public class Horse extends Animal implements Serializable {
    public Horse(String name, int age) {
        super(name, age);
    }

    @Override
    public String classify() {
        return "Лошадь относится к классу Насекомоядные";
    }
}
