package Models;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BasketService {
    public static void save(BasketView itemToSave, DatabaseConnection database){
        PreparedStatement statement = database.newStatement("INSERT INTO BASKET(BasketID,TransactionID, ProductID) VALUES(?,?,?) ");
        try {
            statement.setInt(1,itemToSave.getBasketId());
            statement.setInt(2,itemToSave.getTransactionId());
            statement.setInt(3,itemToSave.getProductId());
            database.executeUpdate(statement);
        } catch (SQLException resultsException) {
            resultsException.getMessage();
        }
    }
}
