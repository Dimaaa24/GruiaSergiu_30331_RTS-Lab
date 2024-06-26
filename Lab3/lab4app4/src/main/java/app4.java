class   ExecutionThread_app4 extends Thread {
    int sleep, activity_min, activity_max;

    Integer monitor1, monitor2;

    public ExecutionThread_app4(Integer monitor1, Integer monitor2, int sleep, int activity_min, int activity_max) {
        this.monitor1 = monitor1;
        this.monitor2 = monitor2;
        this.sleep = sleep;
        this.activity_min = activity_min;
        this.activity_max = activity_max;
    }

    public void run() {
        System.out.println(this.getName() + " - STATE 1");
        try {
            Thread.sleep(Math.round(sleep * 500));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(this.getName() + " - STATE 2");
        int k = (int) Math.round(Math.random() * (activity_max - activity_min) + activity_min);
        for (int i = 0; i < k * 100000; i++) {
            i++;
            i--;

        }
        synchronized (monitor1) {
            monitor1.notify();
        }
        synchronized (monitor2) {
            monitor2.notify();
        }
        System.out.println(this.getName() + " - STATE 3");
    }
}

class ExecutionThread_app4_2 extends Thread {
    Integer monitor;
    int activity_min, activity_max;
    ExecutionThread_app4 thread1;

    public ExecutionThread_app4_2(Integer monitor, int activity_min, int activity_max, ExecutionThread_app4 thread1) {
        this.monitor = monitor;
        this.activity_min = activity_min;
        this.activity_max = activity_max;
        this.thread1 = thread1;
    }

    public void run() {
        System.out.println(this.getName() + " - STATE 1");
        synchronized (monitor) {
            try {
                monitor.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(this.getName() + " - STATE 2");

            int k = (int) Math.round(Math.random() * (activity_max - activity_min) + activity_min);
            for (int i = 0; i < k * 100000; i++) {
                i++;
                i--;

            }
        }
        System.out.println(this.getName() + " - STATE 3");
        if (thread1 != null) {
            try {
                thread1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class app4 {
    public static void main(String[] args) {
        Integer monitor1 = new Integer(1);
        Integer monitor2 = new Integer(2);

        ExecutionThread_app4 thread1 = new ExecutionThread_app4(monitor1, monitor2, 7, 2, 3);
        thread1.start();

        new ExecutionThread_app4_2(monitor1, 3, 5, thread1).start();
        new ExecutionThread_app4_2(monitor2, 4, 6, thread1).start();

    }
}