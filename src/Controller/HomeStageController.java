package Controller;

import Models.*;
import Veiw.*;
import javafx.collections.FXCollections;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Date;

import static sun.text.normalizer.UCharacter.getAge;


public class HomeStageController {

    private ArrayList<ProductView> currentProduct = new ArrayList<>();

    public HomeStageController() {}

    public static void openCustomerStage(Pane parent) {
        CustomerStage newStage = new CustomerStage(parent);
    }
    public static void openHistoryStage(Pane parent) {
        HistoryStage newStage = new HistoryStage(parent);
    }
    public static void openRefundStage(Pane parent) {
        RefundStage newStage = new RefundStage(parent);
    }
    public static void openStockAdjustmentStage(Pane parent) { StockAdjustmentStage newStage = new StockAdjustmentStage(parent);}
    public static int customerID;
    private ArrayList<CustomerView>customerForTransaction = new ArrayList<>();
    double total;
    double change;
    double amountGiven;

    public void addProduct(TextField searchBar, Label totalCost1, TextField amountGiven1, Label change1){

        int productID = 0;
        try {
            productID = Integer.parseInt(searchBar.getText());
        }
        catch (NumberFormatException nfe) {
            System.out.println (nfe.getMessage());
        }

        currentProduct.add(ProductService.selectById(productID, HomeStage.database));
        HomeStage.productsTable.setItems(FXCollections.observableArrayList(currentProduct));


        total = 0;
        for(ProductView price:HomeStage.productsTable.getItems()){
            if(price.getPrice()!=0) {
                total += price.getPrice();
                totalCost1.setText(Double.toString(total));
            }
        }
        checkChange(change1, amountGiven1);
    }

    public void removeItem(Label change1,Label totalCost1,TextField amountGiven1){
        ProductView selectedItem = HomeStage.productsTable.getSelectionModel().getSelectedItem();
        total = 0;
        HomeStage.productsTable.getItems().remove(selectedItem);
        currentProduct.remove(selectedItem);
        for (ProductView price : HomeStage.productsTable.getItems()) {
            if (price.getPrice() != 0) {
                total += price.getPrice();
            }
        }
        totalCost1.setText(Double.toString(total));
        checkChange(change1, amountGiven1);
    }

    public void updateCost(TextField amountGiven1, Label change1){
        amountGiven = Double.parseDouble(amountGiven1.getText());
        checkChange(change1,amountGiven1);
    }
    public void checkChange(Label change1,TextField amountGiven1){

        if(Double.parseDouble(amountGiven1.getText())==0){
            amountGiven1.setText(Double.toString(amountGiven));
            change = 0;
            change1.setText(Double.toString(change));
        }else {
            change = amountGiven - total;
            if (change < 0) {
                change1.setStyle("-fx-background-color: #f7070c");
            } else {
                change1.setStyle("-fx-background-color: forestgreen");
            }
            change1.setText(Double.toString(change));
        }
    }

    public void selectCustomer(){
        if(customerID!=0) {
            customerForTransaction.clear();
            customerForTransaction.add(CustomerService.selectById(customerID, HomeStage.database));
            CustomerView transactionCustomer = customerForTransaction.get(0);
            HomeStage.customerID1.setText(Integer.toString(transactionCustomer.getCustomerId()));
            HomeStage.name1.setText(transactionCustomer.getFirstName() + " " + transactionCustomer.getLastName());
            String[] dob =transactionCustomer.getDateOfBirth().split("/");
            LocalDate birthDate = LocalDate.parse(dob[2]+"-"+dob[1]+"-"+dob[0]);
            LocalDate curDate = LocalDate.now();
            String age = Integer.toString(Period.between(birthDate,curDate).getYears());
            HomeStage.age1.setText(age);

        }
    }
    public void process(Label totalCost,TextField amountPaid,Label change,Label customerId,Label name, Label age){
        if(Double.parseDouble(change.getText())<0||Double.parseDouble(totalCost.getText())==0||Double.parseDouble(amountPaid.getText())==0){
            Alert notEnoughFunds = new Alert(Alert.AlertType.INFORMATION);
            notEnoughFunds.setTitle("Error");
            notEnoughFunds.setHeaderText(null);
            notEnoughFunds.setContentText("Not enough funds for transaction");
            notEnoughFunds.showAndWait();
        }else{
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date date = new Date();
            String today  = dateFormat.format(date);
            TransactionTableView newTransaction = new TransactionTableView(0,customerID,Double.parseDouble(totalCost.getText()),Double.parseDouble(amountPaid.getText()),Double.parseDouble(change.getText()),today);
            TransactionService.save(newTransaction,HomeStage.database);

            ArrayList<BasketView> temp = new ArrayList<>();
            for (ProductView id:HomeStage.productsTable.getItems()){
                BasketView newBasket = new BasketView(HomeStage.database.lastNewId(),id.getProductID());
                System.out.println(newBasket);
                temp.add(newBasket);
            }
            for(BasketView item: temp)
            BasketService.save(item,HomeStage.database);

            currentProduct.clear();
            HomeStage.productsTable.getItems().clear();
            CustomerStage.customersList.getItems().clear();
            customerId.setText(null);
            name.setText(null);
            age.setText(null);
            totalCost.setText(null);
            amountPaid.clear();
            change.setText(null);
        }
    }
}
