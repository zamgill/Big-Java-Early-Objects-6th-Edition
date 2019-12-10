package CH22;

/**
 * This program runs two greeting threads in parallel
 */

public class GreetingThreadRunner {
    public static void main(String[] args) {
        GreetingRunnable r1 = new GreetingRunnable("Hello");
        GreetingRunnable r2 = new GreetingRunnable("Goodbye");
        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);
        t1.start();
        t2.start();
        t1.interrupt();
        t2.interrupt();
    }
}