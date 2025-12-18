package task1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileMaxWordsReader {
    public static String findLineWithMaxWords(Path filePath) throws IOException {
        List<String> lines = Files.readAllLines(filePath);
        String maxLine = "";
        int maxWords = 0;
        for (String line : lines) {
            String[] words = line.split(" ");
            int wordCount = words.length;
            if (wordCount > maxWords) {
                maxWords = wordCount;
                maxLine = line;
            }
        }
        if (maxLine.isEmpty()) {
            throw new RuntimeException("No lines found in file.");
        }
        return maxLine;
    }
}