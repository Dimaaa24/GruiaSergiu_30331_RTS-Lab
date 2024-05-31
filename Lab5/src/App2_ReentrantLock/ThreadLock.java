package App2_ReentrantLock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

public class ThreadLock extends Thread {
    private final Lock lock;
    private final CountDownLatch latch;
    private final int minActivity;
    private final int maxActivity;
    private final int transitionTime;
    private final String name;

    public ThreadLock(Lock lock, CountDownLatch latch, int minActivity, int maxActivity, int transitionTime, String name) {
        this.lock = lock;
        this.latch = latch;
        this.minActivity = minActivity;
        this.maxActivity = maxActivity;
        this.transitionTime = transitionTime;
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println(name + " :State1:");
            lock.lock();
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
                lock.unlock();

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