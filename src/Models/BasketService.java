package Models;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BasketService {
    public static void save(BasketView itemToSave, DatabaseConnection database){
        PreparedStatement statement = database.newStatement("INSERT INTO BASKET(TransactionID, ProductID) VALUES(?,?) ");
        try {
            statement.setInt(1,itemToSave.getTransactionId());
            statement.setInt(2,itemToSave.getProductId());
            database.executeUpdate(statement);
        } catch (SQLException resultsException) {
            resultsException.getMessage();
        }
    }
    public static void deleteByID(int id, DatabaseConnection database){
        RefundView result = null;
        PreparedStatement statement = database.newStatement("Delete From BASKET WHERE TransactionID = ?");
        try {
            if (statement != null) {
                statement.setInt(1, id);
                database.executeUpdate(statement);
            }
        } catch (SQLException resultsException) {
            System.out.println("Database deletion error: " + resultsException.getMessage());
        }
    }
}
