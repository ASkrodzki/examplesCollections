package pl.tmobile.thread.queue.author.writer.ex;

import java.util.List;
import java.util.Queue;

public class Writer implements Runnable {

    private Queue<String> queue;
    private int counter;

    public Writer(Queue<String> queue, List<String> wordList) {
        this.queue = queue;
        this.counter = wordList.size();
    }

    @Override
    public void run() {
        while (counter > 0) {
            String text;

            synchronized (queue) {
                if (queue.isEmpty()) {
                    continue;
                }
                text = queue.poll();
            }

            counter--;
            System.out.println(text);
            System.out.println(counter);
        }
    }


    public Queue<String> getQueue() {
        return queue;
    }

    public void setQueue(Queue<String> queue) {
        this.queue = queue;
    }


}
