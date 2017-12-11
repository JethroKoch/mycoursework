package Controller;

import Models.ProductService;
import Models.ProductView;
import Veiw.*;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class HomeStageController {

    private ArrayList<ProductView> currentProduct = new ArrayList<>();

    public HomeStageController() {}

    public static void openCustomerStage(Pane parent) {
        CustomerStage newStage = new CustomerStage(parent);
    }
    public static void openHistoryStage(Pane parent) {
        HistoryStage newStage = new HistoryStage(parent);
    }
    public static void openRefundStage(Pane parent) {
        RefundStage newStage = new RefundStage(parent);
    }
    public static void openStockAdjustmentStage(Pane parent) { StockAdjustmentStage newStage = new StockAdjustmentStage(parent);}

    double total;
    double change;
    double amountGiven;

    public void addProduct(TextField searchBar, Label totalCost1, TextField amountGiven1, Label change1){
        System.out.println("Attempt to search for: " + searchBar.getText());

        int productID = 0;
        try {
            productID = Integer.parseInt(searchBar.getText());
        }
        catch (NumberFormatException nfe) {
            System.out.println (nfe.getMessage());
        }

        currentProduct.add(ProductService.selectById(productID, HomeStage.database));

        HomeStage.productsTable.setItems(FXCollections.observableArrayList(currentProduct));


        total = 0;
        for(ProductView price:HomeStage.productsTable.getItems()){
            if(price.getPrice()!=0) {
                total += price.getPrice();
                totalCost1.setText(Double.toString(total));
            }
        }
        checkChange(change1, amountGiven1);
    }

    public void removeItem(Label change1,Label totalCost1,TextField amountGiven1){
        ProductView selectedItem = HomeStage.productsTable.getSelectionModel().getSelectedItem();
        total = 0;
        HomeStage.productsTable.getItems().remove(selectedItem);
        currentProduct.remove(selectedItem);
        for (ProductView price : HomeStage.productsTable.getItems()) {
            if (price.getPrice() != 0) {
                total += price.getPrice();
            }
        }
        totalCost1.setText(Double.toString(total));
        checkChange(change1, amountGiven1);
    }

    public void updateCost(TextField amountGiven1, Label change1){
        amountGiven = Double.parseDouble(amountGiven1.getText());
        checkChange(change1,amountGiven1);
    }
    public void checkChange(Label change1,TextField amountGiven1){

        if(Double.parseDouble(amountGiven1.getText())==0){
            amountGiven1.setText(Double.toString(amountGiven));
            change = 0;
            change1.setText(Double.toString(change));
        }else {
            change = amountGiven - total;
            if (change < 0) {
                change1.setStyle("-fx-background-color: #f7070c");
            } else {
                change1.setStyle("-fx-background-color: forestgreen");
            }
            change1.setText(Double.toString(change));
        }
    }
    public static void error(ActionEvent ae) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("This feature has not been implicated yet");
        alert.showAndWait();
    }
}
