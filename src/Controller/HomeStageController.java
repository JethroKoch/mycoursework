package Controller;

import Models.DatabaseConnection;
import Models.ProductService;
import Models.ProductView;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;

import java.util.ArrayList;

public class HomeStageController {

    private TableView<ProductView> productsTable;

    private DatabaseConnection database;
    private ArrayList<ProductView> currentProduct = new ArrayList<>();

    public HomeStageController(TableView<ProductView>productsTable){
        this.productsTable=productsTable;
        database = new DatabaseConnection("Database.db");
    }

    public void addProduct(ActionEvent ae,int productID){
        currentProduct.clear();
        ProductService.selectById(productID,database);

        productsTable.setItems(FXCollections.observableArrayList(currentProduct));
    }

    public static void error(ActionEvent ae) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("This feature has not been implicated yet");
        alert.showAndWait();
    }
}
