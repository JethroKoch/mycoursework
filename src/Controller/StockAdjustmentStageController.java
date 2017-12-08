package Controller;

import Veiw.StockAdjustmentStage;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class StockAdjustmentStageController {
    public StockAdjustmentStageController(){}

    public void openSearchProduct(ActionEvent ae, Stage stage){ StockAdjustmentStage.searchPane(stage); }
    public void openNewProduct(ActionEvent ae, Stage stage) {
        StockAdjustmentStage.newPane(stage);
    }
    public void openEditProduct(ActionEvent ae, Stage stage) {
        StockAdjustmentStage.editPane(stage);
    }
    public static void error(ActionEvent ae) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("This feature has not been implicated yet");
        alert.showAndWait();
    }
    public void closeStage(Pane parent, Stage stage) {

        parent.setDisable(false);
        stage.close();

    }

}
