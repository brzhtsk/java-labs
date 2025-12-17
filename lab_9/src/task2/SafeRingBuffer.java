package task2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class SafeRingBuffer<T> {
    private final Object[] storage;
    private int start = 0;
    private int end = 0;
    private int count = 0;
    private final ReentrantLock locker = new ReentrantLock();
    private final Condition hasSpace = locker.newCondition();
    private final Condition hasData = locker.newCondition();

    public SafeRingBuffer(int size) {
        storage = new Object[size];
    }

    public void addItem(T item) throws InterruptedException {
        locker.lock();
        try {
            while (count == storage.length) {
                hasSpace.await();
            }
            storage[end] = item;
            end = (end + 1) % storage.length;
            count++;
            hasData.signal();
        } finally {
            locker.unlock();
        }
    }

    public T getItem() throws InterruptedException {
        locker.lock();
        try {
            while (count == 0) {
                hasData.await();
            }
            T item = (T) storage[start];
            start = (start + 1) % storage.length;
            count--;
            hasSpace.signal();
            return item;
        } finally {
            locker.unlock();
        }
    }
}