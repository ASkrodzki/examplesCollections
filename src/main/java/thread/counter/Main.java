package thread.counter;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        // Stwórz klase Licznik ktora ma pole typu int o wartosci 0, getter oraz metode
        // zwiekszajaca pole o 1


        //race condition - wyścig danych występuję gdy dwa lub wiecej wątków
        // dostępuję do tej samej zmiennej i przynajmniej jeden z tych wątków
        // modyfikuje zmienną

        Counter counter = new Counter();

        Thread t1 = new Thread(counter);
        Thread t2 = new Thread(counter);
        Thread t3 = new Thread(counter);

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();

        System.out.println(t1.getName());
        System.out.println(counter.getCounter());

    }
}
