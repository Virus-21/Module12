package Task2;

import java.util.ArrayList;
import java.util.List;


public class Task2 {
    public static void main(String[] args) {
        System.out.println(fizzBuzz(15));
    }

    public static String fizzBuzz(int number) {
        int size = number + 1;
        StringBuilder sb = new StringBuilder();
        NumberProcessor npFizz = new NumberProcessor((n) -> {
            if (n % 3 == 0 && n % 5 != 0) {
                sb.append("Fizz" + ", ");
            }
        });

        NumberProcessor npBazz = new NumberProcessor((n) -> {
            if (n % 5 == 0 && n % 3 != 0) {
                sb.append("Buzz" + ", ");
            }
        });

        NumberProcessor npFizzBAzz = new NumberProcessor((n) -> {
            if (n % 5 == 0 && n % 3 == 0) {
                sb.append("FizzBuzz" + ", ");
            }
        });

        NumberProcessor npNotFizzBAzz = new NumberProcessor((n) -> {
            if (n % 5 != 0 && n % 3 != 0) {
                sb.append(n + ", ");
                ;
            }
        });
        List<NumberProcessor> threads = new ArrayList<>();
        threads.add(npBazz);
        threads.add(npFizz);
        threads.add(npFizzBAzz);
        threads.add(npNotFizzBAzz);
        for (NumberProcessor t : threads) {
            t.start();
        }
        for (int i = 1; i < size; i++) {
            for (NumberProcessor t : threads) {
                t.process(i);
            }
            while (true) {
                int processed = 0;
                for (NumberProcessor t : threads) {
                    if (t.isNProcessed()) {
                        processed++;
                    }
                }
                if (processed == 4) {
                    break;
                }
            }
        }
        for (NumberProcessor t : threads) {
            t.setAliveFalse();
        }
        return sb.toString();
    }

}
