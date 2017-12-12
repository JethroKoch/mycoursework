package Controller;

import Models.CustomerService;
import Models.CustomerView;
import Models.CustomerViewSearch;
import Veiw.CustomerStage;
import Veiw.HomeStage;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;


public class CustomerStageController {
    private ArrayList<CustomerViewSearch> currentCustomers =new ArrayList<>();
    private ArrayList<CustomerView> itemForEditing = new ArrayList<>();

    public CustomerStageController(){}
    public int customerID;
    public void openCustomerSearch(ActionEvent ae, Stage stage){
        CustomerStage.searchPane(stage);
    }
    public void openCustomerNew(ActionEvent ae, Stage stage) {CustomerStage.newPane(stage); }
    public void customerEdit(ActionEvent ae, Stage stage) {
        CustomerViewSearch selectedItem = CustomerStage.customersList.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            customerID = selectedItem.getCustomerId();
            CustomerStage.customersList.getItems().clear();
            currentCustomers.clear();
            CustomerStage.editPane(stage);
        }else {
            Alert noCustomerSelected = new Alert(Alert.AlertType.INFORMATION);
            noCustomerSelected.setTitle("Error");
            noCustomerSelected.setHeaderText(null);
            noCustomerSelected.setContentText("No customer has been selected for editing");
            noCustomerSelected.showAndWait();
        }
    }


    public void search(ActionEvent ae, TextField firstName, TextField secondName, TextField postCode){
        String firstname = firstName.getText();
        String secondname = secondName.getText();
        String postcode = postCode.getText();
        currentCustomers.clear();
        currentCustomers.add(CustomerService.selectForList(firstname,secondname,postcode,HomeStage.database));
        CustomerStage.customersList.setItems(FXCollections.observableArrayList(currentCustomers));
    }


    public void updateInformation(TextField firstName, TextField lastName, TextField DOB,
                             TextField house, TextField street,TextField city,TextField county,
                             TextField postcode, TextField contactNo){
        itemForEditing.clear();
        itemForEditing.add(CustomerService.selectById(customerID,HomeStage.database));
        CustomerView customer = itemForEditing.get(0);
        firstName.setText(customer.getFirstName());
        lastName.setText(customer.getLastName());
        DOB.setText(customer.getDateOfBirth());
        house.setText(customer.getHouse());
        street.setText(customer.getStreet());
        city.setText(customer.getCity());
        county.setText(customer.getCounty());
        postcode.setText(customer.getPostcode());
        contactNo.setText("0"+customer.getContactNumber());
    }

    public void closeStage(Pane parent, Stage stage) {
        parent.setDisable(false);
        stage.close();

    }
    public static void error(ActionEvent ae) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("This feature has not been implicated yet");
        alert.showAndWait();
    }
}
