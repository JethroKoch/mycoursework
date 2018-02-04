package Controller;

import Models.*;
import View.*;
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


public class HomeStageController {

    private ArrayList<ProductModel> currentProduct = new ArrayList<>();

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
    private ArrayList<CustomerModel>customerForTransaction = new ArrayList<>();
    double total;
    double change;
    double amountGiven;

    public void addProduct(TextField searchBar, Label totalCost1, TextField amountGiven1, Label change1){

        int productID = 0;
        try {
            productID = Integer.parseInt(searchBar.getText());
        }
        catch (NumberFormatException nfe) {
        }

        ProductModel addedProduct = ProductService.selectById(productID, HomeStage.database);
        if (addedProduct !=null) {
            currentProduct.add(addedProduct);
            HomeStage.productsTable.setItems(FXCollections.observableArrayList(currentProduct));

            total = 0;
            for (ProductModel price : HomeStage.productsTable.getItems()) {
                if (price.getPrice() != 0) {
                    total += price.getPrice();
                    totalCost1.setText(Double.toString(total));
                }
            }
            checkChange(change1, amountGiven1);
        }else{
            genericError("Not a valid product ID");
        }
    }

    public void removeItem(Label change1,Label totalCost1,TextField amountGiven1){
        ProductModel selectedItem = HomeStage.productsTable.getSelectionModel().getSelectedItem();
        total = 0;
        HomeStage.productsTable.getItems().remove(selectedItem);
        currentProduct.remove(selectedItem);
        for (ProductModel price : HomeStage.productsTable.getItems()) {
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

            change = amountGiven - total;
            if (change < 0) {
                change1.setStyle("-fx-background-color: #f7070c");
            } else {
                change1.setStyle("-fx-background-color: forestgreen");
            }
            change1.setText(Double.toString(change));
    }

    public void selectCustomer(){
        if(customerID!=0) {
            customerForTransaction.clear();
            customerForTransaction.add(CustomerService.selectById(customerID, HomeStage.database));
            CustomerModel transactionCustomer = customerForTransaction.get(0);
            HomeStage.customerID1.setText(Integer.toString(transactionCustomer.getCustomerId()));
            HomeStage.name1.setText(transactionCustomer.getFirstName() + " " + transactionCustomer.getLastName());
            String[] dob =transactionCustomer.getDateOfBirth().split("/");
            LocalDate birthDate = LocalDate.parse(dob[2]+"-"+dob[1]+"-"+dob[0]);
            LocalDate curDate = LocalDate.now();
            String age = Integer.toString(Period.between(birthDate,curDate).getYears());
            HomeStage.age1.setText(age);

        }
    }
    public void process(Label totalCost,TextField amountPaid,Label change,Label customerId,Label name, Label age,TextField searchBar){
        if(currentProduct.size()==0){
            genericError("No items for transaction");
        } else if(Double.parseDouble(change.getText())<0){
            genericError("Not enough money for Transaction");
        } else{
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date date = new Date();
            String today  = dateFormat.format(date);
            TransactionModel newTransaction = new TransactionModel(0,customerID,Double.parseDouble(totalCost.getText()),Double.parseDouble(amountPaid.getText()),Double.parseDouble(change.getText()),today);
            TransactionService.save(newTransaction,HomeStage.database);

            ArrayList<BasketModel> temp = new ArrayList<>();
            for (ProductModel id:HomeStage.productsTable.getItems()){
                BasketModel newBasket = new BasketModel(HomeStage.database.lastNewId(),id.getProductID());
                temp.add(newBasket);
            }
            for(BasketModel item: temp)
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
            searchBar.setText(null);
        }
    }
    public static void genericError(String inputText) {
        Alert error = new Alert(Alert.AlertType.INFORMATION);
        error.setTitle("Error");
        error.setHeaderText(null);
        error.setContentText(inputText);
        error.showAndWait();
    }
}
