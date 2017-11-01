package Models;

public class Basket {
    private int transactionid;
    private int productid;

    public int getTransactionid() {
        return transactionid;
    }

    public void setTransactionid(int transactionid) {
        this.transactionid = transactionid;
    }

    public int getProductid() {
        return productid;
    }

    public void setProductid(int productid) {
        this.productid = productid;
    }

    @Override
    public String toString() {
        return "Basket{" +
                "transactionid=" + transactionid +
                ", productid=" + productid +
                '}';
    }
}
