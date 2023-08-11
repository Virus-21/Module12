package Task2;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;

public class NumberProcessor extends Thread {

    private Consumer<Integer> processor;

    private int n;

    boolean isAlive = true;

    public void setAliveFalse() {
        this.isAlive = false;
    }

    private AtomicBoolean isNProcessed = new AtomicBoolean(true);

    public NumberProcessor(Consumer<Integer> processor) {
        this.processor = processor;
    }

    public void process(int n) {
        this.n = n;
        isNProcessed.set(false);
    }

    public boolean isNProcessed() {
        return isNProcessed.get();
    }

    @Override
    public void run() {
        while (isAlive) {
            if (isNProcessed.get()) {
                continue;
            }
            processor.accept(n);
            isNProcessed.set(true);
        }
    }
}
