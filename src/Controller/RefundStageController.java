package Controller;

import Models.*;
import View.HomeStage;
import View.RefundStage;
import javafx.collections.FXCollections;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class RefundStageController {
    private ArrayList<RefundModel> currentItem = new ArrayList<>();

    public RefundStageController(){}

    public void searchTransactions(TextField transactionId,Label totalCost){
            RefundStage.RefundItems.getItems().clear();
            currentItem.clear();
            if(transactionId.getText()!=null) {
                int id = Integer.parseInt(transactionId.getText());
                RefundService.SelectForRefund(currentItem,id, HomeStage.database);
                if (currentItem != null) {
                    RefundStage.RefundItems.setItems(FXCollections.observableArrayList(currentItem));
                    double cost =0;
                    for(RefundModel current:currentItem) {
                        cost += current.getTotalCost();
                    }
                    totalCost.setText("£"+Double.toString(cost));
                } else {
                    currentItem.clear();
                    RefundStage.RefundItems.getItems().clear();
                }
            }
    }

    public void refundItem(Label totalCost){
        RefundModel selectedItem = RefundStage.RefundItems.getSelectionModel().getSelectedItem();
        TransactionService.deleteById(selectedItem.getTransactionId(),HomeStage.database);
        ProductModel currentProduct = ProductService.SelectByDescription(selectedItem.getProductDescription(),HomeStage.database);
        currentProduct.setInStock(currentProduct.getInStock()+1);
        BasketService.deleteByID(selectedItem.getTransactionId(),HomeStage.database);
        ProductService.save(currentProduct,HomeStage.database);
        RefundStage.RefundItems.getItems().remove(selectedItem);
        double cost =0;
        for(RefundModel current:RefundStage.RefundItems.getItems()) {
            if(current.getTotalCost()!=0){
                cost += current.getTotalCost();
            }
        }
        totalCost.setText("£"+Double.toString(cost));
    }
    public void closeStage(Pane parent, Stage stage) {

        parent.setDisable(false);
        stage.close();

    }
}
