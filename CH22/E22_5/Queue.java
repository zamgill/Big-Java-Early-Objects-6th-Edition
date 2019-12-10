package CH22.E22_5;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Queue {
    private ArrayBlockingQueue<String> list;
    private Lock queueUpdateLock;
    private Condition queueEmptyCondition;

    public Queue(int size) {
        list = new ArrayBlockingQueue<>(size);
        queueUpdateLock = new ReentrantLock();
        queueEmptyCondition = queueUpdateLock.newCondition();
    }

    public void add(String aString) throws InterruptedException {
        queueUpdateLock.lock();
        try {
            while (list.remainingCapacity() == 0) {
                queueEmptyCondition.await();
            }
            System.out.println("Adding\t | " + aString + " | " + Thread.currentThread().getName());
            list.add(aString);
            queueEmptyCondition.signalAll();
        } finally {
            queueUpdateLock.unlock();
        }
    }

    public void remove() throws InterruptedException {
        queueUpdateLock.lock();
        try {
            while (list.remainingCapacity() != 0) {
                queueEmptyCondition.await();
            }
            while (!list.isEmpty()) {
                list.clear();
                System.out
                        .println("Clearing | Size is now " + list.size() + "\t\t| " + Thread.currentThread().getName());
                queueEmptyCondition.signalAll();
            }
        } finally {
            queueUpdateLock.unlock();
        }
    }

    public int size() {
        return list.size();
    }

    public int remainingCapacity() {
        return list.remainingCapacity();
    }
}