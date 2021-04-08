package deadlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Deadlock {
    public static void main(String[] args) {
        Lock lockOne = new ReentrantLock();
        Lock lockTwo = new ReentrantLock();

        Thread threadOne = new Thread(() -> {
            lockOne.lock();
            lockTwo.lock();
            System.out.println("We are in the first thread now!");
            try {
                doSmth();
            } finally {
                lockOne.unlock();
                lockTwo.unlock();
            }
        });

        Thread threadTwo = new Thread(() -> {
            lockTwo.lock();
            lockOne.lock();
            System.out.println("We are in the second thread now!");
            try {
                doSmth();
            } finally {
                lockOne.unlock();
                lockTwo.unlock();
            }
        });

        threadOne.start();
        threadTwo.start();
    }

    public static void doSmth() {
        for (int i = 1; i <= 10; i++) {
            System.out.println("We are working for " + i + " hours!!!");
        }
    }
}
