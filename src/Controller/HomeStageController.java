package Controller;

import Models.DatabaseConnection;
import Models.ProductService;
import Models.ProductView;
import Veiw.HomeStage;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class HomeStageController {

    private ArrayList<ProductView> currentProduct = new ArrayList<>();

    public HomeStageController() {}

    public void addProduct(TextField searchBar){

        System.out.println("Attempt to search for: " + searchBar.getText());

        int productID = 0;
        try {
            Integer.parseInt(searchBar.getText());
        }
        catch (NumberFormatException nfe) {
            System.out.println (nfe.getMessage());
        }

        currentProduct.clear();
        ProductService.selectById(productID, HomeStage.database);

        HomeStage.productsTable.setItems(FXCollections.observableArrayList(currentProduct));
    }

    public static void error(ActionEvent ae) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("This feature has not been implicated yet");
        alert.showAndWait();
    }
}
