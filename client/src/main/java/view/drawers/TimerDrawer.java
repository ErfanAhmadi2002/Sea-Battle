package view.drawers;

import javafx.scene.text.Text;
import timerHandler.TimerThread;

public class TimerDrawer extends Thread{
    private final Text timerText;
    private TimerThread timerThread;

    public TimerDrawer(Text timerText) {
        this.timerText = timerText;
    }

    public Text getTimerText() {
        return timerText;
    }

    public TimerThread getTimerThread() {
        return timerThread;
    }

    public void setTimerThread(TimerThread timerThread) {
        this.timerThread = timerThread;
    }

    @Override
    public void run(){
        boolean isWorking = true;
        while (isWorking){
            if (!timerThread.getTimer().isRunning()){
                isWorking = false;
            }
            try {
                timerText.setText(String.valueOf(timerThread.getTimer().getSecondsLeft()));
            }catch (Throwable throwable){
                break;
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
        }
    }
}
