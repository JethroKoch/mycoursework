package Controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;

public class HistoryStageController {

    public HistoryStageController(){}

    public static void error(ActionEvent ae) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("This feature has not been implicated yet");
        alert.showAndWait();
    }
}
