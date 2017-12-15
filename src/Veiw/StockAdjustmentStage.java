package Veiw;

import Controller.StockAdjustmentStageController;
import Models.ProductView;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class StockAdjustmentStage {
    static Pane parent;
    private static StockAdjustmentStageController controller;
    public static TableView<ProductView> stockTable = new TableView<>();

    public StockAdjustmentStage(Pane theParent) {

        Stage stage = new Stage();
        parent = theParent;
        parent.setDisable(true);
        start(stage);

    }

    public void start(Stage stage) {

        controller = new StockAdjustmentStageController();
        stage.setTitle("Adjust Stock");
        stage.setOnCloseRequest((WindowEvent we) -> controller.closeStage(parent,stage));
        stage.show();
        searchPane(stage);

    }
    public static void  searchPane(Stage stage){
        VBox root = new VBox(0);
        Scene searchProduct = new Scene(root,1024,768);
        stage.setScene(searchProduct);

        HBox navigationPane = new HBox(20);
        navigationPane.setStyle("-fx-background-color: #c2c2c2");
        navigationPane.setPadding(new Insets(30,60,30,60));
        navigationPane.setAlignment(Pos.CENTER);
        HBox.setHgrow(navigationPane, Priority.ALWAYS);
        root.getChildren().add(navigationPane);

        Button searchProductButton = new Button("Search Product");
        searchProductButton.setPrefSize(Integer.MAX_VALUE, 40);
        navigationPane.getChildren().add(searchProductButton);

        Button editProductButton = new Button("Edit Product");
        editProductButton.setPrefSize(Integer.MAX_VALUE, 40);
        editProductButton.setOnAction((ActionEvent ae)->controller.openEditProduct(ae, stage));
        navigationPane.getChildren().add(editProductButton);

        Button newProductButton = new Button("New Product");
        newProductButton.setPrefSize(Integer.MAX_VALUE, 40);
        newProductButton.setOnAction((ActionEvent ae)->controller.openNewProduct(ae,stage));
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
        searchNow.setOnAction((ActionEvent ae)-> controller.loadResults(itemDescription));
        searchBar.getChildren().addAll(itemDescription,searchNow);

        VBox listPane = new VBox(30);
        listPane.setStyle("-fx-background-color: #c2c2c2");
        listPane.setPadding(new Insets(20,60,20,60));
        root.getChildren().add(listPane);

        TableColumn productIDColumn = new TableColumn<>("ProductID");
        productIDColumn.setCellValueFactory(new PropertyValueFactory<>("productID"));
        productIDColumn.setPrefWidth(180.8);

        TableColumn productDescriptionColumn = new TableColumn<>("Product Description");
        productDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("productDescription"));
        productDescriptionColumn.setPrefWidth(452+90.4);

        TableColumn inStockColumn = new TableColumn<>("InStock");
        inStockColumn.setCellValueFactory(new PropertyValueFactory<>("inStock"));
        inStockColumn.setPrefWidth(90.4);;


        TableColumn priceColumn = new TableColumn<>("Price");
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        priceColumn.setPrefWidth(90.4);
        stockTable.getColumns().addAll(productIDColumn,productDescriptionColumn,inStockColumn,priceColumn);
        stockTable.setPrefHeight(Integer.MAX_VALUE);
        listPane.getChildren().add(stockTable);

        HBox bottomPane = new HBox(30);
        bottomPane.setStyle("-fx-background-color: #c2c2c2");
        bottomPane.setPadding(new Insets(20,20,20,20));
        bottomPane.setAlignment(Pos.CENTER);
        root.getChildren().add(bottomPane);

        Button select = new Button("Select");
        select.setPrefSize(100,20);
        select.setOnAction((ActionEvent ae)->controller.error(ae));
        bottomPane.getChildren().add(select);

    }
    public static void editPane(Stage stage){
        VBox root = new VBox(0);
        Scene editProduct = new Scene(root,1024,280);
        stage.setScene(editProduct);

        HBox navigationPane = new HBox(20);
        navigationPane.setStyle("-fx-background-color: #c2c2c2");
        navigationPane.setPadding(new Insets(30,60,30,60));
        navigationPane.setAlignment(Pos.CENTER);
        BorderPane.setAlignment(navigationPane, Pos.TOP_CENTER);
        HBox.setHgrow(navigationPane, Priority.ALWAYS);
        root.getChildren().add(navigationPane);

        Button searchProductButton = new Button("Search Product");
        searchProductButton.setPrefSize(Integer.MAX_VALUE, 40);
        searchProductButton.setOnAction((ActionEvent ae)->controller.openSearchProduct(ae,stage));
        navigationPane.getChildren().add(searchProductButton);

        Button editProductButton = new Button("Edit Product");
        editProductButton.setPrefSize(Integer.MAX_VALUE, 40);
        navigationPane.getChildren().add(editProductButton);

        Button newProductButton = new Button("New Product");
        newProductButton.setPrefSize(Integer.MAX_VALUE, 40);
        newProductButton.setOnAction((ActionEvent ae)->controller.openNewProduct(ae,stage));
        navigationPane.getChildren().add(newProductButton);

        HBox descriptions = new HBox(60);
        descriptions.setStyle("-fx-background-color: #c2c2c2");
        descriptions.setPadding(new Insets(30,60,0,60));
        descriptions.setAlignment(Pos.CENTER);
        HBox.setHgrow(descriptions,Priority.ALWAYS);
        root.getChildren().add(descriptions);

        Label productId = new Label("Product ID");
        productId.setPrefSize(Integer.MAX_VALUE,30);

        Label productDescription = new Label("ProductView Description");
        productDescription.setPrefSize(Integer.MAX_VALUE,30);

        Label inStock = new Label("In Stock");
        inStock.setPrefSize(Integer.MAX_VALUE,30);

        Label price = new Label("Price");
        price.setPrefSize(Integer.MAX_VALUE,30);
        descriptions.getChildren().addAll(productId,productDescription,inStock,price);

        HBox inputs = new HBox(60);
        inputs.setStyle("-fx-background-color: #c2c2c2");
        inputs.setPadding(new Insets(0,60,30,60));
        inputs.setAlignment(Pos.CENTER);
        HBox.setHgrow(inputs,Priority.ALWAYS);
        root.getChildren().add(inputs);

        TextField productIdInput = new TextField();
        productIdInput.setPrefSize(Integer.MAX_VALUE,30);
        productIdInput.setPromptText("ProductID...");

        TextField productDescriptionInput = new TextField();
        productDescriptionInput.setPrefSize(Integer.MAX_VALUE,30);
        productDescriptionInput.setPromptText("Product Description...");

        TextField inStockInput = new TextField();
        inStockInput.setPrefSize(Integer.MAX_VALUE,30);
        inStockInput.setPromptText("In Stock...");

        TextField priceInput = new TextField();
        priceInput.setPrefSize(Integer.MAX_VALUE,30);
        priceInput.setPromptText("Price...");
        inputs.getChildren().addAll(productIdInput,productDescriptionInput,inStockInput,priceInput);

        HBox buttons = new HBox(60);
        buttons.setStyle("-fx-background-color: #c2c2c2");
        buttons.setPadding(new Insets(30,60,30,60));
        buttons.setAlignment(Pos.CENTER);
        HBox.setHgrow(buttons,Priority.ALWAYS);
        root.getChildren().add(buttons);

        Button delete = new Button("Delete Item");
        delete.setStyle("-fx-background-color: #f7cecc");
        delete.setPrefSize(Integer.MAX_VALUE,30);
        delete.setOnAction((ActionEvent ae)->controller.error(ae));

        Button saveEdits = new Button("Update");
        saveEdits.setStyle("-fx-background-color: #f7cecc");
        saveEdits.setPrefSize(Integer.MAX_VALUE,30);
        saveEdits.setOnAction((ActionEvent ae)->controller.error(ae));
        buttons.getChildren().addAll(delete,saveEdits);
    }
    public static void newPane(Stage stage){
        VBox root = new VBox(0);
        Scene editProduct = new Scene(root,1024,280);
        stage.setScene(editProduct);

        HBox navigationPane = new HBox(20);
        navigationPane.setStyle("-fx-background-color: #c2c2c2");
        navigationPane.setPadding(new Insets(30,60,30,60));
        navigationPane.setAlignment(Pos.CENTER);
        BorderPane.setAlignment(navigationPane, Pos.TOP_CENTER);
        HBox.setHgrow(navigationPane, Priority.ALWAYS);
        root.getChildren().add(navigationPane);

        Button searchProductButton = new Button("Search Product");
        searchProductButton.setPrefSize(Integer.MAX_VALUE, 40);
        searchProductButton.setOnAction((ActionEvent ae)->controller.openSearchProduct(ae,stage));
        navigationPane.getChildren().add(searchProductButton);

        Button editProductButton = new Button("Edit Product");
        editProductButton.setPrefSize(Integer.MAX_VALUE, 40);
        editProductButton.setOnAction((ActionEvent ae)-> controller.openEditProduct(ae,stage));
        navigationPane.getChildren().add(editProductButton);

        Button newProductButton = new Button("New Product");
        newProductButton.setPrefSize(Integer.MAX_VALUE, 40);
        navigationPane.getChildren().add(newProductButton);

        HBox descriptions = new HBox(60);
        descriptions.setStyle("-fx-background-color: #c2c2c2");
        descriptions.setPadding(new Insets(30,60,0,60));
        descriptions.setAlignment(Pos.CENTER);
        HBox.setHgrow(descriptions,Priority.ALWAYS);
        root.getChildren().add(descriptions);

        Label productId = new Label("Product ID");
        productId.setPrefSize(Integer.MAX_VALUE,30);

        Label productDescription = new Label("Product Description");
        productDescription.setPrefSize(Integer.MAX_VALUE,30);

        Label inStock = new Label("In Stock");
        inStock.setPrefSize(Integer.MAX_VALUE,30);

        Label price = new Label("Price");
        price.setPrefSize(Integer.MAX_VALUE,30);
        descriptions.getChildren().addAll(productId,productDescription,inStock,price);

        HBox inputs = new HBox(60);
        inputs.setStyle("-fx-background-color: #c2c2c2");
        inputs.setPadding(new Insets(0,60,30,60));
        inputs.setAlignment(Pos.CENTER);
        HBox.setHgrow(inputs,Priority.ALWAYS);
        root.getChildren().add(inputs);

        TextField productIdInput = new TextField();
        productIdInput.setPrefSize(Integer.MAX_VALUE,30);
        productIdInput.setPromptText("ProductID...");

        TextField productDescriptionInput = new TextField();
        productDescriptionInput.setPrefSize(Integer.MAX_VALUE,30);
        productDescriptionInput.setPromptText("Product Description...");

        TextField inStockInput = new TextField();
        inStockInput.setPrefSize(Integer.MAX_VALUE,30);
        inStockInput.setPromptText("In Stock...");

        TextField priceInput = new TextField();
        priceInput.setPrefSize(Integer.MAX_VALUE,30);
        priceInput.setPromptText("Price...");
        inputs.getChildren().addAll(productIdInput,productDescriptionInput,inStockInput,priceInput);

        HBox buttons = new HBox(60);
        buttons.setStyle("-fx-background-color: #c2c2c2");
        buttons.setPadding(new Insets(30,60,30,60));
        buttons.setAlignment(Pos.CENTER);
        HBox.setHgrow(buttons,Priority.ALWAYS);
        root.getChildren().add(buttons);

        Button delete = new Button("Delete Item");
        delete.setStyle("-fx-background-color: #f7cecc");
        delete.setPrefSize(Integer.MAX_VALUE,30);
        delete.setOnAction((ActionEvent ae)->controller.error(ae));

        Button save = new Button("Save New");
        save.setStyle("-fx-background-color: #f7cecc");
        save.setPrefSize(Integer.MAX_VALUE,30);
        save.setOnAction((ActionEvent ae)->controller.error(ae));
        buttons.getChildren().addAll(delete,save);

    }
}