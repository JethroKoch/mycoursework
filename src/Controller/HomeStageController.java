package Controller;

import Models.ProductService;
import Models.ProductView;
import Veiw.*;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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

    public static  double total;
    public static String outputString  = Double.toString(total);

    public void addProduct(TextField searchBar){

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

        for(ProductView price:HomeStage.productsTable.getItems()){
            total += price.getPrice();
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
