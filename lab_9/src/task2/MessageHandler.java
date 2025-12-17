package task2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class MessageHandler {
    public static void main(String[] args) throws InterruptedException {
        SafeRingBuffer<String> buffer1 = new SafeRingBuffer<>(50);
        SafeRingBuffer<String> buffer2 = new SafeRingBuffer<>(50);
        setupThreads(buffer1, buffer2);
        for (int i = 0; i < 100; i++) {
            System.out.println(buffer2.getItem());
        }
    }

    private static void setupThreads(SafeRingBuffer<String> buffer1, SafeRingBuffer<String> buffer2) {
        ThreadFactory daemonFactory = r -> {
            Thread t = new Thread(r);
            t.setDaemon(true);
            return t;
        };
        ExecutorService generators = Executors.newFixedThreadPool(5, daemonFactory);
        ExecutorService movers = Executors.newFixedThreadPool(2, daemonFactory);
        for (int i = 1; i <= 5; i++) {
            int threadNum = i;
            generators.submit(() -> {
                int msgCount = 1;
                while (true) {
                    String msg = "Потік " + threadNum + " створив: Повідомлення #" + msgCount;
                    msgCount++;
                    try {
                        buffer1.addItem(msg);
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        return;
                    }
                }
            });
        }
        for (int i = 6; i <= 7; i++) {
            int threadNum = i;
            movers.submit(() -> {
                while (true) {
                    try {
                        String original = buffer1.getItem();
                        String newMsg = "Потік " + threadNum + " перемістив: " + original;
                        buffer2.addItem(newMsg);
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        return;
                    }
                }
            });
        }
    }
}