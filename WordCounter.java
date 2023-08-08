import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class WordCounter {
    private Set<String> stopWords;

    public WordCounter() {
        // Initialize common stop words to ignore
        stopWords = new HashSet<>();
        stopWords.add("the");
        stopWords.add("and");
        stopWords.add("of");
        stopWords.add("in");
        // Add more stop words as needed
    }

    public void setStopWords(Set<String> customStopWords) {
        stopWords.addAll(customStopWords);
    }

    public int countWords(String text) {
        if (text == null || text.trim().isEmpty()) {
            return 0;
        }

        String[] words = text.split("[\\s.,?!:;()-]+");
        int wordCount = 0;

        for (String word : words) {
            if (!stopWords.contains(word.toLowerCase())) {
                wordCount++;
            }
        }

        return wordCount;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        WordCounter wordCounter = new WordCounter();

        System.out.println("Welcome to the Word Counter!");
        System.out.println("Enter 1 to input text or 2 to provide a file: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        String text = "";

        if (choice == 1) {
            System.out.println("Enter your text: ");
            text = scanner.nextLine();
        } else if (choice == 2) {
            System.out.println("Enter the path to the file: ");
            String filePath = scanner.nextLine();
            try {
                File file = new File(filePath);
                Scanner fileScanner = new Scanner(file);
                while (fileScanner.hasNextLine()) {
                    text += fileScanner.nextLine() + " ";
                }
            } catch (FileNotFoundException e) {
                System.out.println("File not found or could not be read.");
                System.exit(1);
            }
        } else {
            System.out.println("Invalid choice. Please try again.");
            System.exit(1);
        }

        int totalWords = wordCounter.countWords(text);
        System.out.println("Total words (excluding common words): " + totalWords);
    }
}