package task3;

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

        System.out.println("Enter the path to the output file:");
        String outputPathStr = scanner.nextLine();
        Path outputPath = Paths.get(outputPathStr);

        System.out.println("Enter the key character for encryption:");
        char key = scanner.nextLine().charAt(0);

        Encoder encoder = new KeyEncoder(key);
        Decoder decoder = new KeyDecoder(key);

        try (EncodeInputStream eis = new EncodeInputStream(new FileInputStream(inputPath.toFile()), encoder);
             DecodeOutputStream dos = new DecodeOutputStream(new FileOutputStream(outputPath.toFile()), decoder)) {

            byte[] encodedBytes = eis.readAndEncode();
            System.out.print("Encoded content: ");
            for (byte b : encodedBytes) {
                System.out.print((char) b);
            }
            System.out.println();

            dos.write(encodedBytes);
            System.out.println("Decoding and saving completed.");

            System.out.println("Enter the path to save encoded bytes as object (including file name):");
            String savePathStr = scanner.nextLine();
            Path savePath = Paths.get(savePathStr);

            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(savePath.toFile()))) {
                oos.writeObject(encodedBytes);
                System.out.println("Encoded bytes saved as object.");
            } catch (IOException e) {
                System.out.println("Error saving object: " + e.getMessage());
            }
        } catch (IOException e) {
            System.out.println("IO error: " + e.getMessage());
        } catch (RuntimeException e) {
            System.out.println("Runtime error: " + e.getMessage());
        }
    }
}