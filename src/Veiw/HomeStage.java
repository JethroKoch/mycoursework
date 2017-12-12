package Veiw;

import Controller.HomeStageController;
import Models.DatabaseConnection;
import Models.ProductView;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import javax.swing.*;
import java.util.Observable;

public class HomeStage extends Application {
    public static Models.DatabaseConnection database;
    public static TableView<ProductView> productsTable = new TableView<>();
    private static HomeStageController controller;
    public static Label customerID1 = new Label("");
    public  static Label name1 = new Label("");
    public static Label age1 = new Label("");

    @Override
    public void start(Stage stage) throws Exception {

        database = new DatabaseConnection("Database.db");
        controller = new HomeStageController();

        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 1024, 768);
        scene.getStylesheets().add("Stylesheet.css");
        stage.setTitle("mainScene Window");
        stage.setScene(scene);
        stage.show();
        ObservableList<ProductView> purchaseTable = FXCollections.observableArrayList();

        HBox topPane = new HBox(60);
        topPane.setStyle("-fx-background-color: navy;");
        topPane.setPadding(new Insets(20, 60, 0, 60));
        HBox.setHgrow(topPane, Priority.ALWAYS);
        topPane.setAlignment(Pos.CENTER);
        BorderPane.setAlignment(topPane, Pos.TOP_CENTER);
        root.setTop(topPane);

        Button historyButton = new Button("History");
        historyButton.getStyleClass().add("normalButton");
        historyButton.setPrefSize(Integer.MAX_VALUE, 20);
        historyButton.setOnAction((ActionEvent ae) -> controller.openHistoryStage(root));
        topPane.getChildren().add(historyButton);

        Button adjustStockButton = new Button("Adjust Stock");
        adjustStockButton.getStyleClass().add("normalButton");
        adjustStockButton.setPrefSize(Integer.MAX_VALUE, 20);
        adjustStockButton.setOnAction((ActionEvent ae) -> controller.openStockAdjustmentStage(root));
        topPane.getChildren().add(adjustStockButton);

        Button refundButton = new Button("Refund");
        refundButton.getStyleClass().add("normalButton");
        refundButton.setPrefSize(Integer.MAX_VALUE, 20);
        refundButton.setOnAction((ActionEvent ae) -> controller.openRefundStage(root));
        topPane.getChildren().add(refundButton);

        VBox centerPane = new VBox(20);
        centerPane.setStyle("-fx-background-color: navy;");
        centerPane.setPadding(new Insets(0, 60, 0, 60));
        root.setCenter(centerPane);
        centerPane.setAlignment(Pos.CENTER);
        BorderPane.setAlignment(centerPane, Pos.CENTER);

        HBox searchPane = new HBox(10);
        searchPane.setStyle("-fx-background-color: navy");
        centerPane.getChildren().add(searchPane);

        TextField searchBar = new TextField();
        searchBar.setPromptText("Search ProductID");
        searchBar.setPrefSize(Integer.MAX_VALUE, 20);
        searchPane.getChildren().add(searchBar);

        Button searchButton = new Button("Add Product");
        searchButton.setPrefHeight(20);
        searchButton.setMinWidth(100);
        searchPane.getChildren().add(searchButton);

        Button removeButton = new Button("Remove Product");
        removeButton.setPrefHeight(20);
        removeButton.setMinWidth(150);
        searchPane.getChildren().add(removeButton);

        productsTable.setPrefSize(400, 400);
        productsTable.setItems(purchaseTable);

        TableColumn productIDColumn = new TableColumn<>("ProductID");
        productIDColumn.setCellValueFactory(new PropertyValueFactory<>("productID"));
        productIDColumn.setPrefWidth(180.8);
        productsTable.getColumns().add(productIDColumn);

        TableColumn productDescriptionColumn = new TableColumn<>("Product Description");
        productDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("productDescription"));
        productDescriptionColumn.setPrefWidth(452+90.4);
        productsTable.getColumns().add(productDescriptionColumn);

        TableColumn inStockColumn = new TableColumn<>("InStock");
        inStockColumn.setCellValueFactory(new PropertyValueFactory<>("inStock"));
        inStockColumn.setPrefWidth(90.4);
        productsTable.getColumns().add(inStockColumn);


        TableColumn priceColumn = new TableColumn<>("Price");
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        priceColumn.setPrefWidth(90.4);
        productsTable.getColumns().add(priceColumn);
        centerPane.getChildren().add(productsTable);

        HBox bottomPane = new HBox(20);
        bottomPane.setStyle("-fx-background-color: navy;");
        bottomPane.setPadding(new Insets(0));
        root.setBottom(bottomPane);
        bottomPane.setAlignment(Pos.BASELINE_CENTER);
        BorderPane.setAlignment(bottomPane, Pos.BOTTOM_CENTER);

        GridPane customerPane = new GridPane();
        customerPane.setHgap(10);
        customerPane.setVgap(10);
        customerPane.setPadding(new Insets(10));
        customerPane.setStyle("-fx-background-color: forestgreen");
        bottomPane.getChildren().add(customerPane);
        customerPane.setAlignment(Pos.BOTTOM_LEFT);

        Label customerInformation = new Label("Customer Information");
        customerInformation.setPrefWidth(Integer.MAX_VALUE);
        customerPane.add(customerInformation, 0, 0);

        Button editCustomerInfo = new Button("Edit/New");
        editCustomerInfo.getStyleClass().add("normalButton");
        editCustomerInfo.setPrefSize(Integer.MAX_VALUE, 20);
        editCustomerInfo.setOnAction((ActionEvent ae) -> controller.openCustomerStage(root));
        customerPane.add(editCustomerInfo, 1, 0);

        Label customerID = new Label("Customer ID");
        customerID.setPrefWidth(Integer.MAX_VALUE);
        customerPane.add(customerID, 0, 1);

        customerID1.setPrefWidth(Integer.MAX_VALUE);
        customerPane.add(customerID1, 1, 1);

        Label name = new Label("customerName");
        name.setPrefWidth(Integer.MAX_VALUE);
        customerPane.add(name, 0, 2);


        customerInformation.setPrefWidth(Integer.MAX_VALUE);
        customerPane.add(name1, 1, 2);

        Label age = new Label("Age");
        age.setPrefWidth(Integer.MAX_VALUE);
        customerPane.add(age, 0, 3);


        age1.setPrefWidth(Integer.MAX_VALUE);
        customerPane.add(age1, 1, 3);


        Button processButton = new Button("PROCESS");
        processButton.setMinSize(300, 100);
        processButton.setOnAction((ActionEvent ae) -> controller.error(ae));
        bottomPane.getChildren().add(processButton);
        bottomPane.setAlignment(Pos.CENTER);

        GridPane costPane = new GridPane();
        costPane.setHgap(10);
        costPane.setVgap(10);
        costPane.setPadding(new Insets(10));
        costPane.setStyle("-fx-background-color: forestgreen");
        bottomPane.getChildren().add(costPane);
        costPane.setAlignment(Pos.CENTER_RIGHT);

        Label totalCost = new Label("Total Cost");
        totalCost.setPrefWidth(Integer.MAX_VALUE);
        costPane.add(totalCost, 0, 0);

        Label totalCost1 = new Label();
        totalCost1.setPrefWidth(Integer.MAX_VALUE);
        costPane.add(totalCost1, 1, 0);

        Label amountGiven = new Label("Amount Given");
        amountGiven.setPrefWidth(Integer.MAX_VALUE);
        costPane.add(amountGiven, 0, 1);

        TextField amountGiven1 = new TextField();
        amountGiven1.setText("0.0");
        amountGiven1.setPrefWidth(Integer.MAX_VALUE);
        costPane.add(amountGiven1, 1, 1);

        Label change = new Label("change");
        change.setPrefWidth(Integer.MAX_VALUE);
        costPane.add(change, 0, 2);

        Label change1 = new Label();
        change1.setPrefWidth(Integer.MAX_VALUE);
        costPane.add(change1, 1, 2);

        searchButton.setOnAction((ae) -> controller.addProduct(searchBar,totalCost1,amountGiven1,change1));
        removeButton.setOnAction((ActionEvent ae)->controller.removeItem(change1,totalCost1,amountGiven1));
        amountGiven1.setOnKeyPressed(event -> { if (event.getCode() == KeyCode.ENTER){controller.updateCost(amountGiven1, change1);}});
    }

    public static void main(String[] args) {
        launch(args);
    }

}