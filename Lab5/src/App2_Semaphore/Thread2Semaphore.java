package App2_Semaphore;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class Thread2Semaphore extends Thread {
    private final Semaphore semaphore1;
    private final Semaphore semaphore2;
    private final CyclicBarrier barrier;
    private final int minActivity;
    private final int maxActivity;
    private final int transitionTime;
    private final String name;

    public Thread2Semaphore(Semaphore semaphore1, Semaphore semaphore2, CyclicBarrier barrier, int minActivity, int maxActivity, int transitionTime, String name) {
        this.semaphore1 = semaphore1;
        this.semaphore2 = semaphore2;
        this.barrier = barrier;
        this.minActivity = minActivity;
        this.maxActivity = maxActivity;
        this.transitionTime = transitionTime;
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println(name +":State1:");
        try {
            semaphore1.acquire(1);
            semaphore2.acquire(1);
            System.out.println(name + ": Token acquired.");
            System.out.println(name +":State2:");
            int k = (int) Math.round(Math.random() * (maxActivity - minActivity) + minActivity);
            for (int i = 0; i < k * 100000; i++) {
                i++;
                i--;
            }
            try {
                Thread.sleep(transitionTime * 1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            semaphore2.release(1);
            semaphore1.release(1);
            System.out.println(name + ": Tokens released.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(name +":State3:");
        System.out.println(name + ": Waiting for the other threads to reach this point.");
        try {
            barrier.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}