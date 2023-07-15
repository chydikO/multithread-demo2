package org.itstep;

public class JoinDemo {
    public static void main(String[] args) throws InterruptedException {
        final long[] num = new long[1];
        Thread t = new Thread(() -> {
            for (int i = 0; i < 100_000; i++) {
                num[0] += i;
            }
            System.out.println("End thread");
        });
        t.start();
        t.join();
        System.out.println("num[0] = " + num[0]);
        System.out.println("End program");
    }
}
