public class Main {
    public static void main(String[] args) {
        Integer monitor1 = new Integer(1);
        Integer monitor2 = new Integer(1);

        new ExecutionThread(monitor1,3, 6, 5).start();
        new ExecutionThread(monitor1, 4, 7, 3).start();
        new ExecutionThread(monitor1, 5, 7, 6).start();
    }
}