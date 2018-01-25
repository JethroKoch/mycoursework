package Models;

public class BasketView {
    private int transactionId;
    //Created integer Object Transaction ID
    private int productId;
    //Created Integer object Product ID
    public BasketView(int transactionId, int productId) {
        this.transactionId = transactionId;
        this.productId = productId;
    }//Constructor created to initialize the objects

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
    //Getters and setters created for encapsulation purposes
}