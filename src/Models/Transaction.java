package Models;

import java.util.Date;

public class Transaction {
    private int transactionId;
    private int customerId;
    private double totalCost;
    private double amountPaid;
    private double change;
    private Date date;

    public Transaction(int transactiond, int customerid, double totalCost, double amountPaid, double change, Date date) {
        this.transactionId = transactionId;
        this.customerId = customerid;
        this.totalCost = totalCost;
        this.amountPaid = amountPaid;
        this.change = change;
        this.date = date;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public double getChange() {
        return change;
    }

    public void setChange(double change) {
        this.change = change;
    }

    public java.util.Date getDate() {
        return date;
    }

    public void setDate(java.util.Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId=" + transactionId +
                ", customerId=" + customerId +
                ", totalCost=" + totalCost +
                ", amountPaid=" + amountPaid +
                ", change=" + change +
                ", date=" + date +
                '}';
    }
}
