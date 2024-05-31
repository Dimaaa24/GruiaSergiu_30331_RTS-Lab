package App3;

import java.util.concurrent.CountDownLatch;

public class Thread2 extends Thread {
    private final Object monitor;
    private final CountDownLatch latch;
    private final int transitionTime;
    private final int minActivity;
    private final int maxActivity;
    private final String name;

    public Thread2(Object monitor, CountDownLatch latch, int minActivity, int maxActivity, int transitionTime, String name) {
        this.monitor = monitor;
        this.latch = latch;
        this.minActivity = minActivity;
        this.maxActivity = maxActivity;
        this.transitionTime = transitionTime;
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println(name + " :State1:");
        synchronized (monitor) {
            try {
                monitor.wait();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println(name + ": Token acquired.");
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
