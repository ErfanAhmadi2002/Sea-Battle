package timerHandler;

import listeners.ChooseBoardListeners;
import shared.models.Board;
import shared.models.Timer;

public class ChooseBoardTimerHandler extends Thread{
    private final Board selectedBoard;
    private final Timer timer;
    private final ChooseBoardListeners chooseBoardListeners;

    public ChooseBoardTimerHandler(Board selectedBoard, Timer timer , ChooseBoardListeners chooseBoardListeners) {
        this.selectedBoard = selectedBoard;
        this.timer = timer;
        this.chooseBoardListeners = chooseBoardListeners;
    }

    public Board getSelectedBoard() {
        return selectedBoard;
    }

    public Timer getTimer() {
        return timer;
    }

    public ChooseBoardListeners getChooseBoardListeners() {
        return chooseBoardListeners;
    }

    @Override
    public void run(){
        boolean isWorking = true;
        while (isWorking){
            if (!timer.isRunning()){
                isWorking = false;
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
        }
        if (timer.getSecondsLeft() == 0) {
            chooseBoardListeners.readyButtonPressed(selectedBoard , timer);
        }
    }


}
