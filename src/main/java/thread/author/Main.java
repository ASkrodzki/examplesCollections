package thread.author;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<String> list = List.of("aaa", "bbb", "ccc");

        Author author = new Author(list);
        Writer writer = new Writer(author);

        Thread t1 = new Thread(author);
        Thread t2 = new Thread(writer);

        t1.start();
        t2.start();
    }
}
