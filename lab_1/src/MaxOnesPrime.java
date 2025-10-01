import java.util.Scanner;

public class MaxOnesPrime {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введіть число n: ");
        int n = scanner.nextInt();

        int maxOnes = 0;
        int resultPrime = 0;

        // Перебираємо всі числа від 2 до n
        for (int i = 2; i <= n; i++) {
            if (isPrime(i)) {
                int ones = countOnesInBinary(i);
                if (ones > maxOnes) {
                    maxOnes = ones;
                    resultPrime = i;
                }
            }
        }

        if (resultPrime == 0) {
            System.out.println("Простих чисел не знайдено");
        } else {
            System.out.println("Просте число з максимальною кількістю одиниць: " + resultPrime);
            System.out.println("Двійкова форма: " + Integer.toBinaryString(resultPrime));
            System.out.println("Кількість одиниць: " + maxOnes);
        }

        scanner.close();
    }

    // Метод для перевірки, чи є число простим
    private static boolean isPrime(int num) {
        if (num < 2) return false;
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) return false;
        }
        return true;
    }

    // Метод для підрахунку одиниць у двійковій формі числа
    private static int countOnesInBinary(int num) {
        String binary = Integer.toBinaryString(num);
        int count = 0;
        for (char c : binary.toCharArray()) {
            if (c == '1') count++;
        }
        return count;
    }
}