package Models;

public class BasketView {
    private int transactionId;
    private int productId;

    public BasketView(int transactionId, int productId) {
        this.transactionId = transactionId;
        this.productId = productId;
    }

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

    @Override
    public String toString() {
        return transactionId + ", " + productId;
    }
}
