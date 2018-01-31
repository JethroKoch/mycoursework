package Models;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BasketService {
    public static void save(BasketModel itemToSave, DatabaseConnection database){
        //Initialized function save
        PreparedStatement statement = database.newStatement("INSERT INTO BASKET(TransactionID, ProductID) VALUES(?,?) ");
        //created SQL Statement to run on the database, it will save a new item to database table Basket
        try {
            //Try statement to catch any errors
            statement.setInt(1,itemToSave.getTransactionId());
            statement.setInt(2,itemToSave.getProductId());
            database.executeUpdate(statement);
            //set the question marks in the statement to inputs values and run statement
        } catch (SQLException resultsException) {
            resultsException.getMessage();
        }
    }
    public static void deleteByID(int id, DatabaseConnection database){
        RefundModel result = null;
        PreparedStatement statement = database.newStatement("Delete From BASKET WHERE TransactionID = ?");
        //Statement created to delete an item from a database.
        try {
            if (statement != null) {
                statement.setInt(1, id);
                database.executeUpdate(statement);
                //runs statment if there is an item to delete
            }
        } catch (SQLException resultsException) {
            System.out.println("Database deletion error: " + resultsException.getMessage());
            //catches exceptions and displayes error message.
        }
    }
}
