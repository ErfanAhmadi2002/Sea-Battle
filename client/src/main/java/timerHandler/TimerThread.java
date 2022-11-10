package timerHandler;


import shared.models.Timer;

public class TimerThread extends Thread{
    private final Timer timer;

    public TimerThread(Timer timer) {
        this.timer = timer;
    }

    public Timer getTimer() {
        return timer;
    }

    @Override
    public void run(){
        timer.start();
    }
}
