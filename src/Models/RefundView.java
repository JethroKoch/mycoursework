package Models;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class RefundView {
    private final SimpleIntegerProperty transactionId;
    private final SimpleIntegerProperty customerId;
    private final SimpleStringProperty productDescription;
    private final SimpleDoubleProperty totalCost;
    private final SimpleStringProperty date;

    public RefundView(int transactionId, int customerId, String productDescription, double totalCost, String date) {
        this.transactionId = new SimpleIntegerProperty (transactionId);
        this.customerId = new SimpleIntegerProperty(customerId);
        this.productDescription = new SimpleStringProperty(productDescription);
        this.totalCost = new SimpleDoubleProperty(totalCost);
        this.date = new SimpleStringProperty(date);
    }

    public int getTransactionId() {
        return transactionId.get();
    }

    public SimpleIntegerProperty transactionIdProperty() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId.set(transactionId);
    }

    public int getCustomerId() {
        return customerId.get();
    }

    public SimpleIntegerProperty customerIdProperty() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId.set(customerId);
    }

    public String getProductDescription() {
        return productDescription.get();
    }

    public SimpleStringProperty productDescriptionProperty() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription.set(productDescription);
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
        return transactionId +
                ", " + customerId +
                ", " + productDescription +
                ", " + totalCost +
                ", " + date;
    }
}
