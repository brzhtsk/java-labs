package task1;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the path to the input file:");
        String inputPathStr = scanner.nextLine();
        Path inputPath = Paths.get(inputPathStr);

        try {
            String maxLine = FileMaxWordsReader.findLineWithMaxWords(inputPath);
            System.out.println("Line with maximum words: " + maxLine);

            System.out.println("Enter the path to save the result (including file name):");
            String savePathStr = scanner.nextLine();
            Path savePath = Paths.get(savePathStr);

            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(savePath.toFile()))) {
                oos.writeObject(maxLine);
                System.out.println("Result saved successfully.");
            } catch (IOException e) {
                System.out.println("Error saving file: " + e.getMessage());
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        } catch (RuntimeException e) {
            System.out.println("Runtime error: " + e.getMessage());
        }
    }
}