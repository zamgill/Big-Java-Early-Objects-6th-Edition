package CH22.E22_4;

public class QueueRemoveRunnable implements Runnable {
    private static final int DELAY = 1000;
    private Queue lineUp;

    public QueueRemoveRunnable(Queue aQueue) {
        lineUp = aQueue;
    }

    public void run() {
        try {
            lineUp.remove();
            Thread.sleep(DELAY);

        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
    }
}