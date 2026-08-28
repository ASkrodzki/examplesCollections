package thread.author;

import java.util.concurrent.TimeUnit;

public class Writer implements Runnable {

    private Author author;

    public Writer(Author author) {
        this.author = author;
    }

    @Override
    public void run() {
        while (true) {
            try {
                String text = author.getQueue().poll(2000, TimeUnit.MILLISECONDS);
                if (text == null) {
                    break;
                }
                System.out.println("writer received: " + text);
            } catch (InterruptedException e) {
                e.printStackTrace();

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
