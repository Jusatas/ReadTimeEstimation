module lt.jusaats.readtimeestimation {
    requires javafx.controls;
    requires javafx.fxml;


    opens lt.jusatas.readtimeestimation to javafx.fxml;
    exports lt.jusatas.readtimeestimation;
}