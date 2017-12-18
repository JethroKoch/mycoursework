package Veiw;

import Controller.RefundStageController;
import Models.RefundView;
import Models.TransactionTableView;
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
    public static TableView<RefundView> RefundItems = new TableView<>();
    private static RefundStageController controller;
    public RefundStage(Pane theParent) {

        Stage stage = new Stage();
        parent = theParent;
        parent.setDisable(true);
        stage.setResizable(false);
        start(stage);

    }

    public void start(Stage stage) {

        controller = new RefundStageController();
        VBox root = new VBox();
        Scene scene = new Scene(root, 1024, 768);
        stage.setTitle("Refund A Transaction");
        stage.setScene(scene);
        stage.setOnCloseRequest((WindowEvent we) -> controller.closeStage(parent,stage));
        stage.show();

        HBox topPane = new HBox(20);
        topPane.setStyle("-fx-background-color: #c2c2c2");
        topPane.setPadding(new Insets(20));
        HBox.setHgrow(topPane, Priority.ALWAYS);
        topPane.setAlignment(Pos.CENTER);
        root.getChildren().addAll(topPane);

        TextField transactionIdInput = new TextField();
        transactionIdInput.setPrefSize(Integer.MAX_VALUE,30);
        transactionIdInput.setPromptText("Enter TransactionID...");

        Button loadResults = new Button("Load Results");
        loadResults.setPrefSize(Integer.MAX_VALUE,30);
        loadResults.setStyle("-fx-background-color: #f7cecc");
        topPane.getChildren().addAll(transactionIdInput,loadResults);

        VBox centrePane = new VBox(20);
        centrePane.setStyle("-fx-background-color: #c2c2c2;");
        centrePane.setPadding(new Insets(0, 60, 0, 60));
        root.getChildren().add(centrePane);

        RefundItems.setPrefSize(400, Integer.MAX_VALUE);
        TableColumn transactionID = new TableColumn<>("TransactionID");
        transactionID.setCellValueFactory(new PropertyValueFactory<>("TransactionId"));
        transactionID.setPrefWidth(185);
        RefundItems.getColumns().add(transactionID);
        TableColumn productDescription = new TableColumn<>("CustomerID");
        productDescription.setCellValueFactory(new PropertyValueFactory<>("CustomerId"));
        productDescription.setPrefWidth(185);
        RefundItems.getColumns().add(productDescription);
        TableColumn inStock = new TableColumn<>("ProductDescription");
        inStock.setCellValueFactory(new PropertyValueFactory<>("ProductDescription"));
        inStock.setPrefWidth(330);
        RefundItems.getColumns().add(inStock);
        TableColumn totalCost = new TableColumn<>("TotalCost");
        totalCost.setCellValueFactory(new PropertyValueFactory<>("TotalCost"));
        totalCost.setPrefWidth(102);
        RefundItems.getColumns().add(totalCost);
        TableColumn date = new TableColumn<>("Date");
        date.setCellValueFactory(new PropertyValueFactory<>("Date"));
        date.setPrefWidth(102);
        RefundItems.getColumns().add(date);
        centrePane.getChildren().add(RefundItems);

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

        Button refundItems = new Button("Refund Item");
        refundItems.setStyle("-fx-background-color: #f7cecc");
        refundItems.setPrefSize(Integer.MAX_VALUE,20);
        refundItems.setOnAction((ActionEvent ae)->controller.refundItemL(cost));
        bottomPane.getChildren().addAll(totalRefundCost, cost, refundItems);

        loadResults.setOnAction((ActionEvent ae)->controller.searchTransactions(transactionIdInput,cost));
    }

}