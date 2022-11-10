package shared.models;

public class Timer {
    private final Object syncTitle = new Object();
    private int secondsLeft;
    private volatile boolean isRunning;

    public Timer(int secondsLeft) {
        this.secondsLeft = secondsLeft;
        this.isRunning = false;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }

    public int getSecondsLeft() {
        return secondsLeft;
    }

    public void increaseTime(int second) {
        synchronized (syncTitle) {
            this.secondsLeft += second;
        }
    }

    public void start() {
        try {
            isRunning = true;
            while (secondsLeft != 0 && isRunning) {
                Thread.sleep(1000);
                synchronized (syncTitle) {
                    secondsLeft--;
                }
            }
            isRunning = false;
        } catch (InterruptedException ignored) {
            this.isRunning = false;
        }
    }

    public void stopTimer() {
        synchronized (syncTitle) {
            isRunning = false;
        }
    }

    public void reset (){
        synchronized (syncTitle){
            this.secondsLeft = 25;
        }
    }
}
