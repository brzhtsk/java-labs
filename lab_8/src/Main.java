import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import static java.lang.System.out;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);

        int numThreads;
        if (args.length > 0) {
            numThreads = Integer.parseInt(args[0]);
        } else {
            out.print("Введіть кількість потоків: ");
            while (true) {
                try {
                    numThreads = Integer.parseInt(scanner.nextLine());
                    if (numThreads > 0) break;
                    out.print("Кількість потоків має бути > 0. Спробуйте ще: ");
                } catch (NumberFormatException e) {
                    out.print("Введіть ціле число: ");
                }
            }
        }

        long totalIterations = 1_000_000_000L;

        long startTime = System.nanoTime();
        double piValue = computePi(totalIterations, numThreads);
        double timeTaken = (System.nanoTime() - startTime) / 1_000_000.0;

        out.println("PI is " + piValue);
        out.println("THREADS " + numThreads);
        out.printf("ITERATIONS %,d%n", totalIterations);
        out.printf(Locale.US, "TIME %.2fms%n", timeTaken);
    }

    private static double computePi(long totalIter, int numTh) throws InterruptedException {
        long chunkSize = totalIter / numTh;
        long[] counts = new long[numTh];
        List<Thread> threadList = new ArrayList<>();

        for (int i = 0; i < numTh; i++) {
            final int idx = i;
            Thread th = new Thread(() -> {
                long count = 0;
                ThreadLocalRandom rnd = ThreadLocalRandom.current();
                for (long j = 0; j < chunkSize; j++) {
                    double x = rnd.nextDouble();
                    double y = rnd.nextDouble();
                    if (x * x + y * y <= 1.0) {
                        count++;
                    }
                }
                counts[idx] = count;
            });
            threadList.add(th);
            th.start();
        }

        for (Thread t : threadList) {
            t.join();
        }

        long totalInCircle = 0;
        for (long c : counts) {
            totalInCircle += c;
        }

        return 4.0 * totalInCircle / totalIter;
    }
}