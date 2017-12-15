package Veiw;

import Controller.HistoryStageController;
import Models.TransactionTableView;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


public class HistoryStage {
    static Pane parent;
    private static HistoryStageController controller;
    public static  TableView<TransactionTableView> historyTable= new TableView<>();
    public HistoryStage(Pane theParent) {

        Stage stage = new Stage();
        parent = theParent;
        parent.setDisable(true);
        start(stage);

    }

    public void start(Stage stage) {
        controller = new HistoryStageController();
        HBox root = new HBox();
        Scene scene = new Scene(root, 1024, 400);
        stage.setTitle("TransactionHistory");
        stage.setScene(scene);
        stage.setOnCloseRequest((WindowEvent we) -> controller.closeStage(parent,stage));
        stage.show();

        VBox leftPane = new VBox(30);
        leftPane.setStyle("-fx-background-color: #c2c2c2");
        leftPane.setPadding(new Insets(20));
        root.getChildren().add(leftPane);

        Label customerID = new Label("CustomerID");
        customerID.setPrefSize(Integer.MAX_VALUE, 20);

        TextField customerIdInput = new TextField();
        customerIdInput.setPromptText("CustomerID...");
        customerIdInput.setPrefSize(Integer.MAX_VALUE, 20);
        leftPane.getChildren().addAll(customerID,customerIdInput);

        Button searchNow = new Button("Search Now");
        searchNow.setStyle("-fx-background-color: #f7cecc");
        searchNow.setMinSize(100,20);
        searchNow.setOnAction((ActionEvent ae) ->controller.loadResults(customerIdInput));
        leftPane.getChildren().add(searchNow);

        VBox rightPane = new VBox(30);
        rightPane.setStyle("-fx-background-color: #c2c2c2");
        leftPane.setPadding(new Insets(20));
        root.getChildren().add(rightPane);

        TableColumn transactionID = new TableColumn<>("TransactionID");
        transactionID.setCellValueFactory(new PropertyValueFactory<>("TransactionID"));
        transactionID.setPrefWidth(125);

        TableColumn customerId = new TableColumn<>("CustomerID");
        customerId.setCellValueFactory(new PropertyValueFactory<>("CustomerID"));
        customerId.setPrefWidth(125);

        TableColumn totalCost = new TableColumn<>("Total");
        totalCost.setCellValueFactory(new PropertyValueFactory<>("TotalCost"));
        totalCost.setPrefWidth(55.5);

        TableColumn amountGiven = new TableColumn<>("Paid");
        amountGiven.setCellValueFactory(new PropertyValueFactory<>("AmountPaid"));
        amountGiven.setPrefWidth(55.5);

        TableColumn change = new TableColumn<>("Change");
        change.setCellValueFactory(new PropertyValueFactory<>("Change"));
        change.setPrefWidth(55.5);

        TableColumn date = new TableColumn<>("Date");
        date.setCellValueFactory(new PropertyValueFactory<>("Date"));
        date.setPrefWidth(Integer.MAX_VALUE);
        historyTable.setPrefSize(Integer.MAX_VALUE, Integer.MAX_VALUE);
        historyTable.getColumns().addAll(transactionID,customerId,totalCost,amountGiven,change,date);
        rightPane.getChildren().add(historyTable);


    }
}

