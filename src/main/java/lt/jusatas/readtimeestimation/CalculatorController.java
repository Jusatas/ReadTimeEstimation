package lt.jusatas.readtimeestimation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class CalculatorController {

    @FXML Label timerLabel;

    @FXML Button timerButton;

    private boolean timerRunning = false;

    @FXML
    void onTimerButtonClick(ActionEvent event) {
        if (timerRunning) {
            // stop timer
        } else {
            // start timer
        }
    }
}