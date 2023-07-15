package org.itstep;

import java.time.LocalDateTime;

import static org.itstep.ThreadUtils.*;

public class Application {
    public static void main(String[] args) {
        printStartInfo();
        Thread workingThread = new WorkingThread();
        workingThread.start();
        Thread sleppyThread = new Thread(new SleppyThread(), "Sleppy Thread");
        sleppyThread.start();
        Thread usingLambdaThread = new Thread(() -> {
            printStartInfo();
            String threadName = Thread.currentThread().getName();
            for (int i = 0; i < 10; i++) {
                System.out.println(threadName + " " + i);
            }
            printEndInfo();
        }, "Thread With Lambda");
        usingLambdaThread.start();
        printEndInfo();
    }
}

class ThreadUtils {
    public static void printStartInfo() {
        String threadName = Thread.currentThread().getName();
        System.out.println("Thread <" + threadName + "> started at " + LocalDateTime.now());
    }

    public static void printInterruptExceptionInfo(InterruptedException ex) {
        String threadName = Thread.currentThread().getName();
        System.out.println("Thread <" + threadName + "> catch InterruptedException exception at " + LocalDateTime.now());
    }

    public static void printEndInfo() {
        String threadName = Thread.currentThread().getName();
        System.out.println("Thread <" + threadName + "> ended at " + LocalDateTime.now());
    }
}

class SleppyThread implements Runnable {

    @Override
    public void run() {
        printStartInfo();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            printInterruptExceptionInfo(e);
        }
        printEndInfo();
    }
}

class WorkingThread extends Thread {

    public WorkingThread() {
        super("Working Thread");
    }

    @Override
    public void run() {
        // write code here which run in separate thread
        printStartInfo();
        long s = 0;
        for (int i = 0; i < 1_000; i++) {
            s += i;
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                printInterruptExceptionInfo(e);
            }
        }
        System.out.println("s = " + s);
        printEndInfo();
    }
}
