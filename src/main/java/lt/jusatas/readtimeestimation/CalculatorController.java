package lt.jusatas.readtimeestimation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class CalculatorController {

    @FXML TextArea paragraphTextArea;

    @FXML Label timerLabel;
    @FXML Button timerButton;

    @FXML Label timerResultS;

    @FXML Label wordsRead;
    @FXML Label wordsPerMinute;



    private boolean timerRunning = false;
    private String paragraphString;

    private TimerManager timerManager = TimerManager.getInstance();
    private ParagraphManager paragraphManager = ParagraphManager.getInstance("paragraphs.txt");


    @FXML
    void onTimerButtonClick(ActionEvent event) {
        if (timerRunning) {
            timerManager.stopTimer();
            timerRunning = false;
            timerButton.setText("Start");

            int wordCount = paragraphManager.countWords(paragraphString);
            long timeS = timerManager.getElapsedTimeMS() / 1000;
            timerResultS.setText(String.valueOf(timeS));
            wordsRead.setText(String.valueOf(wordCount));
            wordsPerMinute.setText(String.valueOf((wordCount / timeS) * 60.0));
        } else {
            timerManager.startTimer();
            timerRunning = true;
            paragraphString = paragraphManager.getRandomParagraph();
            paragraphTextArea.setText(paragraphString);

            timerButton.setText("Stop");
            timerResultS.setText("");
        }
    }
}