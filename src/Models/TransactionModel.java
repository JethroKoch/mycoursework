package Models;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class TransactionModel {
    private final SimpleIntegerProperty transactionID;
    private final SimpleIntegerProperty customerID;
    private final SimpleDoubleProperty totalCost;
    private final SimpleDoubleProperty amountPaid;
    private final SimpleDoubleProperty change;
    private final SimpleStringProperty date;

    public TransactionModel(int transactionID, int customerID, double totalCost, double amountPaid, double changeGiven, String date) {
        this.transactionID = new SimpleIntegerProperty(transactionID);
        this.customerID = new SimpleIntegerProperty(customerID);
        this.totalCost = new SimpleDoubleProperty(totalCost);
        this.amountPaid = new SimpleDoubleProperty(amountPaid);
        this.change = new SimpleDoubleProperty(changeGiven);
        this.date = new SimpleStringProperty(date);
    }

    public int getTransactionID() {
        return transactionID.get();
    }

    public SimpleIntegerProperty transactionIDProperty() {
        return transactionID;
    }

    public void setTransactionID(int transactionID) {
        this.transactionID.set(transactionID);
    }

    public int getCustomerID() {
        return customerID.get();
    }

    public SimpleIntegerProperty customerIDProperty() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID.set(customerID);
    }

    public double getTotalCost() {
        return totalCost.get();
    }

    public SimpleDoubleProperty totalCostProperty() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost.set(totalCost);
    }

    public double getAmountPaid() {
        return amountPaid.get();
    }

    public SimpleDoubleProperty amountPaidProperty() {
        return amountPaid;
    }

    public void setAmountPaid(double amountPaid) {
        this.amountPaid.set(amountPaid);
    }

    public double getChange() {
        return change.get();
    }

    public SimpleDoubleProperty changeProperty() {
        return change;
    }

    public void setChange(double change) {
        this.change.set(change);
    }

    public String getDate() {
        return date.get();
    }

    public SimpleStringProperty dateProperty() {
        return date;
    }

    public void setDate(String date) {
        this.date.set(date);
    }

    @Override
    public String toString() {
        return transactionID +
                ", " + customerID +
                ", " + totalCost +
                ", " + amountPaid +
                ", " + change +
                ", " + date;
    }
}
