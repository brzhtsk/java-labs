import java.util.Scanner;
import java.util.function.IntPredicate;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введіть число n: ");
        int n = scanner.nextInt();

        IntPredicate isPrime = num -> {
            if (num < 2) return false;
            for (int i = 2; i <= Math.sqrt(num); i++) {
                if (num % i == 0) return false;
            }
            return true;
        };

        java.util.function.ToIntFunction<Integer> countOnes = num ->
                Integer.toBinaryString(num).chars().map(ch -> ch == '1' ? 1 : 0).sum();

        var result = IntStream.rangeClosed(2, n)
                .filter(isPrime::test)
                .boxed()
                .max((a, b) -> Integer.compare(countOnes.applyAsInt(a), countOnes.applyAsInt(b)))
                .orElse(null);

        if (result == null) {
            System.out.println("Простих чисел не знайдено");
        } else {
            int prime = result;
            int ones = countOnes.applyAsInt(prime);
            System.out.println("Просте число з максимальною кількістю одиниць: " + prime);
            System.out.println("Двійкова форма: " + Integer.toBinaryString(prime));
            System.out.println("Кількість одиниць: " + ones);
        }

        scanner.close();
    }
}