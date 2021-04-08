package deadlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeadlockResolve {
    public static void main(String[] args) {
        Lock lockOne = new ReentrantLock();
        Lock lockTwo = new ReentrantLock();

        Thread threadOne = new Thread(() -> {
            System.out.println("We are in the first thread now!");
            acquireLocks(lockOne, lockTwo);
        });

        Thread threadTwo = new Thread(() -> {
            System.out.println("We are in the second thread now!");
            acquireLocks(lockTwo, lockOne);
        });

        threadOne.start();
        threadTwo.start();
    }

    public static void doSmth() {
        for (int i = 1; i <= 10; i++) {
            System.out.println("We are working for " + i + " hours!!!");
        }
    }

    public static void acquireLocks(Lock first, Lock second) {
        while (true) {
            if (first.tryLock()) {
                try {
                    if (second.tryLock()) {
                        doSmth();
                        second.unlock();
                        return;
                    }
                } finally {
                    first.unlock();
                }
            } else {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
