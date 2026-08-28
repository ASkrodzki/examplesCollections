package thread.letters;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        Letters letters = new Letters("ABCD");

        for (Thread thread : letters.getThreads()) {
            System.out.println(thread.getName());
            thread.start();
        }


        Thread.sleep(5000);


        for (Thread thread : letters.getThreads()) {
            thread.interrupt();
        }
    }
}
