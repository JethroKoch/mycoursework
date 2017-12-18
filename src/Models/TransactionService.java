package Models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TransactionService {

    public static TransactionTableView selectById(int transactionId, DatabaseConnection database) {
        TransactionTableView result = null;
        PreparedStatement statement = database.newStatement("SELECT TransactionID, CustomerID, TotalCost, AmountPaid, Change, Date WHERE TransactionID = ?");

        try {
            if (statement != null) {
                statement.setInt(1,transactionId);
                ResultSet results = database.excecuteQuery(statement);

                if (results != null&&!results.isAfterLast()) {
                    result = new TransactionTableView(
                            results.getInt("TransactionID"),
                            results.getInt("CustomerID"),
                            results.getDouble("TotalCost"),
                            results.getDouble("AmountPaid"),
                            results.getDouble("Change"),
                            results.getString("Date"));
                }
            }
        } catch (SQLException resultsException) {
            System.out.println("Database select all error: " + resultsException.getMessage());
        }
        return result;
    }
    public static TransactionTableView selectForList(int customerID, DatabaseConnection database) {
        TransactionTableView result = null;
        PreparedStatement statement = database.newStatement("SELECT TransactionID, CustomerID, TotalCost, AmountPaid, Change, Date FROM TRANSACTIONS WHERE CustomerID = ?");

        try {

            if (statement != null) {
                statement.setInt(1,customerID);
                ResultSet results = database.excecuteQuery(statement);

                if (results != null&&!results.isAfterLast()) {
                    result = new TransactionTableView(
                            results.getInt("TransactionID"),
                            results.getInt("CustomerID"),
                            results.getDouble("TotalCost"),
                            results.getDouble("AmountPaid"),
                            results.getDouble("Change"),
                            results.getString("Date"));
                }
            }
        } catch (SQLException resultsException) {
            System.out.println("Database selectForList error: " + resultsException.getMessage());
        }
        return result;
    }
    public static void save(TransactionTableView itemToSave, DatabaseConnection database) {

        TransactionTableView existingItem = null;
        if (itemToSave.getTransactionID() != 0) selectById(itemToSave.getTransactionID(), database);

        try {
            if (existingItem == null) {
                PreparedStatement statement = database.newStatement("INSERT INTO TRANSACTIONS (CustomerID, TotalCost, AmountPaid, Change, Date) VALUES (?, ?, ?, ?, ?)");
                statement.setInt(1, itemToSave.getCustomerID());
                statement.setDouble(2,itemToSave.getTotalCost());
                statement.setDouble(3,itemToSave.getAmountPaid());
                statement.setDouble(4,itemToSave.getChange());
                statement.setString(5, itemToSave.getDate());
                database.executeUpdate(statement);
            }
            else {
                PreparedStatement statement = database.newStatement("UPDATE TRANSACTIONS SET TransactionID = ?, CustomerID = ?, TotalCost = ?, AmountPaid =?, Change=?, Date=? WHERE TransactionID = ?");
                statement.setInt(1, itemToSave.getTransactionID());
                statement.setInt(2, itemToSave.getTransactionID());
                statement.setDouble(3,itemToSave.getTotalCost());
                statement.setDouble(4,itemToSave.getAmountPaid());
                statement.setDouble(5,itemToSave.getChange());
                statement.setString(6, itemToSave.getDate());
                database.executeUpdate(statement);
            }
        } catch (SQLException resultsException) {
            System.out.println("Database saving error: " + resultsException.getMessage());
        }
    }
    public static void deleteById(int id, DatabaseConnection database) {

        PreparedStatement statement = database.newStatement("DELETE FROM TRANSACTIONS WHERE TransactionID = ?");

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
