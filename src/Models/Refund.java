package Models;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Refund {
    public int getCustomerID() {
        return customerID.get();
    }

    private final SimpleIntegerProperty customerID;
    private final SimpleIntegerProperty productID;
    private final SimpleStringProperty productDescription;
    private final SimpleIntegerProperty inStock;
    private final SimpleDoubleProperty price;

    public Refund(SimpleIntegerProperty customerID, Integer productID, String productDescription, Integer inStock, Double price) {
        this.customerID = customerID;
        this.productID = new SimpleIntegerProperty(productID);
        this.productDescription = new SimpleStringProperty(productDescription);
        this.inStock = new SimpleIntegerProperty(inStock);
        this.price = new SimpleDoubleProperty(price);
    }

    public SimpleIntegerProperty customerIDProperty() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID.set(customerID);
    }

    public int getProductID() {
        return productID.get();
    }

    public SimpleIntegerProperty productIDProperty() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID.set(productID);
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

    public int getInStock() {
        return inStock.get();
    }

    public SimpleIntegerProperty inStockProperty() {
        return inStock;
    }

    public void setInStock(int inStock) {
        this.inStock.set(inStock);
    }

    public double getPrice() {
        return price.get();
    }

    public SimpleDoubleProperty priceProperty() {
        return price;
    }

    public void setPrice(double price) {
        this.price.set(price);
    }
}
