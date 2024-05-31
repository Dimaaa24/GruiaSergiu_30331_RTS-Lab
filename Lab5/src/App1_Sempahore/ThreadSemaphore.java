package App1_Sempahore;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class ThreadSemaphore extends Thread {
    private final Semaphore semaphore1;
    private final Semaphore semaphore2;
    private final CyclicBarrier barrier;
    private final int minActivity1;
    private final int maxActivity1;
    private final int minActivity2;
    private final int maxActivity2;
    private final int transitionTime;
    private final String name;

    public ThreadSemaphore(Semaphore semaphore1, Semaphore semaphore2, CyclicBarrier barrier, int minActivity1, int maxActivity1, int minActivity2, int maxActivity2, int transitionTime, String name) {
        this.semaphore1 = semaphore1;
        this.semaphore2 = semaphore2;
        this.barrier = barrier;
        this.minActivity1 = minActivity1;
        this.maxActivity1 = maxActivity1;
        this.minActivity2 = minActivity2;
        this.maxActivity2 = maxActivity2;
        this.transitionTime = transitionTime;
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println(name + ":State1:");
        int k1 = (int) Math.round(Math.random() * (maxActivity1 - minActivity1) + minActivity1);
        for (int i = 0; i < k1 * 100000; i++) {
            i++;
            i--;
        }
        try {
            this.semaphore1.acquire(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(name + ": first token acquired.");
        System.out.println(name + ":State2:");
        int k2 = (int) Math.round(Math.random() * (maxActivity2 - minActivity2) + minActivity2);
        for (int i = 0; i < k2 * 100000; i++) {
            i++;
            i--;
        }
        if (semaphore2.tryAcquire()) {
            try {
                System.out.println(name + ": second token acquired.");
                System.out.println(name + ":State3:");
                Thread.sleep(transitionTime * 1000);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                System.out.println(name + ": second token released.");
                this.semaphore2.release(1);
            }
        }


            System.out.println(name + ": first token released.");
            this.semaphore1.release(1);

            System.out.println(name + ":State4:");

            System.out.println(name + ": Waiting for the other thread to reach this point.");
            try {
                barrier.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }