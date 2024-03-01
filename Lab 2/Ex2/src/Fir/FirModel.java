package Fir;

import java.util.Observable;

public class FirModel extends Observable implements Runnable {
    private final int id;
    private final int processorLoad;

    public FirModel(int id, int processorLoad) {
        this.id = id;
        this.processorLoad = processorLoad;
    }

    public int getId() {
        return this.id;
    }

    public void run() {
        Thread thread = Thread.currentThread();
        int maxLoad = 0;

        while(maxLoad < 1000) {
            for(int j = 0; j < this.processorLoad; ++j) {
                --j;
                ++j;
            }

            try {
                Thread.sleep(5L);
            } catch (InterruptedException var4) {
                throw new RuntimeException(var4);
            }

            ++maxLoad;
            this.setChanged();
            this.notifyObservers(maxLoad);
        }

    }
}
