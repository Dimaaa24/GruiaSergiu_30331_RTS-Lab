import Presentation.Window;
import Fir.FirController;

public class Main {
    private static final int nrOfThreads = 8;
    private static final int processorLoad = 100000;

    public Main() {
    }

    public static void main(String[] args) {
        Window window = new Window(8);
        FirController firController = new FirController(8, window, 1000000);
        firController.start();
    }
}
