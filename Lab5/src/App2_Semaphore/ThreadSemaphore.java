package App2_Semaphore;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class ThreadSemaphore extends Thread {
    private final Semaphore semaphore;
    private final CyclicBarrier barrier;
    private final int minActivity;
    private final int maxActivity;
    private final int transitionTime;
    private final String name;

    public ThreadSemaphore(Semaphore semaphore, CyclicBarrier barrier, int minActivity, int maxActivity, int transitionTime, String name) {
        this.semaphore = semaphore;
        this.barrier = barrier;
        this.minActivity = minActivity;
        this.maxActivity = maxActivity;
        this.transitionTime = transitionTime;
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println(name + " :State1:");
        try {
            semaphore.acquire(1);
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
            System.out.println(name + ": Token released.");
            semaphore.release(1);
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
