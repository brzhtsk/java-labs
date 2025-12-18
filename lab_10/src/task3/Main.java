package task3;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    private static Locale currentLocale = Locale.ENGLISH;
    private static ResourceBundle bundle = ResourceBundle.getBundle("location.messages", currentLocale);

    public static void main(String[] args) {
        logger.info("Application started");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            bundle = ResourceBundle.getBundle("location.messages", currentLocale);

            System.out.println(bundle.getString("menu.process"));
            System.out.println(bundle.getString("menu.change.language"));
            System.out.println(bundle.getString("menu.exit"));
            System.out.print(bundle.getString("select.option"));

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println(bundle.getString("invalid.input"));
                continue;
            }

            if (choice == 1) {
                processFile(scanner);
            } else if (choice == 2) {
                changeLanguage(scanner);
            } else if (choice == 3) {
                break;
            } else {
                System.out.println(bundle.getString("invalid.input"));
            }
        }

        logger.info("Application ended");
        scanner.close();
    }

    private static void processFile(Scanner scanner) {
        System.out.println(bundle.getString("input.path"));
        String inputPathStr = scanner.nextLine();
        Path inputPath = Paths.get(inputPathStr);

        System.out.println(bundle.getString("output.path"));
        String outputPathStr = scanner.nextLine();
        Path outputPath = Paths.get(outputPathStr);

        System.out.println(bundle.getString("key.char"));
        String keyInput = scanner.nextLine();
        if (keyInput.isEmpty()) {
            System.out.println(bundle.getString("invalid.key"));
            return;
        }
        char key = keyInput.charAt(0);

        Encoder encoder = new KeyEncoder(key);
        Decoder decoder = new KeyDecoder(key);

        try (EncodeInputStream eis = new EncodeInputStream(new FileInputStream(inputPath.toFile()), encoder);
             DecodeOutputStream dos = new DecodeOutputStream(new FileOutputStream(outputPath.toFile()), decoder)) {

            logger.debug("Starting encoding process");

            byte[] encodedBytes = eis.readAndEncode();

            System.out.print(bundle.getString("encoded.content"));
            for (byte b : encodedBytes) {
                System.out.print((char) b);
            }
            System.out.println();

            dos.write(encodedBytes);
            System.out.println(bundle.getString("decoding.completed"));

            logger.info("Encoding and decoding completed successfully");

            System.out.println(bundle.getString("save.path"));
            String savePathStr = scanner.nextLine();
            Path savePath = Paths.get(savePathStr);

            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(savePath.toFile()))) {
                oos.writeObject(encodedBytes);
                System.out.println(bundle.getString("saved.object"));
                logger.debug("Saved encoded bytes to object file");
            } catch (IOException e) {
                logger.error("Error saving object file", e);
                System.out.println(bundle.getString("error.saving") + e.getMessage());
            }
        } catch (IOException e) {
            logger.error("IO error occurred", e);
            System.out.println(bundle.getString("io.error") + e.getMessage());
        } catch (RuntimeException e) {
            logger.error("Runtime error occurred", e);
            System.out.println(bundle.getString("runtime.error") + e.getMessage());
        }
    }

    private static void changeLanguage(Scanner scanner) {
        ResourceBundle tempBundle = ResourceBundle.getBundle("location.messages", Locale.ENGLISH);

        System.out.println(tempBundle.getString("language.english"));
        System.out.println(tempBundle.getString("language.spanish"));
        System.out.print(tempBundle.getString("select.language"));

        int lang;
        try {
            lang = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println(tempBundle.getString("invalid.input"));
            return;
        }

        if (lang == 1) {
            currentLocale = Locale.ENGLISH;
        } else if (lang == 2) {
            currentLocale = new Locale("es", "ES");
        } else {
            System.out.println(tempBundle.getString("invalid.input"));
            return;
        }

        bundle = ResourceBundle.getBundle("location.messages", currentLocale);
        System.out.println(bundle.getString("language.changed"));
    }
}