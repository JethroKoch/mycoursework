package Controller;

import Models.CustomerService;
import Models.CustomerView;
import Models.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;

public class CustomerStageController {
    private ListView<CustomerView>customerList;
    private DatabaseConnection database;

    public CustomerStageController(ListView<CustomerView>customerList){
        this.customerList = customerList;

        database = new DatabaseConnection("Database.db");
    }

    public void search(ActionEvent ae){
        customerList.getItems().clear();
        CustomerService.selectAll(customerList.getItems(), database);

    }
    public static void error(ActionEvent ae) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("This feature has not been implicated yet");
        alert.showAndWait();
    }
}
