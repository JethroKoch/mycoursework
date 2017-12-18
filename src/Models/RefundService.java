package Models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RefundService {
    public static RefundView SelectForRefund(int id, DatabaseConnection database) {
        RefundView result = null;
        PreparedStatement statement = database.newStatement("Select TRANSACTIONS.TransactionID, TRANSACTIONS.CustomerID, PRODUCTS.ProductDescription,TRANSACTIONS.TotalCost,TRANSACTIONS.Date From TRANSACTIONS " +
                "INNER JOIN BASKET ON TRANSACTIONS.TransactionID = BASKET.TransactionID " +
                "INNER JOIN PRODUCTS ON PRODUCTS.ProductID = BASKET.ProductID WHERE TRANSACTIONS.TransactionID = ?");
        try {
            if (statement != null) {
                statement.setInt(1, id);
                ResultSet results = database.excecuteQuery(statement);

                if (results != null && !results.isAfterLast()) {
                    result = new RefundView(
                            results.getInt("TransactionID"),
                            results.getInt("CustomerID"),
                            results.getString("ProductDescription"),
                            results.getDouble("TotalCost"),
                            results.getString("Date"));
                }

            }
        } catch (SQLException resultsException) {
            System.out.println("Database select for Refund error: " + resultsException.getMessage());
        }
        return result;
    }
}