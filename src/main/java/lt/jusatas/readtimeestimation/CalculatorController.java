package lt.jusatas.readtimeestimation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

public class CalculatorController {

    @FXML TextArea paragraphTextArea;
    @FXML TextField bookName;
    @FXML TextField pageCount;
    @FXML TextField wordCount;

    @FXML Button timerButton;
    @FXML Button addBookButton;
    @FXML Button calculateButton;

    @FXML Label timerResultS;
    @FXML Label wordsRead;
    @FXML Label wordsPerMinute;

    @FXML TableView<Book> bookTableView;

    private ObservableList<Book> bookList;
    private boolean timerRunning = false;
    private String paragraphString;
    private boolean timerClicked = false;

    private TimerManager timerManager = ManagerFactory.createTimerManager();
    private ParagraphManager paragraphManager = ManagerFactory.createParagraphManger("paragraphs.txt");
    private BookTableViewManager bookTableViewManager;

    public void initialize() {
        bookTableViewManager = new BookTableViewManager(bookTableView);;
        bookList = FXCollections.observableArrayList();

        Book book = new Book("The Bible (ESV)", 757439, true);
        bookList.add(book);
        book = new Book("Metai", 20872, true);
        bookList.add(book);
        book = new Book("The Lord of the Rings series", 550147, true);
        bookList.add(book);

        bookTableViewManager.setItems(bookList);

    }

    @FXML
    void onTimerButtonClick(ActionEvent event) {
        if (timerRunning) {
            timerManager.stopTimer();
            timerRunning = false;
            timerButton.setText("Start timer");

            int wordCount = paragraphManager.countWords(paragraphString);
            long timeS = timerManager.getElapsedTimeMS() / 1000;
            if (timeS != 0) {
                int wpm = (int)(wordCount / timeS * 60);
                wordsPerMinute.setText(String.valueOf(wpm));
            }
            timerResultS.setText(String.valueOf(timeS));
            wordsRead.setText(String.valueOf(wordCount));
            timerClicked = true;
        } else {
            timerManager.startTimer();
            timerRunning = true;
            paragraphString = paragraphManager.getRandomParagraph();
            paragraphTextArea.setText(paragraphString);

            timerButton.setText("Stop timer");
            timerResultS.setText("");
        }
    }
    @FXML
    void onAddBookButtonClick(ActionEvent event) {
        String name = bookName.getText();
        String pageCountText = pageCount.getText();
        String wordCountText = wordCount.getText();
        Book newBook;

        if (name.isEmpty() || (pageCountText.isEmpty() && wordCountText.isEmpty())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please fill in all the required fields");
            alert.showAndWait();
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
    void onCalculateButtonClick(ActionEvent event) throws IOException {

        if (!timerClicked) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please read the text with the timer.");
            alert.showAndWait();
            return;
        }
        int readSpeed = Integer.parseInt(wordsPerMinute.getText());

        FXMLLoader loader = new FXMLLoader(getClass().getResource("timer-calculator-result-view.fxml"));
        Parent root = loader.load();
        ResultViewController resultViewController = loader.getController();

        resultViewController.setData(readSpeed, bookList);

        resultViewController.initialize();

        Stage resultView = new Stage();
        resultView.setScene(new Scene(root));
        resultView.show();
    }
}