package task1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TestTransfers {
    public static void main(String[] args) throws InterruptedException {
        Random random = new Random();
        int numAccounts = 100;
        int numThreads = 1000;
        Bank myBank = new Bank();
        List<Account> accountsList = new ArrayList<>();
        for (int i = 0; i < numAccounts; i++) {
            accountsList.add(new Account(random.nextInt(100, 500000)));
        }
        long totalBefore = 0;
        for (Account acc : accountsList) {
            totalBefore += acc.getMoney();
        }
        System.out.println("Гроші до переказів: " + totalBefore);
        runTests(numThreads, numAccounts, accountsList, myBank);
        long totalAfter = 0;
        for (Account acc : accountsList) {
            totalAfter += acc.getMoney();
        }
        System.out.println("Гроші після переказів: " + totalAfter);
        if (totalBefore == totalAfter) {
            System.out.println("Все OK, гроші не втрачено!");
        } else {
            System.out.println("Проблема, гроші втрачено!");
        }
    }

    private static void runTests(int numThreads, int numAccounts, List<Account> accountsList, Bank myBank) throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(numThreads);
        Random random = new Random();
        for (int i = 0; i < numThreads; i++) {
            service.submit(() -> {
                int fromIdx = random.nextInt(numAccounts);
                int toIdx = random.nextInt(numAccounts);
                while (toIdx == fromIdx) {
                    toIdx = random.nextInt(numAccounts);
                }
                Account fromAcc = accountsList.get(fromIdx);
                Account toAcc = accountsList.get(toIdx);
                int sendAmount = random.nextInt(500);
                myBank.sendMoney(fromAcc, toAcc, sendAmount);
            });
        }
        service.shutdown();
        service.awaitTermination(2, TimeUnit.MINUTES);
    }
}