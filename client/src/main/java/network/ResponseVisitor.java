package network;

public class ResponseVisitor extends Thread{
    private final Runnable runnable;

    public ResponseVisitor(Runnable runnable) {
        this.runnable = runnable;
    }

    public Runnable getRunnable() {
        return runnable;
    }

    @Override
    public void run(){
        runnable.run();
    }
}
