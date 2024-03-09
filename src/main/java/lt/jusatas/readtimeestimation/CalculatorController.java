package lt.jusatas.readtimeestimation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class CalculatorController {

    @FXML Label timerLabel;

    @FXML Button timerButton;

    @FXML Label timerResultMS;
    @FXML Label timerResultS;

    private boolean timerRunning = false;

    private TimerManager timerManager = TimerManager.getInstance();


    @FXML
    void onTimerButtonClick(ActionEvent event) {
        if (timerRunning) {
            timerManager.stopTimer();
            timerRunning = false;
            timerButton.setText("Start");

            timerResultMS.setText(String.valueOf(timerManager.getElapsedTimeMS()));
            timerResultS.setText(String.valueOf(timerManager.getElapsedTimeMS() / 1000));
        } else {
            timerManager.startTimer();
            timerRunning = true;
            timerButton.setText("Stop");

            timerResultMS.setText("");
            timerResultS.setText("");
        }
    }
}