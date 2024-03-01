package Threads;

import java.util.Observable;

public class FirModel extends Observable implements  Runnable
{
    private final int id;
    private final int  processorLoad;

    public FirModel(int id , int processorLoad)
    {
        this.id = id;
        this.processorLoad = processorLoad;
    }

    public int getId() {
        return id;
    }

    public void run()
    {
        Thread thread = Thread.currentThread();

        int maxLoad = 0;

        while(maxLoad < 1000)
        {
            for (int j =0 ; j < this.processorLoad ; j++)
            {
                j--;
                j++;
            }
            try
            {
                Thread.sleep(5);
            }
            catch (InterruptedException e)
            {
                throw new RuntimeException(e);
            }
            maxLoad ++ ;
            setChanged();
            notifyObservers(maxLoad);
        }

    }

}
