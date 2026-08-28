package pl.tmobile.thread.queue.producer.cosnumer;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class Main {
          /*
        Stworz interakcje miedzy producentem i konsumentem przy użyciu Thread, Queue (konkretnie
        LinkedList) i synchronizacji na wspólnej kolejce. Producent generuje określoną liczbę "produktów"
        i dodaje je do wspólnej kolejki, podczas gdy konsument pobiera te produkty z kolejki i przetwarza
        je.
         */

    //kolejka nalezy do main nie konkretnoeg obiektu. Nie tworzymy obiektu Main, wiec pola uzywane przez main sa static
    // final zmienna wskazuje zawzse na tę samą kolejke. Nie znaczy ze kolejka nie jest niezmienna mozna robic add i pool
    // ale nie moge zrobic queue = new LinkedList()
    private static final Queue<String> queue = new LinkedList<>();
    private static int counter = 0;

    private static final Random random = new Random();

    public static void main(String[] args) {

        int productCount = 10;
        Thread producer = new Thread(() -> {
            for (int i = 0; i < productCount; i++) {
                try {
                    Thread.sleep(random.nextInt(6000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //synchroznied dlateog ze linkedlist nie jest bezpeiczna dla wielu wątków
                // producent i consumer moga jednoczesnie dotknac kolejki
                //!! To znaczy ze tylko jeden wątek naraz może wykonać kod w tym bloku
                // dla tej kolejki
                synchronized (queue) {
                    queue.offer("product" + i);
                }
            }
        });

        Thread consumer = new Thread(() -> {

            int productLeft = productCount;

            while (productLeft > 0) {
                counter++;

                String product;

                synchronized (queue) {
                    if (queue.isEmpty()) {
                        continue;
                    }
                    product = queue.poll();
                }
                productLeft--;
                System.out.println(product);
                System.out.println(counter);
            }

        });

        producer.start();
        consumer.start();


    }
}
