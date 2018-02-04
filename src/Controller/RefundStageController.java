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
            if(transactionId.getText().isEmpty()) {
                HomeStageController.genericError("No criteria for search");
                //Checks whether the search criteria is empty and if it as an alert says so
            }else{
                int id = Integer.parseInt(transactionId.getText());
                RefundService.SelectForRefund(currentItem,id, HomeStage.database);
                //searches the database for transaction matching the search criteria
                if (currentItem.size()!=0) {
                    RefundStage.RefundItems.setItems(FXCollections.observableArrayList(currentItem));
                    double cost =0;
                    for(RefundModel current:currentItem) {
                        cost += current.getTotalCost();
                    }
                    totalCost.setText("£"+Double.toString(cost));
                    //if the results are not empty they are added to the table in refund stage
                } else {
                    HomeStageController.genericError("Not a valid TransactionID");
                }//If the results are empty an alert comes up saying so.
            }
    }

    public void refundItem(Label totalCost){
        RefundModel selectedItem = RefundStage.RefundItems.getSelectionModel().getSelectedItem();
        if(selectedItem==null) {
            HomeStageController.genericError("No item selected for refund");
        }else{
            TransactionService.deleteById(selectedItem.getTransactionId(), HomeStage.database);
            ProductModel currentProduct = ProductService.SelectByDescription(selectedItem.getProductDescription(), HomeStage.database);
            currentProduct.setInStock(currentProduct.getInStock() + 1);
            BasketService.deleteByID(selectedItem.getTransactionId(), HomeStage.database);
            ProductService.save(currentProduct, HomeStage.database);
            RefundStage.RefundItems.getItems().remove(selectedItem);
            double cost = 0;
            for (RefundModel current : RefundStage.RefundItems.getItems()) {
                if (current.getTotalCost() != 0) {
                    cost += current.getTotalCost();
                }
            }
            totalCost.setText("£" + Double.toString(cost));
        }
    }
    public void closeStage(Pane parent, Stage stage) {

        parent.setDisable(false);
        stage.close();

    }
}
