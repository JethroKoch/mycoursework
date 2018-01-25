package Controller;

import Models.CustomerService;
import Models.CustomerView;
import Models.CustomerViewSearch;
import View.CustomerStage;
import View.HomeStage;
import javafx.collections.FXCollections;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;


public class CustomerStageController {
    private ArrayList<CustomerViewSearch> currentCustomers =new ArrayList<>();
    private static HomeStageController controller;
    public CustomerStageController(){controller = new HomeStageController(); }
    public int customerID;
    public void openCustomerSearch(Stage stage){
        CustomerStage.searchPane(stage);
    }
    public void openCustomerNew(Stage stage) {CustomerStage.newPane(stage); }

    public void customerEdit(Stage stage) {
        CustomerViewSearch selectedItem = CustomerStage.customersList.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            customerID = selectedItem.getCustomerId();
            CustomerStage.customersList.getItems().clear();
            currentCustomers.clear();
            CustomerStage.editPane(stage);
        }else {
            HomeStageController.genericError("No customer has been selected for editing");
        }
    }


    public void search(TextField firstName, TextField secondName, TextField postCode){
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
        CustomerView customer = CustomerService.selectById(customerID,HomeStage.database);
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
    public void selectCustomer(Pane parent, Stage stage){
        CustomerViewSearch selectedItem = CustomerStage.customersList.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            HomeStageController.customerID = selectedItem.getCustomerId();
            CustomerStage.customersList.getItems().clear();
            currentCustomers.clear();
            closeStage(parent, stage);
            controller.selectCustomer();

        }else {
            HomeStageController.genericError("No customer has been selected for transaction");
        }

    }public void saveEdit(Pane parent,Stage stage,TextField firstName, TextField secondName,TextField DOB, TextField contactNumber,TextField house,TextField street,
                          TextField city,TextField county, TextField postcode){
        CustomerView updatedCustomer = new CustomerView(customerID,firstName.getText(),secondName.getText(),DOB.getText(),contactNumber.getText(),house.getText(),
                street.getText(),city.getText(),county.getText(),postcode.getText());
        System.out.print(updatedCustomer);
        CustomerService.save(updatedCustomer,HomeStage.database);
        HomeStageController.customerID = customerID;
        closeStage(parent,stage);
        controller.selectCustomer();
    }
    public void saveNew(Pane parent,Stage stage,TextField firstName, TextField secondName,TextField DOB, TextField contactNumber,TextField house,TextField street,
                        TextField city,TextField county, TextField postcode){
        CustomerView newCustomer = new CustomerView(0,firstName.getText(),secondName.getText(),DOB.getText(),contactNumber.getText(),house.getText(),
                street.getText(),city.getText(),county.getText(),postcode.getText());
        CustomerService.save(newCustomer,HomeStage.database);
        HomeStageController.customerID = HomeStage.database.lastNewId();
        closeStage(parent,stage);
        controller.selectCustomer();
    }

    public void deleteCustomer(TextField firstName, TextField lastName, TextField postcode){
        CustomerViewSearch selectedItem = CustomerStage.customersList.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            customerID = selectedItem.getCustomerId();
            CustomerService.deleteById(customerID,HomeStage.database);
            currentCustomers.clear();
            CustomerStage.customersList.getItems().clear();
            firstName.clear();
            lastName.clear();
            postcode.clear();

        }else {
            HomeStageController.genericError("No customer has been selected to delete");
        }


    }

    public void closeStage(Pane parent, Stage stage) {
        parent.setDisable(false);
        stage.close();

    }
}
