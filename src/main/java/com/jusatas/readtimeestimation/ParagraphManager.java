package com.jusatas.readtimeestimation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
        try (InputStream inputStream = getClass().getResourceAsStream(fileName);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            StringBuilder paragraph = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    paragraph.append(line).append("\n");
                } else {
                    paragraphs.add(paragraph.toString());
                    paragraph.setLength(0);
                }
            }
            if (paragraph.length() > 0) {
                paragraphs.add(paragraph.toString());
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
        String[] words = text.split("\\s+");
        return words.length;
    }
}
