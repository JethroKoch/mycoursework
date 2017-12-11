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

    public CustomerStageController(){}

    public void openCustomerSearch(ActionEvent ae, Stage stage){
        CustomerStage.searchPane(stage);
    }
    public void openCustomerNew(ActionEvent ae, Stage stage) {CustomerStage.newPane(stage); }
    public void customerEdit(ActionEvent ae, Stage stage) {CustomerStage.editPane(stage); }

    public void search(ActionEvent ae, TextField firstName, TextField secondName, TextField postCode){
        String firstname = firstName.getText();
        String secondname = secondName.getText();
        String postcode = postCode.getText();
        currentCustomers.clear();
        currentCustomers.add(CustomerService.selectForList(firstname,secondname,postcode,HomeStage.database));
        CustomerStage.customersList.setItems(FXCollections.observableArrayList(currentCustomers));
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
