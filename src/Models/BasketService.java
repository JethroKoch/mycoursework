package Models;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BasketService {
    public static void save(BasketView itemToSave, DatabaseConnection database){
        PreparedStatement statement = database.newStatement("INSERT INTO BASKET(TransactionID, ProductID) VALUES(?,?) ");
        try {
            statement.setInt(1,itemToSave.getTransactionId());
            statement.setInt(2,itemToSave.getProductId());
        } catch (SQLException resultsException) {
            resultsException.getMessage();
        }
    }
}
