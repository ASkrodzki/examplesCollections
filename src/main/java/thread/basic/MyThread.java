package thread.basic;

public class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("Im in Thread!");
    }
}
