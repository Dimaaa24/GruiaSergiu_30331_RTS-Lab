package App2_ReentrantLock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

public class Thread2lock extends Thread {
    private final Lock lock1;
    private final Lock lock2;
    private final CountDownLatch latch;
    private final int minActivity;
    private final int maxActivity;
    private final int transitionTime;
    private final String name;

    public Thread2lock(Lock lock1, Lock lock2, CountDownLatch latch, int minActivity, int maxActivity, int transitionTime, String name) {
        this.lock1 = lock1;
        this.lock2 = lock2;
        this.latch = latch;
        this.minActivity = minActivity;
        this.maxActivity = maxActivity;
        this.transitionTime = transitionTime;
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println(name +":State1:");
        lock1.lock();
        lock2.lock();
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
                    System.out.println(name + ": Tokens released.");
                    lock2.unlock();
                    lock1.unlock();


        System.out.println(name +":State3:");
        System.out.println(name + ": Waiting for the other threads to reach this point.");
        try {
            latch.countDown();
            latch.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}