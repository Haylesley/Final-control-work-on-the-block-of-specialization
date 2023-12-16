import java.io.Serializable;

public class Counter implements AutoCloseable, Serializable {
    private int count;

    public Counter() {
        this.count = 1;
    }

    public void add() {
        count++;
    }

    public int getCount() {
        return count;
    }

    @Override
    public void close() throws Exception {
        if (count > 1) {
            throw new IllegalStateException("Ресурс не был закрыт в ресурсном блоке try-with-resources.");
        }
        System.out.println("Ресурс успешно закрыт.");
    }
}
