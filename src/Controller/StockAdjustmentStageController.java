package Controller;

import Models.ProductService;
import Models.ProductView;
import Veiw.HomeStage;
import Veiw.StockAdjustmentStage;
import javafx.collections.FXCollections;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class StockAdjustmentStageController {
    private static ArrayList<ProductView> currentItem = new ArrayList<>();
    private int productID;
    public StockAdjustmentStageController(){}
    public void openSearchProduct(Stage stage){ StockAdjustmentStage.searchPane(stage); }
    public void openNewProduct(Stage stage) {
        StockAdjustmentStage.newPane(stage);
    }
    public void openEditProduct(Stage stage){
        ProductView selectedItem = StockAdjustmentStage.stockTable.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            productID = selectedItem.getProductID();
            StockAdjustmentStage.stockTable.getItems().clear();
            currentItem.clear();
            StockAdjustmentStage.editPane(stage);
        }else {
            Alert noProductSelected = new Alert(Alert.AlertType.INFORMATION);
            noProductSelected.setTitle("Error");
            noProductSelected.setHeaderText(null);
            noProductSelected.setContentText("No product has been selected for editing");
            noProductSelected.showAndWait();
        }
    }
    public void getItemsForEdit(TextField productId, TextField productDescription, TextField inStock,TextField price){
        ProductView product= ProductService.selectById(productID,HomeStage.database);
        productId.setText(Integer.toString(product.getProductID()));
        productDescription.setText(product.getProductDescription());
        inStock.setText(Integer.toString(product.getInStock()));
        price.setText(Double.toString(product.getPrice()));
    }

    public void loadResults(TextField productDescription){
        currentItem.clear();
        String product = "%"+productDescription.getText()+"%";
        ArrayList<ProductView> target = new ArrayList<>();
        currentItem.addAll(ProductService.selectByDescription(target, product, HomeStage.database));
        StockAdjustmentStage.stockTable.setItems(FXCollections.observableArrayList(currentItem));
    }

    public void deleteItem(TextField productId,Stage stage){
        int id = Integer.parseInt(productId.getText());
        ProductService.deleteById(id,HomeStage.database);
        openSearchProduct(stage);
    }
    public void saveProduct(TextField productId,TextField productDescription, TextField inStock, TextField price,Stage stage){
        ProductView product = new ProductView(Integer.parseInt(productId.getText()),productDescription.getText(),Integer.parseInt(inStock.getText()),Double.parseDouble(price.getText()));
        if (product.getProductID()!=0) {
            ProductService.save(product, HomeStage.database);
            openSearchProduct(stage);
        }else {
            Alert noProductID = new Alert(Alert.AlertType.INFORMATION);
            noProductID.setTitle("Error");
            noProductID.setHeaderText(null);
            noProductID.setContentText("No valid productID");
            noProductID.showAndWait();
        }
    }
    public void closeStage(Pane parent, Stage stage) {

        parent.setDisable(false);
        stage.close();
    }

}
