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
        stage.setTitle("Refund A Transaction");
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
        TableColumn transactionID = new TableColumn<>("TransactionID");
        transactionID.setCellValueFactory(new PropertyValueFactory<>("TransactionID"));
        transactionID.setPrefWidth(181);
        table.getColumns().add(transactionID);
        TableColumn totalCost = new TableColumn<>("Total Cost");
        totalCost.setCellValueFactory(new PropertyValueFactory<>("TotalCost"));
        totalCost.setPrefWidth(181);
        table.getColumns().add(totalCost);
        TableColumn amountPaid = new TableColumn<>("Amount Paid");
        amountPaid.setCellValueFactory(new PropertyValueFactory<>("Amount Paid"));
        amountPaid.setPrefWidth(180);
        table.getColumns().add(amountPaid);
        TableColumn changeGiven = new TableColumn<>("Change Given");
        changeGiven.setCellValueFactory(new PropertyValueFactory<>("ChangeGiven"));
        changeGiven.setPrefWidth(181);
        table.getColumns().add(changeGiven);
        TableColumn date = new TableColumn<>("Date");
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        date.setPrefWidth(181);
        table.getColumns().add(date);
        centrePane.getChildren().add(table);

        HBox bottomPane = new HBox(40);
        bottomPane.setStyle("-fx-background-color: #c2c2c2");
        bottomPane.setPadding(new Insets(20));
        HBox.setHgrow(bottomPane, Priority.ALWAYS);
        bottomPane.setAlignment(Pos.CENTER);
        root.getChildren().add(bottomPane);

        Label totalRefundCost = new Label("Total Cost");
        totalRefundCost.setPrefSize(Integer.MAX_VALUE, 20);

        Label cost = new Label("£");
        cost.setStyle("-fx-background-color: #ffffff");
        cost.setMinWidth(100);
        totalRefundCost.setPrefSize(Integer.MAX_VALUE,20);

        Button refundItems = new Button("Transaction Items");
        refundItems.setStyle("-fx-background-color: #f7cecc");
        refundItems.setPrefSize(Integer.MAX_VALUE,20);
        refundItems.setOnAction((ActionEvent ae)->error(ae));
        bottomPane.getChildren().addAll(totalRefundCost, cost, refundItems);
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