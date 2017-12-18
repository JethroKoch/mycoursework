package Controller;

import Models.*;
import Veiw.HomeStage;
import Veiw.RefundStage;
import javafx.collections.FXCollections;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class RefundStageController {
    private ArrayList<RefundView> currentItem = new ArrayList<>();

    public RefundStageController(){}

    public void searchTransactions(TextField transactionId,Label totalCost){
            RefundStage.RefundItems.getItems().clear();
            currentItem.clear();
            if(transactionId.getText()!=null) {
                int id = Integer.parseInt(transactionId.getText());
                currentItem.add(RefundService.SelectForRefund(id, HomeStage.database));
                if (currentItem != null) {
                    RefundStage.RefundItems.setItems(FXCollections.observableArrayList(currentItem));
                    double cost =0;
                    for(RefundView current:currentItem) {
                        cost += current.getTotalCost();
                    }
                    totalCost.setText("£"+Double.toString(cost));
                } else {
                    currentItem.clear();
                    RefundStage.RefundItems.getItems().clear();
                }
            }
    }

    public void refundItemL(Label totalCost){
        RefundView selectedItem = RefundStage.RefundItems.getSelectionModel().getSelectedItem();
        TransactionService.deleteById(selectedItem.getTransactionId(),HomeStage.database);
        ProductView currentProduct = ProductService.SelectByDescription(selectedItem.getProductDescription(),HomeStage.database);
        currentProduct.setInStock(currentProduct.getProductID()+1);
        ProductService.save(currentProduct,HomeStage.database);
        RefundStage.RefundItems.getItems().remove(selectedItem);
        double cost =0;
        for(RefundView current:RefundStage.RefundItems.getItems()) {
            if(current.getTotalCost()!=0){
                cost += current.getTotalCost();
            }
        }
        totalCost.setText("£"+Double.toString(cost));
    }

    public static void error() {
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
