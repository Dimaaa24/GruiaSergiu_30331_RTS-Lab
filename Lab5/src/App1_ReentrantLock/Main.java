package App1_ReentrantLock;


import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.*;

public class Main {
    public static void main(String[] args) {
        Lock lock1 = new ReentrantLock();
        Lock lock2 = new ReentrantLock();
        CyclicBarrier barrier = new CyclicBarrier(3);

        while (true) {
            new ThreadLock(lock1, lock2, barrier, 2, 4, 4, 6, 4, "Thread 1").start();
            new ThreadLock(lock1, lock2, barrier, 3, 5, 5, 7, 5, "Thread 2").start();
            try {
                barrier.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
            barrier.reset();
        }
    }
}