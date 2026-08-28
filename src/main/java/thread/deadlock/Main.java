package thread.deadlock;

public class Main {

    public static void main(String[] args) {

        //Klasa Account ma number i balance oraz metode zwiekszajaca stan konta
        //Klasa Tranfser przyjmuje dwa konta i ma metode transfer ktora przeklada hajs z
        // jednego konta na drugie


        Account account1 = new Account(5000, "123");
        Account account2 = new Account(16500, "456");

        Transfer transfer1 = new Transfer(account1, account2, 500);
        Transfer transfer2 = new Transfer(account1, account2, 500);


        Thread t1 = new Thread(transfer1);
        Thread t2 = new Thread(transfer2);

        t1.start();
        t2.start();

        System.out.println("Account 1 balance: " + account1.getBalance());
        System.out.println("Account 2 balance: " + account2.getBalance());
    }
}
