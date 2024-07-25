module lt.jusaats.readtimeestimation {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.jusatas.readtimeestimation to javafx.fxml;
    exports com.jusatas.readtimeestimation;
}