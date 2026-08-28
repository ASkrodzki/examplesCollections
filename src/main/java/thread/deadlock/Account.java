package thread.deadlock;

import java.util.concurrent.locks.ReentrantLock;

public class Account {

    private String number;
    private double balance;
    private ReentrantLock lock = new ReentrantLock();

    public Account(double balance, String number) {
        this.balance = balance;
        this.number = number;
    }

    public ReentrantLock getLock() {
        return lock;
    }

    public void setLock(ReentrantLock lock) {
        this.lock = lock;
    }

    public void incrementBalance(double money) {
        balance += money;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
