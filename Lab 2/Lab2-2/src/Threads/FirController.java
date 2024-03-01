package Threads;

import Presentation.Windows;
import java.util.ArrayList;

public class FirController
{
    private ArrayList<FirModel> firModels;

    public  FirController(int nrOfThreads, Windows window, int processorLoad)
    {
        firModels = new ArrayList<FirModel>();
        for( int i =0 ;i < nrOfThreads ; i++)
        {
            FirModel firModel = new  FirModel(i, processorLoad);
            firModel.addObserver(window);
            firModels.add(firModel);
        }
    }

    public void start()
    {
        for(FirModel firModel : firModels)
        {
                Thread thread = new Thread(firModel,"thread test");
                thread.start();
        }
    }
}
