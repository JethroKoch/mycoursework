package Controller;

import Models.ProductService;
import Models.ProductView;
import Veiw.HomeStage;
import Veiw.StockAdjustmentStage;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class StockAdjustmentStageController {
    private static ArrayList<ProductView> currentItem = new ArrayList<>();

    public StockAdjustmentStageController(){}

    public void openSearchProduct(ActionEvent ae, Stage stage){ StockAdjustmentStage.searchPane(stage); }
    public void openNewProduct(ActionEvent ae, Stage stage) {
        StockAdjustmentStage.newPane(stage);
    }
    public void openEditProduct(ActionEvent ae, Stage stage) {
        StockAdjustmentStage.editPane(stage);
    }

    public void loadResults(TextField productDescription){
        currentItem.clear();
        String product = productDescription.getText();
        currentItem.addAll(ProductService.selectByDescription(currentItem,product, HomeStage.database));

        StockAdjustmentStage.stockTable.setItems(FXCollections.observableArrayList(currentItem));
    }

    @SuppressWarnings("Duplicates")
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
