package App1_Sempahore;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        Semaphore semaphore1 = new Semaphore(1);
        Semaphore semaphore2 = new Semaphore(1);
        CyclicBarrier barrier = new CyclicBarrier(3);

        while (true) {
            // State 0
            System.out.println("Main: Initial state.");
            new ThreadSemaphore(semaphore1, semaphore2, barrier, 2, 4, 4, 6, 4, "Thread 1").start();
            new ThreadSemaphore(semaphore2, semaphore1, barrier, 3, 5, 5, 7, 5, "Thread 2").start();
            try {
                barrier.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
            barrier.reset();
        }
    }
}