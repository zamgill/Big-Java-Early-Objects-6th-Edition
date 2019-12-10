package CH22.E22_4;

import java.util.Scanner;

public class QueueDemo {
    public static void main(String[] args) {
        final int MAX_SIZE = 10;
        int producerIterations = 0;
        int consumerIterations = 0;
        Queue lineUp = new Queue(MAX_SIZE);

        Scanner in = new Scanner(System.in);
        try {
            System.out.print("Please enter the number of producer threads to start: ");
            producerIterations = in.nextInt();

            System.out.print("Please enter the number of consumer threads to start: ");
            consumerIterations = in.nextInt();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            in.close();
        }

        for (int i = 0; i < producerIterations; i++) {
            QueueAddRunnable qA = new QueueAddRunnable(lineUp, MAX_SIZE);
            Thread qaT = new Thread(qA);
            qaT.start();
        }

        for (int i = 0; i < consumerIterations; i++) {
            QueueRemoveRunnable qR = new QueueRemoveRunnable(lineUp);
            Thread qrT = new Thread(qR);
            qrT.start();
        }
    }
}