package sample;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class purchaseTable {
    private final SimpleIntegerProperty productID;
    private final SimpleStringProperty productDescription;
    private final SimpleIntegerProperty inStock;
    private final SimpleIntegerProperty quantity;
    private final SimpleDoubleProperty price;

    public purchaseTable(Integer productID, String productDescription, Integer inStock, Integer quantity, Double price) {
        this.productID = new SimpleIntegerProperty(productID);
        this.productDescription = new SimpleStringProperty(productDescription);
        this.inStock = new SimpleIntegerProperty(inStock);
        this.quantity = new SimpleIntegerProperty(quantity);
        this.price = new SimpleDoubleProperty(price);
    }

    public Integer getProductID() {
        return productID.get();
    }

    public void setProductID(Integer productID) {
        this.productID.set(productID);
    }

    public String getProductDescription() {
        return productDescription.get();
    }

    public void setProductDescription(String productDescription) {
        this.productDescription.set(productDescription);
    }
    public Integer getInstock(){
        return inStock.get();
    }
    public void setInStock(Integer inStock){
        this.inStock.set(inStock);
    }
    public Integer getQuantity(){
        return quantity.get();
    }
    public void setQuantity(Integer quantity){
        this.quantity.set(quantity);
    }
    public Double getPrice() {
        return price.get();
    }
    public void setPrice(Double price){
        this.price.set(price);
    }
}
