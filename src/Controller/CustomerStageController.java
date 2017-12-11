package Controller;

import Models.CustomerService;
import Models.CustomerView;
import Veiw.CustomerStage;
import Veiw.HomeStage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collection;


public class CustomerStageController {
    private ArrayList<CustomerView> customerList=new ArrayList<>();

    public CustomerStageController(){}

    public void openCustomerSearch(ActionEvent ae, Stage stage){
        CustomerStage.searchPane(stage);
    }
    public void openCustomerNew(ActionEvent ae, Stage stage) {CustomerStage.newPane(stage); }
    public void customerEdit(ActionEvent ae, Stage stage) {CustomerStage.editPane(stage); }

    public void search(ActionEvent ae, String firstName,String secondName, String postCode){
        customerList.clear();
        customerList.add(CustomerService.selectForList(firstName,secondName,postCode, HomeStage.database));
        CustomerStage.customersList.setItems(FXCollections.observableArrayList(customerList));

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
