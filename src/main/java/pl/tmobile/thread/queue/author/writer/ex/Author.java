package pl.tmobile.thread.queue.author.writer.ex;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;

public class Author implements Runnable {

    private List<String> wordsList;
    private Queue<String> queue;

    public Author(Queue<String> queue, List<String> wordsList) {
        this.queue = queue;
        this.wordsList = wordsList;
    }

    @Override
    public void run() {

        for (String text : wordsList) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (queue) {
                queue.add(text);
            }
        }
    }

    public List<String> getWordsList() {
        return wordsList;
    }

    public void setWordsList(List<String> wordsList) {
        this.wordsList = wordsList;
    }

    public Queue<String> getQueue() {
        return queue;
    }

    public void setQueue(Queue<String> queue) {
        this.queue = queue;
    }


}
