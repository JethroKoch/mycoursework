package sample;

import Models.TransactionView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


public class RefundStage {
    static Pane parent;

    public RefundStage(Pane theParent) {

        Stage stage = new Stage();
        parent = theParent;
        parent.setDisable(true);
        start(stage);

    }

    public void start(Stage stage) {

        VBox root = new VBox();
        Scene scene = new Scene(root, 1024, 768);
        stage.setTitle("TransactionView A TransactionView");
        stage.setScene(scene);
        stage.setOnCloseRequest((WindowEvent we) -> closeStage(stage));
        stage.show();

        HBox topPane = new HBox(20);
        topPane.setStyle("-fx-background-color: #c2c2c2");
        topPane.setPadding(new Insets(20));
        HBox.setHgrow(topPane, Priority.ALWAYS);
        topPane.setAlignment(Pos.CENTER);
        root.getChildren().addAll(topPane);

        TextField productID = new TextField();
        productID.setPrefSize(Integer.MAX_VALUE,30);
        productID.setPromptText("Enter ProductID...");

        TextField customerID = new TextField();
        customerID.setPrefSize(Integer.MAX_VALUE,30);
        customerID.setPromptText("Enter CustomerID...");

        Button loadResults = new Button("Load Results");
        loadResults.setPrefSize(Integer.MAX_VALUE,30);
        loadResults.setStyle("-fx-background-color: #f7cecc");
        loadResults.setOnAction((ActionEvent ae)->error(ae));
        topPane.getChildren().addAll(productID,customerID,loadResults);

        VBox centrePane = new VBox(20);
        centrePane.setStyle("-fx-background-color: #c2c2c2;");
        centrePane.setPadding(new Insets(0, 60, 0, 60));
        root.getChildren().add(centrePane);

        TableView table = new TableView<>();
        ObservableList<TransactionView>basket= FXCollections.observableArrayList();
        table.setPrefSize(400, Integer.MAX_VALUE);
        table.setItems(basket);
        TableColumn customerIDColumn = new TableColumn<>("CustomerID");
        customerIDColumn.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        customerIDColumn.setPrefWidth(90.4);
        table.getColumns().add(customerIDColumn);
        TableColumn productIDColumn = new TableColumn<>("ProductID");
        productIDColumn.setCellValueFactory(new PropertyValueFactory<>("productID"));
        productIDColumn.setPrefWidth(180.8);
        table.getColumns().add(productIDColumn);
        TableColumn productDescriptionColumn = new TableColumn<>("ProductView Description");
        productDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("productDescription"));
        productDescriptionColumn.setPrefWidth(452);
        table.getColumns().add(productDescriptionColumn);
        TableColumn inStockColumn = new TableColumn<>("InStock");
        inStockColumn.setCellValueFactory(new PropertyValueFactory<>("inStock"));
        inStockColumn.setPrefWidth(90.4);
        table.getColumns().add(inStockColumn);
        TableColumn priceColumn = new TableColumn<>("Price");
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        priceColumn.setPrefWidth(90.4);
        table.getColumns().add(priceColumn);
        centrePane.getChildren().add(table);

        HBox bottomPane = new HBox(40);
        bottomPane.setStyle("-fx-background-color: #c2c2c2");
        bottomPane.setPadding(new Insets(20));
        HBox.setHgrow(bottomPane, Priority.ALWAYS);
        bottomPane.setAlignment(Pos.CENTER);
        root.getChildren().add(bottomPane);

        Label totalCost = new Label("Total Cost");
        totalCost.setPrefSize(Integer.MAX_VALUE, 20);

        Label cost = new Label("£");
        cost.setStyle("-fx-background-color: #ffffff");
        cost.setMinWidth(100);
        totalCost.setPrefSize(Integer.MAX_VALUE,20);

        Button refundItems = new Button("TransactionView Items");
        refundItems.setStyle("-fx-background-color: #f7cecc");
        refundItems.setPrefSize(Integer.MAX_VALUE,20);
        refundItems.setOnAction((ActionEvent ae)->error(ae));
        bottomPane.getChildren().addAll(totalCost, cost, refundItems);
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