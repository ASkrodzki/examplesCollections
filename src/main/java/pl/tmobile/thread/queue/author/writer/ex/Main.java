package pl.tmobile.thread.queue.author.writer.ex;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class Main {
    public static void main(String[] args) {

        //napisz program author - writer. Author co sekunde generuje napisy ktore otrzymuje
        //  jako parametr konstruktora (np talblica stringow)
        //i dodaje je do kolejki
        //writer ma je przyjac, wyjac z kolejki i wypisac na konsoli

        List<String> textList = new ArrayList<>(
                List.of("Arkadiusz", "Tomasz", "Basia", "Asia", "Kasia", "Arkadiusz2", "Tomasz2", "Basia2", "Asia2", "Kasia2")
        );

        Queue<String> queue = new LinkedList<>();

        Author author = new Author(queue, textList);
        Writer writer = new Writer(queue, textList);


        Thread authorThread = new Thread(author);
        Thread writerThread = new Thread(writer);

        authorThread.start();
        writerThread.start();

    }
}
