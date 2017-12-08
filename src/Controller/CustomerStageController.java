package Controller;

import Models.CustomerService;
import Models.CustomerView;
import Models.DatabaseConnection;
import Veiw.CustomerStage;
import Veiw.HomeStage;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class CustomerStageController {
    private ListView<CustomerView>customerList;

    public CustomerStageController(){}

    public void openCustomerSearch(ActionEvent ae, Stage stage){
        CustomerStage.searchPane(stage);
    }
    public void openCustomerNew(ActionEvent ae, Stage stage) {CustomerStage.newPane(stage); }
    public void customerEdit(ActionEvent ae, Stage stage) {CustomerStage.editPane(stage); }

    public void search(ActionEvent ae){
        customerList.getItems().clear();
        CustomerService.selectAll(customerList.getItems(), HomeStage.database);

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
