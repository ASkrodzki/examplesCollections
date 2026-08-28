package pl.tmobile.thread.queue.author.writer.blocking.queue.ex;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    public static void main(String[] args) {

        List<String> textList1 = List.of("Ala", "Basia", "Celina");
        List<String> textList2 = List.of("Tomek", "Arek", "Marek");
        List<String> textList3 = List.of("Java", "Thread", "Queue");

        BlockingQueue<String> queue = new LinkedBlockingQueue<>();

        int authorCount = 3;
        int writerCount = 2;
        AtomicInteger activeAuthors = new AtomicInteger(authorCount);

        ExecutorService pool = Executors.newFixedThreadPool(authorCount + writerCount);

        Author author1 = new Author(activeAuthors, textList1, queue);
        Author author2 = new Author(activeAuthors, textList2, queue);
        Author author3 = new Author(activeAuthors, textList3, queue);

        Writer writer1 = new Writer(activeAuthors, queue);
        Writer writer2 = new Writer(activeAuthors, queue);

        pool.submit(author1);
        pool.submit(author2);
        pool.submit(author3);
        pool.submit(writer1);
        pool.submit(writer2);

        pool.shutdown();


    }
}
