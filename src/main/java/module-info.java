module lt.jusaats.readtimeestimation {
    requires javafx.controls;
    requires javafx.fxml;


    opens lt.jusaats.readtimeestimation to javafx.fxml;
    exports lt.jusaats.readtimeestimation;
}