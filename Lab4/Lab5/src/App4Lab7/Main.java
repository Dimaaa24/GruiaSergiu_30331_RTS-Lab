package App4Lab7;

import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        Semaphore s = new Semaphore(2);
        Threadish one,two,three;
        one = new Threadish(1, s, 5, (int) Math.round(Math.random() * 3 + 6), 2);
        two= new Threadish(2, s, 3, (int) Math.round(Math.random() * 4 + 7), 2);
        three = new Threadish(3, s, 6, (int) Math.round(Math.random() * 5 + 7), 2);
        one.start();
        two.start();
       three.start();
    }
}
