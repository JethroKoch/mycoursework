package Controller;

import Models.ProductModel;
import Models.ProductService;
import View.HomeStage;
import View.StockAdjustmentStage;
import javafx.collections.FXCollections;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class StockAdjustmentStageController {
    private static ArrayList<ProductModel> currentItem = new ArrayList<>();
    private int productID;
    public StockAdjustmentStageController(){}
    public void openSearchProduct(Stage stage){ StockAdjustmentStage.searchPane(stage); }
    public void openNewProduct(Stage stage) {
        StockAdjustmentStage.newPane(stage);
    }

    public void openEditProduct(Stage stage){
        ProductModel selectedItem = StockAdjustmentStage.stockTable.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            productID = selectedItem.getProductID();
            StockAdjustmentStage.stockTable.getItems().clear();
            currentItem.clear();
            StockAdjustmentStage.editPane(stage);
        }else {
            HomeStageController.genericError("No product has been selected for editing");
        }
    }
    public void getItemsForEdit(TextField productId, TextField productDescription, TextField inStock,TextField price){
        ProductModel product= ProductService.selectById(productID,HomeStage.database);
        productId.setText(Integer.toString(product.getProductID()));
        productDescription.setText(product.getProductDescription());
        inStock.setText(Integer.toString(product.getInStock()));
        price.setText(Double.toString(product.getPrice()));
    }

    public void loadResults(TextField productDescription){
        if (productDescription.getText().isEmpty()){
            HomeStageController.genericError("No search criteria entered");
        }else {
            currentItem.clear();
            String product = "%" + productDescription.getText() + "%";
            ArrayList<ProductModel> target = new ArrayList<>();
            currentItem.addAll(ProductService.selectByDescriptionList(target, product, HomeStage.database));
            StockAdjustmentStage.stockTable.setItems(FXCollections.observableArrayList(currentItem));
        }
    }

    public void deleteItem(TextField productId,Stage stage){
        int id = Integer.parseInt(productId.getText());
        ProductService.deleteById(id,HomeStage.database);
        openSearchProduct(stage);
    }
    public void saveProduct(TextField productId,TextField productDescription, TextField inStock, TextField price,Stage stage) {
        if (productId.getText().isEmpty() || productDescription.getText().isEmpty() || inStock.getText().isEmpty() || price.getText().isEmpty()) {
            HomeStageController.genericError("Fields are empty");
            //if any fields are empty then a message saying so pops up
        } else {
            int stock;
            double cost;
            try {
                //it try to save the program
                try {
                    stock = Integer.parseInt(inStock.getText());
                    cost = Double.parseDouble(price.getText());
                    ProductModel product = new ProductModel(Integer.parseInt(productId.getText()), productDescription.getText(), stock, cost);
                    ProductService.save(product, HomeStage.database);
                    openSearchProduct(stage);
                } catch (NumberFormatException nfe) {
                    HomeStageController.genericError("Invalid data in price");
                    //if the price is not an integer a error pop up says so
                }
            } catch (NumberFormatException nfe) {
                HomeStageController.genericError("invalid data in InStock");
                //If in stock is not a double an error message pops up and says so
            }
        }
    }
    public void closeStage(Pane parent, Stage stage) {

        parent.setDisable(false);
        stage.close();
    }

}
