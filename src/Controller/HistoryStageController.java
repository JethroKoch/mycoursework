package Controller;

import Models.TransactionService;
import Models.TransactionModel;
import View.HistoryStage;
import View.HomeStage;
import javafx.collections.FXCollections;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class HistoryStageController {
    private ArrayList<TransactionModel> currentTransaction = new ArrayList<>();

    public HistoryStageController(){}
    public void loadResults(TextField customerID){
        if(customerID.getText().isEmpty()) {
            HomeStageController.genericError("No customer Id");
        }else{
            currentTransaction.clear();
            int customerId = Integer.parseInt(customerID.getText());
            currentTransaction.add(TransactionService.selectForList(customerId, HomeStage.database));
            HistoryStage.historyTable.setItems(FXCollections.observableArrayList(currentTransaction));
        }
    }
    public void closeStage(Pane parent, Stage stage) {

        parent.setDisable(false);
        stage.close();

    }
}
