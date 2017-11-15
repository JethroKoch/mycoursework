package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class CustomerStage {
    Scene searchCustomer, editCustomer, newCustomer;
    static Pane parent;

    public CustomerStage(Pane theParent) {
        Stage stage = new Stage();
        parent = theParent;
        parent.setDisable(true);
        start(stage);

    }

    public void start(Stage stage) {

        stage.setTitle("Customer Information");
        searchPane(stage);
    }
    private void searchPane(Stage stage){
        BorderPane search = new BorderPane();
        searchCustomer = new Scene(search, 1024, 768);
        stage.setScene(searchCustomer);
        stage.setOnCloseRequest((WindowEvent we) -> closeStage(stage));
        stage.show();

        HBox topPane = new HBox(20);
        topPane.setStyle("-fx-background-color: #c2c2c2");
        topPane.setPadding(new Insets(20,20,0,20));
        topPane.setAlignment(Pos.CENTER);
        BorderPane.setAlignment(topPane, Pos.TOP_CENTER);
        HBox.setHgrow(topPane, Priority.ALWAYS);
        search.setTop(topPane);

        Button searchCustomerButton = new Button("Search Customer");
        searchCustomerButton.setPrefSize(Integer.MAX_VALUE, 40);
        topPane.getChildren().add(searchCustomerButton);

        Button editCustomerButton = new Button("Edit Customer");
        editCustomerButton.setPrefSize(Integer.MAX_VALUE, 40);
        editCustomerButton.setOnAction((ActionEvent ae)->openCustomeredit(ae,stage));
        topPane.getChildren().add(editCustomerButton);

        Button newCustomerButton = new Button("New Customer");
        newCustomerButton.setPrefSize(Integer.MAX_VALUE, 40);
        newCustomerButton.setOnAction((ActionEvent ae)->openCustomerNew(ae,stage));
        topPane.getChildren().add(newCustomerButton);

        VBox leftPane = new VBox(40);
        leftPane.setStyle("-fx-background-color: #c2c2c2");
        leftPane.setPadding(new Insets(60,20,60,20));
        leftPane.setAlignment(Pos.CENTER);
        BorderPane.setAlignment(leftPane, Pos.CENTER_LEFT);
        search.setLeft(leftPane);

        Label firstName = new Label("First Name");
        firstName.setMinSize(100,20);
        leftPane.getChildren().add(firstName);

        TextField firstNameInput = new TextField("");
        firstNameInput.setPromptText("Enter First Name...");
        firstNameInput.setMinSize(100,20);
        leftPane.getChildren().add(firstNameInput);

        Label secondName = new Label("Second Name");
        secondName.setMinSize(100,20);
        leftPane.getChildren().add(secondName);

        TextField secondNameInput = new TextField("");
        secondNameInput.setPromptText("Enter Second Name...");
        secondNameInput.setMinSize(100,20);
        leftPane.getChildren().add(secondNameInput);

        Label postcode = new Label("Postcode");
        postcode.setMinSize(100,20);
        leftPane.getChildren().add(postcode);

        TextField postcodeInput = new TextField("");
        postcodeInput.setPromptText("Enter postcode...");
        postcodeInput.setMinSize(100,20);
        leftPane.getChildren().add(postcodeInput);

        Button searchCustomerNowButton = new Button("Search Now");
        searchCustomerNowButton.setStyle("-fx-background-color: #f7cecc");
        searchCustomerNowButton.setMinSize(100,20);
        searchCustomerNowButton.setOnAction((ActionEvent ae) ->error(ae));
        leftPane.getChildren().add(searchCustomerNowButton);

        ListView<String> results = new ListView<String>();
        ObservableList<String>customers = FXCollections.observableArrayList("No items Searched");
        results.setItems(customers);
        results.setPrefSize(Integer.MAX_VALUE, Integer.MAX_VALUE);
        search.setCenter(results);
    }

    private void newPane(Stage stage){
        BorderPane search = new BorderPane();
        searchCustomer = new Scene(search, 1024, 768);
        stage.setScene(searchCustomer);
        stage.setOnCloseRequest((WindowEvent we) -> closeStage(stage));
        stage.show();

        VBox topPane = new VBox(20);
        topPane.setStyle("-fx-background-color: #c2c2c2");
        topPane.setPadding(new Insets(20,0,0,0));
        topPane.setAlignment(Pos.CENTER);
        BorderPane.setAlignment(topPane, Pos.TOP_CENTER);
        HBox.setHgrow(topPane, Priority.ALWAYS);
        search.setTop(topPane);

        HBox tabButtons = new HBox(20);
        tabButtons.setStyle("-fx-background-color: #c2c2c2");
        tabButtons.setPadding(new Insets(0,20,0,20));
        topPane.getChildren().add(tabButtons);

        Button searchCustomerButton = new Button("Search Customer");
        searchCustomerButton.setPrefSize(Integer.MAX_VALUE, 40);
        searchCustomerButton.setOnAction((ActionEvent ae)-> openCustomerSearch(ae, stage));

        Button editCustomerButton = new Button("Edit Customer");
        editCustomerButton.setPrefSize(Integer.MAX_VALUE, 40);
        editCustomerButton.setOnAction((ActionEvent ae)->openCustomeredit(ae,stage));

        Button newCustomerButton = new Button("New Customer");
        newCustomerButton.setPrefSize(Integer.MAX_VALUE, 40);
        tabButtons.getChildren().addAll(searchCustomerButton, editCustomerButton, newCustomerButton);

        HBox labels = new HBox();
        labels.setStyle("-fx-background-color: #c2c2c2");
        labels.setPadding(new Insets(0,20,0,20));
        topPane.getChildren().add(labels);

        Label firstName = new Label("First Name");
        firstName.setPrefSize(Integer.MAX_VALUE, 20);

        Label secondName = new Label("Second Name");
        secondName.setPrefSize(Integer.MAX_VALUE, 20);

        Label datOfBirth = new Label("Date of Birth");
        datOfBirth.setPrefSize(Integer.MAX_VALUE, 20);
        labels.getChildren().addAll(firstName, secondName, datOfBirth);

        HBox textFields = new HBox();
        textFields.setStyle("-fx-background-color: #c2c2c2");
        textFields.setPadding(new Insets(0,20,0,20));
        topPane.getChildren().add(textFields);

        TextField firstNameInput = new TextField();
        firstNameInput.setPrefSize(Integer.MAX_VALUE, 20);

        TextField secondNameInput = new TextField();
        secondNameInput.setPrefSize(Integer.MAX_VALUE, 20);

        TextField datOfBirthInput = new TextField();
        datOfBirthInput.setPrefSize(Integer.MAX_VALUE, 20);
        textFields.getChildren().addAll(firstNameInput, secondNameInput, datOfBirthInput);


    }
    private void editPane(Stage stage){
        BorderPane search = new BorderPane();
        searchCustomer = new Scene(search, 1024, 768);
        stage.setScene(searchCustomer);
        stage.setOnCloseRequest((WindowEvent we) -> closeStage(stage));
        stage.show();
        HBox topPane = new HBox(20);
        topPane.setStyle("-fx-background-color: #c2c2c2");
        topPane.setPadding(new Insets(20,20,0,20));
        topPane.setAlignment(Pos.CENTER);
        BorderPane.setAlignment(topPane, Pos.TOP_CENTER);
        HBox.setHgrow(topPane, Priority.ALWAYS);
        search.setTop(topPane);
        Button searchCustomerButton = new Button("Search Customer");
        searchCustomerButton.setPrefSize(Integer.MAX_VALUE, 40);
        searchCustomerButton.setOnAction((ActionEvent ae)-> openCustomerSearch(ae, stage));
        topPane.getChildren().add(searchCustomerButton);
        Button editCustomerButton = new Button("Edit Customer");
        editCustomerButton.setPrefSize(Integer.MAX_VALUE, 40);
        topPane.getChildren().add(editCustomerButton);
        Button newCustomerButton = new Button("New Customer");
        newCustomerButton.setPrefSize(Integer.MAX_VALUE, 40);
        newCustomerButton.setOnAction((ActionEvent ae)->openCustomerNew(ae,stage));
        topPane.getChildren().add(newCustomerButton);
    }

    private void openCustomerSearch(ActionEvent ae, Stage stage){
        searchPane(stage);
    }
    private void openCustomerNew(ActionEvent ae, Stage stage) {
        newPane(stage);
    }

    private void openCustomeredit(ActionEvent ae, Stage stage) {
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