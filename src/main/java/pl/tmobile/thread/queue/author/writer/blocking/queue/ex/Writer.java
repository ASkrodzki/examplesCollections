package pl.tmobile.thread.queue.author.writer.blocking.queue.ex;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Writer implements Runnable {

    private Author author;
    private AtomicInteger activeAuthors;
    private BlockingQueue<String> queue;

    public Writer(AtomicInteger activeAuthors, BlockingQueue<String> queue) {
        this.activeAuthors = activeAuthors;
        this.queue = queue;
    }

    @Override
    public void run() {
        while (activeAuthors.get() > 0 || !queue.isEmpty()) {
            try {
                String text = queue.poll(200, TimeUnit.MILLISECONDS);

                if (text == null) {
                    continue;
                }
                System.out.println(Thread.currentThread().getName() + " received: " + text);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }


}
