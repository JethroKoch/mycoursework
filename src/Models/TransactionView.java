package Models;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class TransactionView {
    private final SimpleIntegerProperty transactionID;
    private final SimpleIntegerProperty customerID;
    private final SimpleDoubleProperty totalCost;
    private final SimpleDoubleProperty amountPaid;
    private final SimpleDoubleProperty changeGiven;
    private final SimpleObjectProperty<Date> date;

    public TransactionView(int transactionID, int customerID, double totalCost, double amountPaid, double changeGiven, Date date) {
        this.transactionID = new SimpleIntegerProperty(transactionID);
        this.customerID = new SimpleIntegerProperty(customerID);
        this.totalCost = new SimpleDoubleProperty(totalCost);
        this.amountPaid = new SimpleDoubleProperty(amountPaid);
        this.changeGiven = new SimpleDoubleProperty(changeGiven);
        this.date = new SimpleObjectProperty<>(date);
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

    public double getChangeGiven() {
        return changeGiven.get();
    }

    public SimpleDoubleProperty changeGivenProperty() {
        return changeGiven;
    }

    public void setChangeGiven(double changeGiven) {
        this.changeGiven.set(changeGiven);
    }

    public Date getDate() {
        return date.get();
    }

    public SimpleObjectProperty<Date> dateProperty() {
        return date;
    }

    public void setDate(Date date) {
        this.date.set(date);
    }
}
