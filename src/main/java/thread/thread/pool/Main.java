package thread.thread.pool;

import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws Exception {

        //pule wątków
        /*
        Callable interfejs ktory zwraca cos result albo wyjatek za pomoca call

         */

        Callable<Integer> callable = () -> {
            System.out.println("Return result");
            return -1;
        };

        System.out.println(callable.call());

        /*

        Executor service - zarzadznaia pula watkow
        new singleThread execiutor dziala na jednym watku
        new FiexThreadPool - dziala na puli watkow o zadanej wielksoci np 3
        new cachedThreadPool - w przpyadku braku wątku moze obsluzyc nowe zadanie

         */

        ExecutorService pool = Executors.newFixedThreadPool(5);

        //future reprezentacja przyszlego wyniku, dzialan naszych zdan wykonujacych sie  asynchronicz
        Future<Integer> result = pool.submit(callable);

        System.out.println(result.get());

        //ex2

        Callable<Integer> callable2 = () -> {
            Thread.sleep(1000);
            System.out.println("test2");
            return 2;
        };

        Future<Integer> result2 = pool.submit(callable2);
        System.out.println(result2.get(1500, TimeUnit.MILLISECONDS));

        Callable<Integer> callable3 = () -> {
            try {
                //sekcja krytyczna - try - wuspanie watku na sekunde
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.out.println("ending");
                return -1;
            }

            System.out.println("returning result:");
            return 67;
        };

        Future<Integer> result3 = pool.submit(callable3);

        try {
            System.out.println(result3.get(1500, TimeUnit.MILLISECONDS));
        } catch (TimeoutException e) {
            System.out.println("cancel");
            result3.cancel(true);
        }

        pool.shutdown();
    }
}
