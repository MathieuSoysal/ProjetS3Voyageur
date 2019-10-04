package projetS3Voyageur;

import java.util.TimerTask;

public class InterruptTimerTask extends TimerTask {

    private Thread theThread;

    public InterruptTimerTask(Thread theThread){
        this.theThread = theThread;
    }

    @Override
    public void run(){
        this.theThread.interrupt();
    }

}
