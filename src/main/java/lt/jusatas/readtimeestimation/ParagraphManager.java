package lt.jusatas.readtimeestimation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class ParagraphManager {
    private static ParagraphManager instance;
    private ArrayList<String> paragraphs = new ArrayList<>();
    private Random random = new Random();

    private ParagraphManager(String fileName) {
        loadParagraphs(fileName);
    }

    public static ParagraphManager getInstance(String fileName) {
        if (instance == null) {
            instance = new ParagraphManager(fileName);
        }
        return instance;
    }

    private void loadParagraphs(String fileName) {

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            StringBuilder paragraph = new StringBuilder();
            while ((line = reader.readLine()) != null) {      // if the line is NOT empty
                if (!line.trim().isEmpty()) {                       // if the line is NOT empty after trimming whitespace
                    paragraph.append(line).append("\n");                 // add line and newline
                } else {                                            // if the line IS empty (end of paragraph)
                    paragraphs.add(paragraph.toString());                // add the whole paragraph
                    paragraph.setLength(0);                              // reset
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getRandomParagraph() {
        int randomIndex = random.nextInt(paragraphs.size());
        return paragraphs.get(randomIndex);
    }

    public int countWords(String text) {
        String[] words = text.split("\\s+"); // regex: one or more whitespace characters
        return words.length;
    }
}
