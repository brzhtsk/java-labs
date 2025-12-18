package task4;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.HashMap;
import java.util.Map;

public class HtmlTagCounter {
    public static Map<String, Integer> countTags(String html) {
        Document doc = Jsoup.parse(html);
        Elements allElements = doc.getAllElements();
        Map<String, Integer> tagCounts = new HashMap<>();
        for (Element element : allElements) {
            String tag = element.tagName();
            tagCounts.put(tag, tagCounts.getOrDefault(tag, 0) + 1);
        }
        return tagCounts;
    }
}