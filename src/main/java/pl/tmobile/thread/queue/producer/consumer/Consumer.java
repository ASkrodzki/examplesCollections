package pl.tmobile.thread.queue.producer.consumer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Consumer implements Runnable {

    private BlockingQueue<Path> queue;
    private Map<String, AtomicInteger> primitiveMap;
    private final AtomicInteger activeProducers;

    public Consumer(BlockingQueue<Path> queue, Map<String, AtomicInteger> primitiveMap, AtomicInteger activeProducers) {
        this.queue = queue;
        this.primitiveMap = primitiveMap;
        this.activeProducers = activeProducers;
    }

    @Override
    public void run() {

        while (activeProducers.get() > 0 || !queue.isEmpty()) {

            try {
                Path path = queue.poll(200, TimeUnit.MILLISECONDS);

                if (path == null) {
                    continue;
                }

                String data = Files.readString(path);

                for (String primitive : primitiveMap.keySet()) {
                    int count = countPrimitive(data, primitive);
                    primitiveMap.get(primitive).addAndGet(count);

                    System.out.println(Thread.currentThread().getName() +
                            " - " + path.getFileName() + " - " + primitive + " - " + count);
                }

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    private int countPrimitive(String data, String primitive) {
        Pattern pattern = Pattern.compile("\\b" + primitive + "\\b");
        Matcher matcher = pattern.matcher(data);

        int counter = 0;

        while (matcher.find()) {
            counter++;
        }
        return counter;
    }

    public AtomicInteger getActiveProducers() {
        return activeProducers;
    }

    public BlockingQueue<Path> getQueue() {
        return queue;
    }

    public void setQueue(BlockingQueue<Path> queue) {
        this.queue = queue;
    }

    public Map<String, AtomicInteger> getPrimitiveMap() {
        return primitiveMap;
    }

    public void setPrimitiveMap(Map<String, AtomicInteger> primitiveMap) {
        this.primitiveMap = primitiveMap;
    }

   /* private int countInts(String data) {
        int counter = 0;

        String[] words = data.split("\\s+");

        for (String word : words) {
            if ("int".equals(word)) {
                counter++;
            }
        }
        return counter;
    }

    public BlockingQueue<Path> getQueue() {
        return queue;
    }

    public void setQueue(BlockingQueue<Path> queue) {
        this.queue = queue;
    }

    */
}
