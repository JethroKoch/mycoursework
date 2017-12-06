package Veiw;

import Controller.MainController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


public class HistoryStage {
    static Pane parent;
    private static MainController controller;
    public HistoryStage(Pane theParent) {

        Stage stage = new Stage();
        parent = theParent;
        parent.setDisable(true);
        start(stage);

    }

    public void start(Stage stage) {

        HBox root = new HBox();
        Scene scene = new Scene(root, 1024, 400);
        stage.setTitle("TransactionHistory");
        stage.setScene(scene);
        stage.setOnCloseRequest((WindowEvent we) -> closeStage(stage));
        stage.show();

        VBox leftPane = new VBox(30);
        leftPane.setStyle("-fx-background-color: #c2c2c2");
        leftPane.setPadding(new Insets(20));
        root.getChildren().add(leftPane);

        Label transactionID = new Label("TransactionID");
        transactionID.setPrefSize(Integer.MAX_VALUE, 20);

        TextField transactionIDInput = new TextField();
        transactionIDInput.setPromptText("TransactionID...");
        transactionIDInput.setPrefSize(Integer.MAX_VALUE, 20);

        Label customerID = new Label("CustomerID");
        customerID.setPrefSize(Integer.MAX_VALUE, 20);

        TextField customerIDInput = new TextField();
        customerIDInput.setPromptText("CustomerID...");
        customerIDInput.setPrefSize(Integer.MAX_VALUE, 20);

        Label productID = new Label("ProductID");
        productID.setPrefSize(Integer.MAX_VALUE, 20);

        TextField productIDInput = new TextField();
        productIDInput.setPromptText("ProductID...");
        productIDInput.setPrefSize(Integer.MAX_VALUE, 20);
        leftPane.getChildren().addAll(transactionID,transactionIDInput,customerID,customerIDInput,productID,productIDInput);

        Button searchNow = new Button("Search Now");
        searchNow.setStyle("-fx-background-color: #f7cecc");
        searchNow.setMinSize(100,20);
        searchNow.setOnAction((ActionEvent ae) ->controller.error(ae));
        leftPane.getChildren().add(searchNow);

        VBox rightPane = new VBox(30);
        rightPane.setStyle("-fx-background-color: #c2c2c2");
        leftPane.setPadding(new Insets(20));
        root.getChildren().add(rightPane);
        ListView<String> results = new ListView<String>();
        results.setPrefSize(Integer.MAX_VALUE, Integer.MAX_VALUE);
        ObservableList<String> transactions = FXCollections.observableArrayList("No items Searched");
        results.setItems(transactions);
        rightPane.getChildren().add(results);


    }

    public void closeStage(Stage stage) {

        parent.setDisable(false);
        stage.close();

    }

}

