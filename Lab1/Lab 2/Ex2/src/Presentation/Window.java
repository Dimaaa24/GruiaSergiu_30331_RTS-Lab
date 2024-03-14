package Presentation;

import Fir.FirModel;
import java.awt.LayoutManager;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;
import javax.swing.JProgressBar;

public class Window extends JFrame implements Observer {
    private ArrayList<JProgressBar> progressBars;

    public Window(int nrOfThreads) {
        this.progressBars = new ArrayList(nrOfThreads);
        this.setLayout((LayoutManager)null);
        this.setSize(450, 400);
        this.setDefaultCloseOperation(3);
        this.init(nrOfThreads);
        this.setVisible(true);
    }

    private void init(int n) {
        for(int i = 0; i < n; ++i) {
            JProgressBar progressBar = new JProgressBar();
            progressBar.setMaximum(1000);
            progressBar.setBounds(50, (i + 1) * 30, 350, 20);
            this.add(progressBar);
            this.progressBars.add(progressBar);
        }
    }

    public void setProgressValue(int id, int val) {
        ((JProgressBar)this.progressBars.get(id)).setValue(val);
    }

    public void update(Observable o, Object arg)
    {
        if (arg instanceof Integer) {
            int id = ((FirModel)o).getId();
            int progress = (Integer)arg;
            this.setProgressValue(id, progress);
        }
    }
}
