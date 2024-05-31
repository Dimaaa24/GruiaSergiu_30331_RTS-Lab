package App3;

import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

public class Main {
    public static void main(String[] args) {
        Object monitor1 = new Object();
        Object monitor2 = new Object();
        CountDownLatch latch = new CountDownLatch(3);
        Scanner scanner = new Scanner(System.in);
        int x;
        int y;

        System.out.println("Type the value for x:");
        x = scanner.nextInt();
        System.out.println("Type the value for y:");
        y = scanner.nextInt();

        Thread1 thread1 = new Thread1(monitor1, monitor2, latch, 2, 3, 7, "Thread 1");
        Thread2 thread2 = new Thread2(monitor1, latch, 3, 5, x, "Thread 2");
        Thread2 thread3 = new Thread2(monitor2, latch, 4, 6, y, "Thread 3");
        thread1.start();
        thread2.start();
        thread3.start();
    }
}
