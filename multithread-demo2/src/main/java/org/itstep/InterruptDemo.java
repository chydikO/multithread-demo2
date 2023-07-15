package org.itstep;

import java.time.LocalDateTime;

public class InterruptDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            while (true){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    //throw new RuntimeException(e);
                    System.out.println("exception");
                    break;
                }
//                for (long i = 0; i < 4_000_000_000L; i++) {
//                }
//                if (Thread.interrupted()) {
//                    break;
//                }
                System.out.println(LocalDateTime.now());
            }
        });
        t.setDaemon(true);
        System.out.println("t.isDaemon() = " + t.isDaemon());
        t.start();
        Thread.sleep(5000);
        // t.interrupt();
        System.out.println("End program");
    }
}
