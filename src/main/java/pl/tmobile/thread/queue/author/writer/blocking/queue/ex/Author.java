package pl.tmobile.thread.queue.author.writer.blocking.queue.ex;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Author implements Runnable {

    private List<String> wordsList;
    private BlockingQueue<String> blockingQueue;
    private Writer writer;
    private AtomicInteger activeAuthors;

    public Author(AtomicInteger activeAuthors, List<String> wordsList, BlockingQueue<String> blockingQueue) {
        this.activeAuthors = activeAuthors;
        this.wordsList = wordsList;
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        for (String text : wordsList) {
            try {
                Thread.sleep(1000);
                System.out.println("Author put: " + text);
                blockingQueue.put(text);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                activeAuthors.decrementAndGet();
            }
        }
    }

    public List<String> getWordsList() {
        return wordsList;
    }

    public void setWordsList(List<String> wordsList) {
        this.wordsList = wordsList;
    }

    public BlockingQueue<String> getBlockingQueue() {
        return blockingQueue;
    }

    public void setBlockingQueue(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    public AtomicInteger getActiveAuthors() {
        return activeAuthors;
    }

    public void setActiveAuthors(AtomicInteger activeAuthors) {
        this.activeAuthors = activeAuthors;
    }

    public Writer getWriter() {
        return writer;
    }

    public void setWriter(Writer writer) {
        this.writer = writer;
    }


}
