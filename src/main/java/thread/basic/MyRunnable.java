package thread.basic;

public class MyRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("I m in Runnable");
    }
}
