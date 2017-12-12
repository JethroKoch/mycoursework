package Veiw;

import Controller.CustomerStageController;
import Models.CustomerView;
import Models.CustomerViewSearch;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class CustomerStage {
    static Pane parent;
    private static CustomerStageController controller;
    public static ListView<CustomerViewSearch>customersList  = new ListView<>();
    public static ListView<CustomerView>customerForEdit = new ListView<>();

    public CustomerStage(Pane theParent) {
        Stage stage = new Stage();
        parent = theParent;
        parent.setDisable(true);
        start(stage);
        controller = new CustomerStageController();

    }

    public void start(Stage stage) {
        stage.setTitle("Customer Information");
        stage.setOnCloseRequest((WindowEvent we) -> controller.closeStage(parent, stage));
        stage.show();
        searchPane(stage);
    }

    public static void searchPane(Stage stage) {
        BorderPane search = new BorderPane();
        Scene searchCustomer = new  Scene(search, 1024, 768);
        stage.setScene(searchCustomer);

        HBox topPane = new HBox(20);
        topPane.setStyle("-fx-background-color: #c2c2c2");
        topPane.setPadding(new Insets(20, 20, 0, 20));
        topPane.setAlignment(Pos.CENTER);
        BorderPane.setAlignment(topPane, Pos.TOP_CENTER);
        HBox.setHgrow(topPane, Priority.ALWAYS);
        search.setTop(topPane);

        Button searchCustomerButton = new Button("Search Customer");
        searchCustomerButton.setPrefSize(Integer.MAX_VALUE, 40);
        topPane.getChildren().add(searchCustomerButton);

        Button newCustomerButton = new Button("New Customer");
        newCustomerButton.setPrefSize(Integer.MAX_VALUE, 40);
        newCustomerButton.setOnAction((ActionEvent ae) -> controller.openCustomerNew(ae, stage));
        topPane.getChildren().add(newCustomerButton);

        VBox leftPane = new VBox(40);
        leftPane.setStyle("-fx-background-color: #c2c2c2");
        leftPane.setPadding(new Insets(60, 20, 60, 20));
        leftPane.setAlignment(Pos.CENTER);
        BorderPane.setAlignment(leftPane, Pos.CENTER_LEFT);
        search.setLeft(leftPane);

        Label firstNameLabel = new Label("First Name");
        firstNameLabel.setMinSize(100, 20);
        leftPane.getChildren().add(firstNameLabel);

        TextField firstNameInput = new TextField("");
        firstNameInput.setPromptText("Enter First Name...");
        firstNameInput.setMinSize(100, 20);
        leftPane.getChildren().add(firstNameInput);

        Label secondNameLabel = new Label("Second Name");
        secondNameLabel.setMinSize(100, 20);
        leftPane.getChildren().add(secondNameLabel);

        TextField secondNameInput = new TextField("");
        secondNameInput.setPromptText("Enter Second Name...");
        secondNameInput.setMinSize(100, 20);
        leftPane.getChildren().add(secondNameInput);

        Label postcodeLabel = new Label("Postcode");
        postcodeLabel.setMinSize(100, 20);
        leftPane.getChildren().add(postcodeLabel);

        TextField postcodeInput = new TextField("");
        postcodeInput.setPromptText("Enter postcode...");
        postcodeInput.setMinSize(100, 20);
        leftPane.getChildren().add(postcodeInput);

        Button searchCustomerNowButton = new Button("Search Now");
        searchCustomerNowButton.setStyle("-fx-background-color: #f7cecc");
        searchCustomerNowButton.setMinSize(100, 20);
        searchCustomerNowButton.setOnAction((ActionEvent ae) -> controller.search(ae,firstNameInput,secondNameInput,postcodeInput));
        leftPane.getChildren().add(searchCustomerNowButton);

        Button selectButton = new Button("Select Customer");
        selectButton.setMinSize(100, 20);
        selectButton.setOnAction((ActionEvent ae) -> controller.selectCustomer(parent,stage));
        leftPane.getChildren().add(selectButton);

        Button editCustomer = new Button("Edit Customer");
        editCustomer.setMinSize(100,20);
        editCustomer.setOnAction((ActionEvent ae)-> controller.customerEdit(ae,stage));
        leftPane.getChildren().add(editCustomer);

        HBox centrePane = new HBox(40);
        centrePane.setStyle("-fx-background-color: #c2c2c2");
        centrePane.setPadding(new Insets(60, 20, 60, 20));
        centrePane.setAlignment(Pos.CENTER);
        search.setAlignment(centrePane, Pos.CENTER);
        search.setCenter(centrePane);

        customersList.setPrefSize(Integer.MAX_VALUE, Integer.MAX_VALUE);
        centrePane.getChildren().add(customersList);

    }

    public static void newPane(Stage stage) {
        BorderPane new1 = new BorderPane();
        Scene newCustomer = new Scene(new1, 1024, 768);
        stage.setScene(newCustomer);
        stage.setOnCloseRequest((WindowEvent we) -> controller.closeStage(parent,stage));
        stage.show();

        VBox topPane = new VBox(20);
        topPane.setStyle("-fx-background-color: #c2c2c2");
        topPane.setPadding(new Insets(20, 0, 0, 0));
        topPane.setAlignment(Pos.CENTER);
        BorderPane.setAlignment(topPane, Pos.TOP_CENTER);
        HBox.setHgrow(topPane, Priority.ALWAYS);
        new1.setTop(topPane);

        HBox tabButtons = new HBox(20);
        tabButtons.setStyle("-fx-background-color: #c2c2c2");
        tabButtons.setPadding(new Insets(0, 20, 0, 20));
        topPane.getChildren().add(tabButtons);

        Button searchCustomerButton = new Button("Search Customer");
        searchCustomerButton.setPrefSize(Integer.MAX_VALUE, 40);
        searchCustomerButton.setOnAction((ActionEvent ae) -> controller.openCustomerSearch(ae, stage));


        Button newCustomerButton = new Button("New Customer");
        newCustomerButton.setPrefSize(Integer.MAX_VALUE, 40);
        tabButtons.getChildren().addAll(searchCustomerButton, newCustomerButton);

        HBox labels = new HBox();
        labels.setStyle("-fx-background-color: #c2c2c2");
        labels.setPadding(new Insets(0, 20, 0, 20));
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
        textFields.setPadding(new Insets(0, 20, 0, 20));
        topPane.getChildren().add(textFields);

        TextField firstNameInput = new TextField();
        firstNameInput.setPrefSize(Integer.MAX_VALUE, 20);
        firstNameInput.setPromptText("First Name");

        TextField secondNameInput = new TextField();
        secondNameInput.setPrefSize(Integer.MAX_VALUE, 20);
        secondNameInput.setPromptText("Second Name");

        TextField datOfBirthInput = new TextField();
        datOfBirthInput.setPrefSize(Integer.MAX_VALUE, 20);
        datOfBirthInput.setPromptText("Date of Birth");
        textFields.getChildren().addAll(firstNameInput, secondNameInput, datOfBirthInput);

        VBox leftPane = new VBox(40);
        leftPane.setStyle("-fx-background-color: #c2c2c2");
        leftPane.setPadding(new Insets(0, 20, 0, 20));
        leftPane.setAlignment(Pos.CENTER_LEFT);
        BorderPane.setAlignment(leftPane, Pos.CENTER_LEFT);
        VBox.setVgrow(leftPane, Priority.ALWAYS);
        new1.setLeft(leftPane);

        Label address = new Label("Address");
        address.setMinWidth(500);

        TextField house = new TextField();
        house.setMinWidth(500);
        house.setPromptText("House Name/Number");

        TextField street = new TextField();
        street.setMinWidth(500);
        street.setPromptText("Street Name");

        TextField city = new TextField();
        city.setMinWidth(500);
        city.setPromptText("City/Town");

        TextField county = new TextField();
        county.setMinWidth(500);
        county.setPromptText("County");

        TextField postcode = new TextField();
        postcode.setMinWidth(500);
        postcode.setPromptText("Postcode");
        leftPane.getChildren().addAll(address, house, street, city, county, postcode);

        VBox rightPane = new VBox(200);
        rightPane.setStyle("-fx-background-color: #c2c2c2");
        rightPane.setPadding(new Insets(0, 20, 0, 20));
        rightPane.setAlignment(Pos.CENTER_RIGHT);
        BorderPane.setAlignment(rightPane, Pos.CENTER_RIGHT);
        VBox.setVgrow(rightPane, Priority.ALWAYS);
        new1.setRight(rightPane);

        TextField contactNumber = new TextField();
        contactNumber.setMinWidth(350);
        contactNumber.setPromptText("Contact Number");

        Button saveButton = new Button("Save");
        saveButton.setStyle("-fx-background-color: #f7cecc");
        saveButton.setMinSize(350, 20);
        saveButton.setOnAction((ActionEvent ae) -> controller.error(ae));
        rightPane.getChildren().addAll(contactNumber, saveButton);

        VBox centrePane = new VBox(0);
        centrePane.setStyle("-fx-background-color: #c2c2c2");
        centrePane.setPadding(new Insets(0));
        centrePane.setAlignment(Pos.CENTER);
        BorderPane.setAlignment(centrePane, Pos.CENTER);
        VBox.setVgrow(centrePane, Priority.ALWAYS);
        new1.setCenter(centrePane);


    }

    public static void editPane(Stage stage) {
        BorderPane edit = new BorderPane();
        Scene editCustomer = new Scene(edit, 1024, 768);
        stage.setScene(editCustomer);
        stage.setOnCloseRequest((WindowEvent we) -> controller.closeStage(parent,stage));
        stage.show();
        VBox topPane = new VBox(20);
        topPane.setStyle("-fx-background-color: #c2c2c2");
        topPane.setPadding(new Insets(20, 0, 0, 0));
        topPane.setAlignment(Pos.CENTER);
        BorderPane.setAlignment(topPane, Pos.TOP_CENTER);
        HBox.setHgrow(topPane, Priority.ALWAYS);
        edit.setTop(topPane);

        HBox tabButtons = new HBox(20);
        tabButtons.setStyle("-fx-background-color: #c2c2c2");
        tabButtons.setPadding(new Insets(0, 20, 0, 20));
        topPane.getChildren().add(tabButtons);

        Button searchCustomerButton = new Button("Search Customer");
        searchCustomerButton.setPrefSize(Integer.MAX_VALUE, 40);
        searchCustomerButton.setOnAction((ActionEvent ae) -> controller.openCustomerSearch(ae, stage));


        Button newCustomerButton = new Button("New Customer");
        newCustomerButton.setPrefSize(Integer.MAX_VALUE, 40);
        newCustomerButton.setOnAction((ActionEvent ae) -> controller.openCustomerNew(ae, stage));
        tabButtons.getChildren().addAll(searchCustomerButton, newCustomerButton);

        HBox labels = new HBox();
        labels.setStyle("-fx-background-color: #c2c2c2");
        labels.setPadding(new Insets(0, 20, 0, 20));
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
        textFields.setPadding(new Insets(0, 20, 0, 20));
        topPane.getChildren().add(textFields);

        TextField firstNameInput = new TextField();
        firstNameInput.setPrefSize(Integer.MAX_VALUE, 20);
        firstNameInput.setPromptText("First Name");

        TextField secondNameInput = new TextField();
        secondNameInput.setPrefSize(Integer.MAX_VALUE, 20);
        secondNameInput.setPromptText("Second Name");

        TextField datOfBirthInput = new TextField();
        datOfBirthInput.setPrefSize(Integer.MAX_VALUE, 20);
        datOfBirthInput.setPromptText("Date of Birth");
        textFields.getChildren().addAll(firstNameInput, secondNameInput, datOfBirthInput);

        VBox leftPane = new VBox(40);
        leftPane.setStyle("-fx-background-color: #c2c2c2");
        leftPane.setPadding(new Insets(0, 20, 0, 20));
        leftPane.setAlignment(Pos.CENTER_LEFT);
        BorderPane.setAlignment(leftPane, Pos.CENTER_LEFT);
        VBox.setVgrow(leftPane, Priority.ALWAYS);
        edit.setLeft(leftPane);

        Label address = new Label("Address");
        address.setMinWidth(500);

        TextField house = new TextField();
        house.setMinWidth(500);
        house.setPromptText("House Name/Number");

        TextField street = new TextField();
        street.setMinWidth(500);
        street.setPromptText("Street Name");

        TextField city = new TextField();
        city.setMinWidth(500);
        city.setPromptText("City/Town");

        TextField county = new TextField();
        county.setMinWidth(500);
        county.setPromptText("County");

        TextField postcode = new TextField();
        postcode.setMinWidth(500);
        postcode.setPromptText("Postcode");
        leftPane.getChildren().addAll(address, house, street, city, county, postcode);

        VBox rightPane = new VBox(200);
        rightPane.setStyle("-fx-background-color: #c2c2c2");
        rightPane.setPadding(new Insets(0, 20, 0, 20));
        rightPane.setAlignment(Pos.CENTER_RIGHT);
        BorderPane.setAlignment(rightPane, Pos.CENTER_RIGHT);
        VBox.setVgrow(rightPane, Priority.ALWAYS);
        edit.setRight(rightPane);

        TextField contactNumber = new TextField();
        contactNumber.setMinWidth(350);
        contactNumber.setPromptText("Contact Number");

        Button saveButton = new Button("Save Edit");
        saveButton.setStyle("-fx-background-color: #f7cecc");
        saveButton.setMinSize(350, 20);
        saveButton.setOnAction((ActionEvent ae) -> controller.saveEdit(parent,stage,firstNameInput,secondNameInput,datOfBirthInput,house,street,city,county,postcode,contactNumber));
        rightPane.getChildren().addAll(contactNumber, saveButton);

        VBox centrePane = new VBox(0);
        centrePane.setStyle("-fx-background-color: #c2c2c2");
        centrePane.setPadding(new Insets(0));
        centrePane.setAlignment(Pos.CENTER);
        BorderPane.setAlignment(centrePane, Pos.CENTER);
        VBox.setVgrow(centrePane, Priority.ALWAYS);
        edit.setCenter(centrePane);
        controller.updateInformation(firstNameInput,secondNameInput,datOfBirthInput,house,street,city,county,postcode,contactNumber);
    }
}
