import Presentation.Windows;
import Threads.FirController;
import Threads.FirModel;

public class Main
{
    private static final int nrOfThreads = 8;
    private static final int processorLoad = 1000000;
    public static void main(String[] args)
    {
        Windows window = new Windows(nrOfThreads);
        FirController firController = new FirController(nrOfThreads , window , processorLoad);
        firController.start();
    }
}