package thread.basic;

public class Main {
    public static void main(String[] args) {

        //Rozszerzenie klasy Thread
        Thread t1 = new MyThread();
        t1.start();

        //implmement runnable w class

        Thread t2 = new Thread(new MyRunnable());
        t2.start();

        // wyrazneie lambda Thread() -> {

        Runnable runnable = () -> {
            System.out.println("I m in lambda runnable");
        };

        Thread t3 = new Thread(runnable);
        t3.start();

        Thread t4 = new Thread(() -> {
            System.out.println("im in lambda thread");
        });

        t4.start();
    }
}
