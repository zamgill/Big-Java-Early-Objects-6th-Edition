package CH22;

import java.util.Date;

public class QueueAddRunnable implements Runnable {
    private static final int DELAY = 1000;
    private Queue lineUp;
    private int count;

    public QueueAddRunnable(Queue aQueue, int aCount) {
        lineUp = aQueue;
        count = aCount;
    }

    public void run() {
        try {
            while (lineUp.size() < count) {
                lineUp.add(new Date().toString());
                Thread.sleep(DELAY);
            }
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
    }
}