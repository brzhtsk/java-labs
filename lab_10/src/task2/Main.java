package task2;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        logger.info("Application started");

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

            logger.debug("Starting encoding process");

            byte[] encodedBytes = eis.readAndEncode();
            System.out.print("Encoded content: ");
            for (byte b : encodedBytes) {
                System.out.print((char) b);
            }
            System.out.println();

            dos.write(encodedBytes);
            System.out.println("Decoding and saving completed.");

            logger.info("Encoding and decoding completed successfully");

            System.out.println("Enter the path to save encoded bytes as object (including file name):");
            String savePathStr = scanner.nextLine();
            Path savePath = Paths.get(savePathStr);

            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(savePath.toFile()))) {
                oos.writeObject(encodedBytes);
                System.out.println("Encoded bytes saved as object.");
                logger.debug("Saved encoded bytes to object file");
            } catch (IOException e) {
                logger.error("Error saving object file", e);
                System.out.println("Error saving object: " + e.getMessage());
            }
        } catch (IOException e) {
            logger.error("IO error occurred", e);
            System.out.println("IO error: " + e.getMessage());
        } catch (RuntimeException e) {
            logger.error("Runtime error occurred", e);
            System.out.println("Runtime error: " + e.getMessage());
        }

        logger.info("Application ended");
    }
}