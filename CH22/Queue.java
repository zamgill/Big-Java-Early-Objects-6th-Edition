package CH22;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Queue {
    private LinkedList<String> list;
    private Lock queueUpdateLock;
    private Condition queueEmptyCondition;
    private int MAX_SIZE;

    public Queue(int size) {
        list = new LinkedList<>();
        MAX_SIZE = size;
        queueUpdateLock = new ReentrantLock();
        queueEmptyCondition = queueUpdateLock.newCondition();
    }

    public void add(String aString) {
        queueUpdateLock.lock();
        try {
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
            while (list.size() < MAX_SIZE) {
                queueEmptyCondition.await();
            }
            while (!list.isEmpty()) {
                System.out.println("Removing | " + list.removeFirst() + " | " + Thread.currentThread().getName());
            }
        } finally {
            queueUpdateLock.unlock();
        }
    }

    public int size() {
        return list.size();
    }
}