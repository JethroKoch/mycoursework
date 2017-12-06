package Controller;

import Veiw.CustomerStage;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class CustomerStageController {
    public static void error(ActionEvent ae) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("This feature has not been implicated yet");
        alert.showAndWait();
    }
}
