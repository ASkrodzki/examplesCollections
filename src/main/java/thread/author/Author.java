package thread.author;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Author implements Runnable {

    private List<String> list;
    private BlockingQueue<String> queue = new LinkedBlockingQueue<>();

    public Author(List<String> list) {
        this.list = list;
    }

    @Override
    public void run() {
        for (String text : list) {
            try {
                Thread.sleep(1000);
                System.out.println("Author put: " + text);
                queue.put(text);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public BlockingQueue<String> getQueue() {
        return queue;
    }

    public void setQueue(BlockingQueue<String> queue) {
        this.queue = queue;
    }

}
