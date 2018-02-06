package View;
//In package/Folder view

import Controller.HistoryStageController;
import Models.TransactionModel;
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
//All necessary imports used to write program

public class HistoryStage {
    static Pane parent;
    private static HistoryStageController controller;
    //Links to the controller
    public static  TableView<TransactionModel> historyTable= new TableView<>();
    //Table view needed for stage
    public HistoryStage(Pane theParent) {

        Stage stage = new Stage();
        parent = theParent;
        parent.setDisable(true);
        stage.setResizable(false);
        start(stage);
        //Initializes stage

    }

    public void start(Stage stage) {
        controller = new HistoryStageController();
        //opens link to controller
        HBox root = new HBox();
        Scene scene = new Scene(root, 1024, 400);
        stage.setTitle("TransactionHistory");
        stage.setScene(scene);
        stage.setOnCloseRequest((WindowEvent we) -> controller.closeStage(parent,stage));
        stage.show();
        //starts up the stage with a scene

        VBox leftPane = new VBox(30);
        leftPane.setStyle("-fx-background-color: #c2c2c2");
        leftPane.setPadding(new Insets(20));
        root.getChildren().add(leftPane);
        //created left pane so put in search criteria

        Label customerID = new Label("CustomerID");
        customerID.setPrefSize(Integer.MAX_VALUE, 20);
        //created label with the text CustomerID

        TextField customerIdInput = new TextField();
        customerIdInput.setPromptText("CustomerID...");
        customerIdInput.setPrefSize(Integer.MAX_VALUE, 20);
        leftPane.getChildren().addAll(customerID,customerIdInput);
        //created and added textfield to take in search criteria

        Button searchNow = new Button("Search Now");
        searchNow.setStyle("-fx-background-color: #f7cecc");
        searchNow.setMinSize(100,20);
        searchNow.setOnAction((ActionEvent ae) ->controller.loadResults(customerIdInput));
        leftPane.getChildren().add(searchNow);
        //created search now button to load transactions matching criteria

        VBox rightPane = new VBox(30);
        rightPane.setStyle("-fx-background-color: #c2c2c2");
        leftPane.setPadding(new Insets(20));
        root.getChildren().add(rightPane);
        //Initialized right pane for results table

        TableColumn transactionID = new TableColumn<>("TransactionID");
        transactionID.setCellValueFactory(new PropertyValueFactory<>("TransactionID"));
        transactionID.setPrefWidth(125);
        //created column in results table with title TransactionID

        TableColumn customerId = new TableColumn<>("CustomerID");
        customerId.setCellValueFactory(new PropertyValueFactory<>("CustomerID"));
        customerId.setPrefWidth(125);
        //created column in results table with title CustomerID

        TableColumn totalCost = new TableColumn<>("Total");
        totalCost.setCellValueFactory(new PropertyValueFactory<>("TotalCost"));
        totalCost.setPrefWidth(55.5);
        //created column in results table with title TotalCost

        TableColumn amountGiven = new TableColumn<>("Paid");
        amountGiven.setCellValueFactory(new PropertyValueFactory<>("AmountPaid"));
        amountGiven.setPrefWidth(55.5);
        //created column in results table with title amountPaid

        TableColumn change = new TableColumn<>("Change");
        change.setCellValueFactory(new PropertyValueFactory<>("Change"));
        change.setPrefWidth(55.5);
        //created column in results table with title Change

        TableColumn date = new TableColumn<>("Date");
        date.setCellValueFactory(new PropertyValueFactory<>("Date"));
        date.setPrefWidth(Integer.MAX_VALUE);
        //created column in results table with title TransactionID
        historyTable.setPrefSize(Integer.MAX_VALUE, Integer.MAX_VALUE);
        historyTable.getColumns().addAll(transactionID,customerId,totalCost,amountGiven,change,date);
        //Added all the columns to the table
        rightPane.getChildren().add(historyTable);
        //Adds the table to the scene

    }
}