package App3;

import java.util.concurrent.CountDownLatch;

public class Thread1 extends Thread {
    private final Object monitor1;
    private final Object monitor2;
    private final CountDownLatch latch;
    private final int minActivity;
    private final int maxActivity;
    private final int transitionTime;
    private final String name;

    public Thread1(Object monitor1, Object monitor2, CountDownLatch latch, int minActivity, int maxActivity, int transitionTime, String name) {
        this.monitor1 = monitor1;
        this.monitor2 = monitor2;
        this.latch = latch;
        this.minActivity = minActivity;
        this.maxActivity = maxActivity;
        this.transitionTime = transitionTime;
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println(name + " :State1:");
        try {
            Thread.sleep(transitionTime * 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(name + " :State2:");
        int k = (int) Math.round(Math.random() * (maxActivity - minActivity) + minActivity);
        for (int i = 0; i < k * 100000; i++) {
            i++;
            i--;
        }
        synchronized (monitor1) {
            synchronized (monitor2) {
                monitor1.notify();
                monitor2.notify();
            }
        }
        System.out.println(name + " :State3:");
        System.out.println(name + ": Waiting for all threads to finish execution.");
        try {
            latch.countDown();
            latch.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
