class JoinTestThread extends Thread
{
    Thread t;
    String name;
    int number;

    JoinTestThread(String name, Thread t, int number){

        this.setName(name);
        this.name =name;
        this.t=t;
        this.number=number;
    }

    public void run()
    {
        System.out.println("Thread "+number+" has entered the run() method");
        try {

            if (t != null)
                t.join();

            System.out.println("Thread "+number+" executing operation.");
            for(int i=1;i<=number;i++)
            {
                if(number%i==0)
                {
                    Main.sum += i;
                }
            }

            System.out.println("Sum: "+Main.sum);
            Thread.sleep(1000);
            System.out.println("Thread "+number+" has terminated operation.");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }}

    }
    public class Main
    {
        static int sum = 0;
        public static void main(String[] args)
        {
            JoinTestThread w1 = new JoinTestThread("Thread 1", null,70000);
            JoinTestThread w2 = new JoinTestThread("Thread 2", w1, 25000);

            w1.start();
            w2.start();

            System.out.println("done");

        }
    }