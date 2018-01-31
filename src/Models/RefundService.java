package Models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RefundService {
    public static RefundModel SelectForRefund(ArrayList<RefundModel> targetList, int id, DatabaseConnection database) {
        RefundModel result = null;
        PreparedStatement statement = database.newStatement("Select TRANSACTIONS.TransactionID, TRANSACTIONS.CustomerID, " +
                "PRODUCTS.ProductDescription,TRANSACTIONS.TotalCost,TRANSACTIONS.Date From TRANSACTIONS " +
                "INNER JOIN BASKET ON TRANSACTIONS.TransactionID = BASKET.TransactionID " +
                "INNER JOIN PRODUCTS ON PRODUCTS.ProductID = BASKET.ProductID WHERE BASKET.TransactionID = ?");
        //Three way join to get all relevant data for refund.
        try {
            if (statement != null) {

                statement.setInt(1, id);
                ResultSet results = database.excecuteQuery(statement);

                if (results != null && !results.isAfterLast()) {
                    //runs if there is an item in the database matching that transaction ID
                    while (results.next()) {
                        //while loop gets all results not just the first one
                        targetList.add(new RefundModel(results.getInt("TransactionID"),
                                results.getInt("CustomerID"),
                                results.getString("ProductDescription"),
                                results.getDouble("TotalCost"),
                                results.getString("Date")));
                    }
                    //pulls all the relevant data from the database based on the transaction ID
                }

            }
        } catch(SQLException resultsException) {
            System.out.println("Database select for Refund error: " + resultsException.getMessage());
        }
        return result;
    }
}
