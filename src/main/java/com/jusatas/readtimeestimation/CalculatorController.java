package com.jusatas.readtimeestimation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class CalculatorController {

    @FXML TextArea paragraphTextArea;
    @FXML TextField bookName, wordCount, pageCount;
    @FXML Button timerButton, addBookButton, calculateButton;
    @FXML Label timerResultS, wordsRead, wordsPerMinute, timeSpentLabel, wordsReadLabel,
            wpmLabel, bookNameLabel, pagecountLabel, wordcountLabel, infoLabel;
    @FXML TableView<Book> bookTableView;
    @FXML ChoiceBox<String> languageChoiceBox;

    private ObservableList<Book> bookList;
    private boolean timerRunning = false;
    private String paragraphString;
    private TimerManager timerManager = TimerManager.getInstance();
    private ParagraphManager paragraphManager;

    private BookTableViewManager bookTableViewManager;
    private ResourceBundle bundle;
    private Locale currentLocale = new Locale("en");

    public void initialize() {
        languageChoiceBox.setItems(FXCollections.observableArrayList("English", "Lithuanian"));
        languageChoiceBox.setValue("English");

        Locale locale = new Locale("en");
        bundle = ResourceBundle.getBundle("bundle", locale);
        bookTableViewManager = new BookTableViewManager(bookTableView, locale);
        bookList = FXCollections.observableArrayList();

        addSampleBooks();

        bookTableViewManager.setItems(bookList);
        paragraphManager = ParagraphManager.getInstance(locale);

        languageChoiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            switch (newValue) {
                case "English":
                    updateLocale(new Locale("en"));
                    break;
                case "Lithuanian":
                    updateLocale(new Locale("lt"));
                    break;
            }
        });

    }

    private void addSampleBooks() {
        bookList.addAll(
                new Book("The Bible (ESV)", 757439, true),
                new Book("Metai", 20872, true),
                new Book("The Lord of the Rings series", 550147, true)
        );
    }

    private void updateLocale(Locale locale) {
        this.currentLocale = locale;

        bundle = ResourceBundle.getBundle("bundle", locale);
        bookTableViewManager.updateLocale(locale);

        timerButton.setText(bundle.getString("startTimer"));
        addBookButton.setText(bundle.getString("addBook"));
        calculateButton.setText(bundle.getString("calculateResult"));
        timeSpentLabel.setText(bundle.getString("timeSpent"));
        wordsReadLabel.setText(bundle.getString("wordsRead"));
        wpmLabel.setText(bundle.getString("wordsPerMinute"));
        bookNameLabel.setText(bundle.getString("bookName"));
        pagecountLabel.setText(bundle.getString("pageCount"));
        wordcountLabel.setText(bundle.getString("wordCountOptional"));
        infoLabel.setText(bundle.getString("info"));

        paragraphManager.setLocale(locale);

    }

    @FXML
    void onTimerButtonClick() {
        if (timerRunning) {
            timerManager.stopTimer();
            timerRunning = false;
            timerButton.setText(bundle.getString("startTimer"));
            displayTimerResults();
        } else {
            timerManager.startTimer();
            timerRunning = true;
            paragraphString = paragraphManager.getRandomParagraph();
            paragraphTextArea.setText(paragraphString);
            timerButton.setText(bundle.getString("stopTimer"));
            timerResultS.setText("");
        }
    }

    private void displayTimerResults() {
        int wordCount = paragraphManager.countWords(paragraphString);
        long timeS = timerManager.getElapsedTimeMS() / 1000;
        if (timeS != 0) {
            int wpm = (int) (wordCount / timeS * 60);
            wordsPerMinute.setText(String.valueOf(wpm));
        }
        timerResultS.setText(String.valueOf(timeS));
        wordsRead.setText(String.valueOf(wordCount));
    }

    @FXML
    void onAddBookButtonClick() {
        String name = bookName.getText();
        String pageCountText = pageCount.getText();
        String wordCountText = wordCount.getText();
        Book newBook;

        if (name.isEmpty() || (pageCountText.isEmpty() && wordCountText.isEmpty())) {
            showAlert(Alert.AlertType.ERROR, bundle.getString("error"), bundle.getString("ERROR_fieldsEmpty"));
            return;

        } else if (pageCountText.isEmpty()) {
            newBook = new Book(name, Integer.parseInt(wordCountText), true);
        } else if (wordCountText.isEmpty()) {
            newBook = new Book(name, Integer.parseInt(pageCountText));
        } else {
            newBook = new Book(name, Integer.parseInt(wordCountText), Integer.parseInt(pageCountText));
        }

        bookList.add(newBook);

        bookName.clear();
        pageCount.clear();
        wordCount.clear();
    }

    @FXML
    void onCalculateButtonClick() throws IOException {
        int readSpeed = 0;
        try {
            readSpeed = Integer.parseInt(wordsPerMinute.getText());
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, bundle.getString("error"), bundle.getString("ERROR_noTimer"));
            return;
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("timer-calculator-result-view.fxml"));
        Parent root = loader.load();
        ResultViewController resultViewController = loader.getController();

        resultViewController.setData(readSpeed, bookList, currentLocale, bundle);

        resultViewController.initialize();

        Stage resultView = new Stage();
        resultView.setScene(new Scene(root));
        resultView.show();
    }

    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}