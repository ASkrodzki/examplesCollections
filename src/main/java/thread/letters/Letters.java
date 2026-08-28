package thread.letters;

import java.util.ArrayList;
import java.util.List;

public class Letters {

    private String text;

    private List<Thread> threads = new ArrayList<>();


    public Letters(String text) {
        this.text = text;
        for (char c : text.toCharArray()) {
            threads.add(new Thread(() -> {
                while (true){
                    try {
                        Thread.sleep(1000);
                        System.out.print(c);
                    } catch (InterruptedException e) {
                        return;
                    }
                }
            }, "Thread: " + c));
        }
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Thread> getThreads() {
        return threads;
    }

    public void setThreads(List<Thread> threads) {
        this.threads = threads;
    }
}
