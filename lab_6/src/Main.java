import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Translator translator = new Translator();

        translator.addWord("apple", "яблуко");
        translator.addWord("book", "книга");
        translator.addWord("cat", "кіт");
        Scanner scanner = new Scanner(System.in);

        System.out.println("Do you want to add more words? (yes/no)");
        String response = scanner.nextLine();
        if (response.equalsIgnoreCase("yes")) {
            while (true) {
                System.out.print("Enter English word (or 'stop' to finish): ");
                String english = scanner.nextLine();
                if (english.equalsIgnoreCase("stop")) {
                    break;
                }
                System.out.print("Enter Ukrainian translation: ");
                String ukrainian = scanner.nextLine();
                translator.addWord(english, ukrainian);
            }
        }

        System.out.print("Enter an English phrase to translate: ");
        String phrase = scanner.nextLine();

        System.out.println("Ukrainian translation: " + translator.translatePhrase(phrase));
    }
}