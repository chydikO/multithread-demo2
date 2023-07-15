package org.itstep;

public class ConcurrencyRaceDemo {
    public static void main(String[] args) throws InterruptedException {
        final BankAccount account = new BankAccount(100_000);
        int iterations = 100_000;
        Thread withdrawThread = new Thread(() -> {
            for (int i = 0; i < iterations; i++) {
                account.withdraw(1);
            }
        });
        Thread depositThread = new Thread(() -> {
            for (int i = 0; i < iterations; i++) {
                account.deposit(1);
            }
        });

        withdrawThread.start();
        depositThread.start();

        withdrawThread.join();
        depositThread.join();

        System.out.println(account);
    }
}

class BankAccount {
    private long funds;

//    private final Object monitor = new Object();

    public BankAccount(long funds) {
        this.funds = funds;
    }

    public void deposit(int amount) {
//        synchronized (monitor) {
        synchronized (this) {
            funds += amount;
        }
    }

    public void withdraw(int amount) {
//        synchronized (monitor) {
        synchronized (this) {
            funds -= amount;
        }
    }

    @Override
    public String toString() {
        return "Funds: $" + funds;
    }
}