package thread.deadlock;

import java.util.concurrent.locks.ReentrantLock;

public class Transfer implements Runnable {

    private Account transferTo;
    private Account transferFrom;
    private double balance;
    private final ReentrantLock lock = new ReentrantLock();

    public Transfer(Account transferFrom, Account transferTo, double balance) {
        this.transferFrom = transferFrom;
        this.transferTo = transferTo;
        this.balance = balance;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            double transferCash = 145;
            transferWithLock(transferTo, transferFrom, transferCash);
            System.out.println(transferTo.getBalance());
            System.out.println(transferFrom.getBalance());
            System.out.println(transferCash);
        }

    }


    public void transferWithLock(Account sender, Account recipient, double money) {
        Account firstLock;
        Account secondLock;

        if (sender.getNumber().compareTo(recipient.getNumber()) < 0) {
            firstLock = sender;
            secondLock = recipient;
        } else{
            firstLock = recipient;
            secondLock = sender;
        }

        firstLock.getLock().lock();
        try {
            secondLock.getLock().lock();
            try {
                sender.incrementBalance(-money);
                recipient.incrementBalance(money);
            } finally {
                secondLock.getLock().unlock();
            }
        }finally {
            firstLock.getLock().unlock();
        }
    }

    public void transfer(Account sender, Account recipient, double money) {
        int compare = recipient.getNumber().compareTo(sender.getNumber());
        if (compare > 0) {
            synchronized (sender) {
                synchronized (recipient) {
                    sender.incrementBalance(-money);
                    sender.incrementBalance(money);
                }
            }
        } else {
            synchronized (recipient) {
                synchronized (sender) {
                    sender.incrementBalance(-money);
                    recipient.incrementBalance(money);
                }
            }
        }
    }

    public ReentrantLock getLock() {
        return lock;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Account getTransferFrom() {
        return transferFrom;
    }

    public void setTransferFrom(Account transferFrom) {
        this.transferFrom = transferFrom;
    }

    public Account getTransferTo() {
        return transferTo;
    }

    public void setTransferTo(Account transferTo) {
        this.transferTo = transferTo;
    }


}
