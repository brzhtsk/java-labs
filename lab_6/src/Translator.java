import java.util.HashMap;
public class Translator {
    private HashMap<String, String> wordMap = new HashMap<>();
    public void addWord(String english, String ukrainian) {
        wordMap.put(english, ukrainian);
    }
    public String translatePhrase(String phrase) {
        String[] parts = phrase.split(" ");
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < parts.length; i++) {
            String translation = wordMap.get(parts[i]);
            if (translation != null) {
                result.append(translation);
            } else {
                result.append(parts[i]);
            }
            if (i < parts.length - 1) {
                result.append(" ");
            }
        }
        return result.toString();
    }
}