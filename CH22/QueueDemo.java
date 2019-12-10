package CH22;

public class QueueDemo {
    public static void main(String[] args) {
        final int MAX_SIZE = 10;
        Queue lineUp = new Queue(MAX_SIZE);
        final int ITERATIONS = 100;

        for (int i = 0; i < ITERATIONS; i++) {
            QueueAddRunnable qA = new QueueAddRunnable(lineUp, MAX_SIZE);
            QueueRemoveRunnable qR = new QueueRemoveRunnable(lineUp);
            Thread qaT = new Thread(qA);
            Thread qrT = new Thread(qR);
            qaT.start();
            qrT.start();
        }
    }
}