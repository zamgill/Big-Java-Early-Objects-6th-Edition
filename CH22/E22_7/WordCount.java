package CH22.E22_7;

import java.util.Scanner;
import java.io.File;

public class WordCount {
    public static void main(String[] args) {

        for (int i = 0; i < args.length; i++) {
            File currentFile = new File(args[i]);
            Thread wordCountThreads = new Thread(new Runnable() {

                @Override
                public void run() {
                    try (Scanner in = new Scanner(currentFile)) {
                        int wordCount = 0;
                        while (in.hasNext()) {
                            in.next();
                            wordCount++;
                        }

                        System.out.println(currentFile + ": " + wordCount);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            wordCountThreads.start();
        }
    }
}