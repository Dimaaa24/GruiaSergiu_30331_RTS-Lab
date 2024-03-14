package Fir;

import Presentation.Window;
import java.util.ArrayList;

public class FirController
{
    private final ArrayList<FirModel> firModels;

    public FirController(int nrOfThreads, Window window, int processorLoad)
    {
        firModels = new ArrayList<>();
        for(int i = 0; i < nrOfThreads; ++i) {
            FirModel firModel = new FirModel(i, processorLoad);
            firModel.addObserver(window);
            this.firModels.add(firModel);
        }
    }

    public void start()
    {
            for(int i = 0; i< firModels.size() ; i++)
            {
                Thread thread = new Thread(firModels.get(i), "thread test");
                thread.start();
            }
        }

}
