package CH22;

import java.util.LinkedList;

public class E22_1 {
    public static void main(String[] args) {
        LinkedList<String> grocceryList = new LinkedList<>();

        Thread t1 = new Thread(new Runnable() {

            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println("T1 | Adding item...");
                    String item = "Item " + i;
                    grocceryList.add(item);
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {

            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println("T2 | Size = " + grocceryList.size());
                }
            }
        });

        t1.start();
        t2.start();
    }
}