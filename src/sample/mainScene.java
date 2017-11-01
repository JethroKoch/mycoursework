package sample;

import Models.Product;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class mainScene extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        BorderPane root = new BorderPane();
        ObservableList<Product> purchaseTable = FXCollections.observableArrayList();

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
        historyButton.setOnAction((ActionEvent ae) -> error(ae));
        topPane.getChildren().add(historyButton);

        Button adjustStockButton = new Button("Adjust Stock");
        adjustStockButton.getStyleClass().add("normalButton");
        adjustStockButton.setPrefSize(Integer.MAX_VALUE, 20);
        adjustStockButton.setOnAction((ActionEvent ae) -> error(ae));
        topPane.getChildren().add(adjustStockButton);

        Button newStockButton = new Button("Add New Item");
        newStockButton.getStyleClass().add("normalButton");
        newStockButton.setPrefSize(Integer.MAX_VALUE, 20);
        newStockButton.setOnAction((ActionEvent ae) -> error(ae));
        topPane.getChildren().add(newStockButton);

        Button refundButton = new Button("Refund");
        refundButton.getStyleClass().add("normalButton");
        refundButton.setPrefSize(Integer.MAX_VALUE, 20);
        refundButton.setOnAction((ActionEvent ae) -> error(ae));
        topPane.getChildren().add(refundButton);

        VBox centerPane = new VBox(20);
        centerPane.setStyle("-fx-background-color: navy;");
        centerPane.setPadding(new Insets(0, 60, 0, 60));
        root.setCenter(centerPane);
        centerPane.setAlignment(Pos.CENTER);
        BorderPane.setAlignment(centerPane, Pos.CENTER);


        TableView table = new TableView<>();
        table.setPrefSize(400, 400);
        table.setItems(purchaseTable);
        TableColumn productIDColumn = new TableColumn<>("ProductID");
        productIDColumn.setCellValueFactory(new PropertyValueFactory<>("productID"));
        productIDColumn.setPrefWidth(180.8);
        table.getColumns().add(productIDColumn);
        TableColumn productDescriptionColumn = new TableColumn<>("Product Description");
        productDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("productDescription"));
        productDescriptionColumn.setPrefWidth(452);
        table.getColumns().add(productDescriptionColumn);
        TableColumn inStockColumn = new TableColumn<>("InStock");
        inStockColumn.setCellValueFactory(new PropertyValueFactory<>("inStock"));
        inStockColumn.setPrefWidth(90.4);
        table.getColumns().add(inStockColumn);
        TableColumn quantityColumn = new TableColumn<>("Quantity");
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        quantityColumn.setPrefWidth(90.4);
        table.getColumns().add(quantityColumn);
        TableColumn priceColumn = new TableColumn<>("Price");
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        priceColumn.setPrefWidth(90.4);
        table.getColumns().add(priceColumn);
        centerPane.getChildren().add(table);

        HBox bottomPane = new HBox(20);
        bottomPane.setStyle("-fx-background-color: navy;");
        bottomPane.setPadding(new Insets(20));
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
        editCustomerInfo.setOnAction((ActionEvent ae) -> error(ae));
        customerPane.add(editCustomerInfo, 1, 0);

        Label customerID = new Label("Customer ID");
        customerID.setPrefWidth(Integer.MAX_VALUE);
        customerPane.add(customerID, 0, 1);

        Label customerID1 = new Label("CustomerIDInput");
        customerID1.setPrefWidth(Integer.MAX_VALUE);
        customerPane.add(customerID1, 1, 1);

        Label name = new Label("customerName");
        name.setPrefWidth(Integer.MAX_VALUE);
        customerPane.add(name, 0, 2);

        Label name1 = new Label("customerNameInput");
        customerInformation.setPrefWidth(Integer.MAX_VALUE);
        customerPane.add(name1, 1, 2);

        Label age = new Label("Age");
        age.setPrefWidth(Integer.MAX_VALUE);
        customerPane.add(age, 0, 3);

        Label age1 = new Label("customerAgeInput");
        age1.setPrefWidth(Integer.MAX_VALUE);
        customerPane.add(age1, 1, 3);


        Button processButton = new Button("PROCESS");
        processButton.setMinSize(300, 100);
        processButton.setOnAction((ActionEvent ae) -> error(ae));
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

        Label totalCost1 = new Label("£ input");
        totalCost1.setPrefWidth(Integer.MAX_VALUE);
        costPane.add(totalCost1, 1, 0);

        Label amountGiven = new Label("Amount Given");
        amountGiven.setPrefWidth(Integer.MAX_VALUE);
        costPane.add(amountGiven, 0, 1);

        Label amountGiven1 = new Label("£input");
        amountGiven1.setPrefWidth(Integer.MAX_VALUE);
        costPane.add(amountGiven1, 1, 1);

        Label change = new Label("change");
        change.setPrefWidth(Integer.MAX_VALUE);
        costPane.add(change, 0, 2);

        Label change1 = new Label("£input");
        change1.setPrefWidth(Integer.MAX_VALUE);
        costPane.add(change1, 1, 2);

        Scene scene = new Scene(root, 1024, 768);
        scene.getStylesheets().add("Stylesheet.css");
        stage.setTitle("mainScene Window");
        stage.setScene(scene);
        stage.show();

    }

    public static void error(ActionEvent ae) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("This feature has not been implicated yet");
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}