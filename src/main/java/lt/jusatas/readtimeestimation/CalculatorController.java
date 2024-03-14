package lt.jusatas.readtimeestimation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class CalculatorController {

    @FXML TextArea paragraphTextArea;
    @FXML TextField bookName;
    @FXML TextField pageCount;
    @FXML TextField wordCount;

    @FXML Button timerButton;
    @FXML Button calculateButton;

    @FXML Label timerLabel;
    @FXML Label timerResultS;
    @FXML Label wordsRead;
    @FXML Label wordsPerMinute;



    private boolean timerRunning = false;
    private String paragraphString;

    TimerManager timerManager = ManagerFactory.createTimerManager();
    ParagraphManager paragraphManager = ManagerFactory.createParagraphManger("paragraphs.txt");

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
    void onCalculateButtonClick(ActionEvent event) {

    }
}