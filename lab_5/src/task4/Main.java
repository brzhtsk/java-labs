package task4;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the URL to analyze:");
        String urlStr = scanner.nextLine();

        try {
            URL url = new URL(urlStr);
            String html = readHtmlFromUrl(url);
            Map<String, Integer> tagCounts = HtmlTagCounter.countTags(html);

            System.out.println("Tags in lexicographical order:");
            List<String> sortedTags = new ArrayList<>(tagCounts.keySet());
            Collections.sort(sortedTags);
            for (String tag : sortedTags) {
                System.out.println(tag + ": " + tagCounts.get(tag));
            }

            System.out.println("Tags sorted by frequency (ascending):");
            List<Map.Entry<String, Integer>> sortedByFreq = new ArrayList<>(tagCounts.entrySet());
            sortedByFreq.sort(Comparator.comparingInt(Map.Entry::getValue));
            for (Map.Entry<String, Integer> entry : sortedByFreq) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }

            System.out.println("Enter the path to save the tag counts (including file name):");
            String savePathStr = scanner.nextLine();

            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(savePathStr))) {
                oos.writeObject(tagCounts);
                System.out.println("Tag counts saved successfully.");
            } catch (IOException e) {
                System.out.println("Error saving file: " + e.getMessage());
            }
        } catch (MalformedURLException e) {
            System.out.println("Invalid URL: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error reading URL: " + e.getMessage());
        } catch (RuntimeException e) {
            System.out.println("Runtime error: " + e.getMessage());
        }
    }

    private static String readHtmlFromUrl(URL url) throws IOException {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
        }
        return sb.toString();
    }
}