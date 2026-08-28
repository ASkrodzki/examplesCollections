package pl.tmobile.thread.queue.producer.consumer;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args) throws InterruptedException {

         /*
		Stworz komunikacje Producent-Consumer miedzy watkami.
		grupa producentow:
		- jako argument dostaja folder w ktorym wyszukuja pliki (rekurencyjnie w dol) java i dostarczaja je do consumerow

		grupa konsumerow:
		- analizuja zawartosc pliku pod kątem wystąpień typow prymitywnych, tzn: ile jest intow, byteow, booleanow, double, floatow, shortow, longow
		i na ich podstawie zwiekszaja jakas mape wspolna dla wszystkich consumerow gdzie kluczem bedzie prymitow a wartoscia ilosc wystapien danego prymitowa.
		    mozna uruchomic wiele producentow i wiele consumerow,
		    consumerzy powinni zakonczyc swoje dzialanie w momencie gdy nie ma juz aktywnego producenta i nie zostalo nic do procesowania
		 */

        BlockingQueue<Path> queue = new LinkedBlockingQueue<>();
        Map<String, AtomicInteger> primitiveMap = new ConcurrentHashMap<>();

        AtomicInteger activeProducer = new AtomicInteger(2);

        primitiveMap.put("int", new AtomicInteger(0));
        primitiveMap.put("byte", new AtomicInteger(0));
        primitiveMap.put("boolean", new AtomicInteger(0));
        primitiveMap.put("double", new AtomicInteger(0));
        primitiveMap.put("float", new AtomicInteger(0));
        primitiveMap.put("short", new AtomicInteger(0));
        primitiveMap.put("long", new AtomicInteger(0));
        primitiveMap.put("char", new AtomicInteger(0));

        ExecutorService pool = Executors.newFixedThreadPool(6);

        Producent producent1 = new Producent(
                Path.of("src/main/java/pl/tmobile/stream/examples"), queue, activeProducer
        );

        Producent producent2 = new Producent(
                Path.of("src/main/java/pl/tmobile/thread/queue/author/writer/blocking/queue/ex"), queue, activeProducer
        );

        pool.submit(producent1);
        pool.submit(producent2);

        Consumer consumer1 = new Consumer(queue, primitiveMap, activeProducer);
        Consumer consumer2 = new Consumer(queue, primitiveMap, activeProducer);

        pool.submit(consumer1);
        pool.submit(consumer2);

        pool.shutdown();
        pool.awaitTermination(30000, TimeUnit.MILLISECONDS);

//        Producent producent = new Producent(Path.of("src"), queue);
//        Consumer consumer = new Consumer(queue, primitiveMap);
//
//        Thread producentThread = new Thread(producent);
//        Thread consumerThread = new Thread(consumer);
//        producentThread.start();
//        producentThread.join();
//
//        consumerThread.start();
//        consumerThread.join();join


    }
}
