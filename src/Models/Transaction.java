package Models;

import java.util.Date;

public class Transaction {
    private int transactionid;
    private int customerid;
    private double totalCost;
    private double amountPaid;
    private double change;
    private Date date;

    public Transaction(int transactionid, int customerid, double totalCost, double amountPaid, double change, Date date) {
        this.transactionid = transactionid;
        this.customerid = customerid;
        this.totalCost = totalCost;
        this.amountPaid = amountPaid;
        this.change = change;
        this.date = date;
    }

    public int getTransactionid() {
        return transactionid;
    }

    public void setTransactionid(int transactionid) {
        this.transactionid = transactionid;
    }

    public int getCustomerid() {
        return customerid;
    }

    public void setCustomerid(int customerid) {
        this.customerid = customerid;
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
                "transactionid=" + transactionid +
                ", customerid=" + customerid +
                ", totalCost=" + totalCost +
                ", amountPaid=" + amountPaid +
                ", change=" + change +
                ", date=" + date +
                '}';
    }
}
