package Task1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class Task1 {
    public static void main(String[] args) {
        AtomicLong startTime = new AtomicLong(System.currentTimeMillis() / 1000);

        Thread thread1 = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Минуло 5 секунд");
            }
        });
        AtomicInteger n = new AtomicInteger();
        Thread thread2 = new Thread(() -> {
            while (true) {
                n.getAndIncrement();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Минуло від запуску програми " + (System.currentTimeMillis() / 1000 - startTime.get()));
            }
        });
        List<Thread> threads = new ArrayList<>();
        threads.add(thread1);
        threads.add(thread2);
        for (Thread thread : threads) {
            thread.start();
        }
    }
}
