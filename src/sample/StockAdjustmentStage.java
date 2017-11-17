package sample;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class StockAdjustmentStage {
    Scene searchProduct, editProduct, newProduct;
    static Pane parent;

    public StockAdjustmentStage(Pane theParent) {

        Stage stage = new Stage();
        parent = theParent;
        parent.setDisable(true);
        start(stage);

    }

    public void start(Stage stage) {

        stage.setTitle("Adjust Stock");
        stage.setOnCloseRequest((WindowEvent we) -> closeStage(stage));
        stage.show();
        searchPane(stage);

    }
    private void searchPane(Stage stage){
        VBox root = new VBox(0);
        searchProduct = new Scene(root,1024,768);
        stage.setScene(searchProduct);

        HBox navigationPane = new HBox(20);
        navigationPane.setStyle("-fx-background-color: #c2c2c2");
        navigationPane.setPadding(new Insets(20,60,20,60));
        navigationPane.setAlignment(Pos.CENTER);
        HBox.setHgrow(navigationPane, Priority.ALWAYS);
        root.getChildren().add(navigationPane);

        Button searchProductButton = new Button("Search Product");
        searchProductButton.setPrefSize(Integer.MAX_VALUE, 40);
        navigationPane.getChildren().add(searchProductButton);

        Button editProductButton = new Button("Edit Product");
        editProductButton.setPrefSize(Integer.MAX_VALUE, 40);
        editProductButton.setOnAction((ActionEvent ae)->openEditProduct(ae, stage));
        navigationPane.getChildren().add(editProductButton);

        Button newProductButton = new Button("New Product");
        newProductButton.setPrefSize(Integer.MAX_VALUE, 40);
        newProductButton.setOnAction((ActionEvent ae)->openNewProduct(ae,stage));
        navigationPane.getChildren().add(newProductButton);

        HBox searchBar = new HBox(20);
        searchBar.setStyle("-fx-background-color: #c2c2c2");
        searchBar.setPadding(new Insets(20,60,20,60));
        searchBar.setAlignment(Pos.CENTER);
        HBox.setHgrow(searchBar,Priority.ALWAYS);
        root.getChildren().add(searchBar);

        TextField itemDescription = new TextField();
        itemDescription.setPromptText("Item Description");
        itemDescription.setPrefSize(Integer.MAX_VALUE,20);

        Button searchNow = new Button("Search product");
        searchNow.setPrefSize(Integer.MAX_VALUE,20);
        searchNow.setStyle("-fx-background-color: #f7cecc");
        searchNow.setOnAction((ActionEvent ae)-> error(ae));
        searchBar.getChildren().addAll(itemDescription,searchNow);

    }
    private void editPane(Stage stage){
        VBox root = new VBox(20);
        editProduct = new Scene(root,1024,768);
        stage.setScene(editProduct);

        HBox navigationPane = new HBox(20);
        navigationPane.setStyle("-fx-background-color: #c2c2c2");
        navigationPane.setPadding(new Insets(20,20,0,20));
        navigationPane.setAlignment(Pos.CENTER);
        BorderPane.setAlignment(navigationPane, Pos.TOP_CENTER);
        HBox.setHgrow(navigationPane, Priority.ALWAYS);
        root.getChildren().add(navigationPane);

        Button searchProductButton = new Button("Search Product");
        searchProductButton.setPrefSize(Integer.MAX_VALUE, 40);
        searchProductButton.setOnAction((ActionEvent ae)->openSearchProduct(ae,stage));
        navigationPane.getChildren().add(searchProductButton);

        Button editProductButton = new Button("Edit Product");
        editProductButton.setPrefSize(Integer.MAX_VALUE, 40);
        navigationPane.getChildren().add(editProductButton);

        Button newProductButton = new Button("New Product");
        newProductButton.setPrefSize(Integer.MAX_VALUE, 40);
        newProductButton.setOnAction((ActionEvent ae)->openNewProduct(ae,stage));
        navigationPane.getChildren().add(newProductButton);

    }
    private void newPane(Stage stage){
        VBox root = new VBox(20);
        newProduct = new Scene(root,1024,768);
        stage.setScene(newProduct);

        HBox navigationPane = new HBox(20);
        navigationPane.setStyle("-fx-background-color: #c2c2c2");
        navigationPane.setPadding(new Insets(20,20,0,20));
        navigationPane.setAlignment(Pos.CENTER);
        BorderPane.setAlignment(navigationPane, Pos.TOP_CENTER);
        HBox.setHgrow(navigationPane, Priority.ALWAYS);
        root.getChildren().add(navigationPane);

        Button searchProductButton = new Button("Search Product");
        searchProductButton.setPrefSize(Integer.MAX_VALUE, 40);
        searchProductButton.setOnAction((ActionEvent ae)->openSearchProduct(ae,stage));
        navigationPane.getChildren().add(searchProductButton);

        Button editProductButton = new Button("Edit Product");
        editProductButton.setPrefSize(Integer.MAX_VALUE, 40);
        editProductButton.setOnAction((ActionEvent ae)->openEditProduct(ae, stage));
        navigationPane.getChildren().add(editProductButton);

        Button newProductButton = new Button("New Product");
        newProductButton.setPrefSize(Integer.MAX_VALUE, 40);
        navigationPane.getChildren().add(newProductButton);

    }
    private void openSearchProduct(ActionEvent ae, Stage stage){ searchPane(stage); }
    private void openNewProduct(ActionEvent ae, Stage stage) {
        newPane(stage);
    }
    private void openEditProduct(ActionEvent ae, Stage stage) {
        editPane(stage);
    }
    public static void error(ActionEvent ae) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("This feature has not been implicated yet");
        alert.showAndWait();
    }
    public void closeStage(Stage stage) {

        parent.setDisable(false);
        stage.close();

    }

}