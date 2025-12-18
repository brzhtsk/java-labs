package task1;

import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class StringModifier {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        String literalString = "initial value";
        System.out.println("Original literal string: " + literalString);

        System.out.print("Enter replacement for literal string: ");
        String replacementLiteral = scanner.nextLine();

        modifyStringValue(literalString, replacementLiteral);
        System.out.println("Modified literal string: " + literalString);

        System.out.print("Enter a string: ");
        String inputString = scanner.nextLine();
        System.out.println("Original input string: " + inputString);

        System.out.print("Enter replacement for input string: ");
        String replacementInput = scanner.nextLine();

        modifyStringValue(inputString, replacementInput);
        System.out.println("Modified input string: " + inputString);
    }

    private static void modifyStringValue(String target, String newValue) throws Exception {
        Field valueField = String.class.getDeclaredField("value");
        valueField.setAccessible(true);
        byte[] newBytes = newValue.getBytes(StandardCharsets.UTF_8);
        valueField.set(target, newBytes);
    }
}