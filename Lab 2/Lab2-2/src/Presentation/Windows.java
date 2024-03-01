package Presentation;

import Threads.FirModel;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;
import javax.swing.JProgressBar;

public class Windows  extends JFrame implements Observer
{
    private ArrayList<JProgressBar> progressBars;

    public Windows(int nrOfThreads)
    {
        progressBars = new ArrayList<JProgressBar>(nrOfThreads);
        setLayout(null);
        setSize(450,400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        init(nrOfThreads);
        this.setVisible(true);
    }

    private void init(int n)
    {
        for(int i=0 ; i < n ; i++)
        {
            JProgressBar progressBar=new JProgressBar();
            progressBar.setMaximum(1000);
            progressBar.setBounds(50,(i+1)*30,350,20);
            this.add(progressBar);
            progressBars.add(progressBar);
        }
    }

    public void setProgressValue(int id,int val)
    {
        progressBars.get(id).setValue(val);
    }

    @Override
    public void update(Observable o, Object arg)
    {
        if(arg instanceof Integer)
        {
            int id = ((FirModel)o).getId();
            int progress = (int) arg;
            setProgressValue(id, progress);
        }
    }
}
