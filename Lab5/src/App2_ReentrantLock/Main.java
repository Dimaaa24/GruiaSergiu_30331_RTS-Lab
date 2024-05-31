package App2_ReentrantLock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static void main(String[] args) {
        Lock lock1 = new ReentrantLock();
        Lock lock2 = new ReentrantLock();
        CountDownLatch latch;

        while (true) {
            latch = new CountDownLatch(4);
            new ThreadLock(lock1, latch, 2, 4, 4, "Thread 1").start();
            new Thread2lock(lock1, lock2, latch, 3, 6, 3, "Thread 2").start();
            new ThreadLock(lock2, latch, 2, 5, 5, "Thread 3").start();
            try {
                latch.countDown();
                latch.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
