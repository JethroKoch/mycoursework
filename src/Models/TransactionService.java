package Models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class TransactionService {
    public static void selectAll(List<Transaction> targetList, DatabaseConnection database) {

        PreparedStatement statement = database.newStatement("SELECT TransactionID, CustomerID, TotalCost, AmountPaid, Change, Date FROM TRANSACTIONS ORDER BY x");

        try {
            if (statement != null) {

                ResultSet results = database.excecuteQuery(statement);

                if (results != null) {
                    while (results.next()) {
                        targetList.add(new Transaction(
                                results.getInt("TransactionID"),
                                results.getInt("CustomerID"),
                                results.getDouble("TotalCost"),
                                results.getDouble("AmountPaid"),
                                results.getDouble("Change"),
                                results.getDate("Date")));
                    }
                }
            }
        } catch (SQLException resultsException) {
            System.out.println("Database select all error: " + resultsException.getMessage());
        }
    }
    public static void selectById(int id, DatabaseConnection database) {
        Transaction result = null;
        PreparedStatement statement = database.newStatement("SELECT TransactionID, CustomerID, TotalCost, AmountPaid, Change, Date WHERE TransactionID = ?");

        try {
            statement.setInt(1,id);
            if (statement != null) {

                ResultSet results = database.excecuteQuery(statement);

                if (results != null) {
                    result = new Transaction(
                            results.getInt("TransactionID"),
                            results.getInt("CustomerID"),
                            results.getDouble("TotalCost"),
                            results.getDouble("AmountPaid"),
                            results.getDouble("Change"),
                            results.getDate("Date"));
                }
            }
        } catch (SQLException resultsException) {
            System.out.println("Database select all error: " + resultsException.getMessage());
        }
    }
    public static void save(Transaction itemToSave, DatabaseConnection database) {

        Transaction existingItem = null;
        if (itemToSave.getTransactionId() != 0) selectById(itemToSave.getTransactionId(), database);

        try {
            if (existingItem == null) {
                PreparedStatement statement = database.newStatement("INSERT INTO Transaction (TransactionID, CustomerID, TotalCost, AmountPaid, Change, Date) VALUES (?, ?, ?, ?, ?, ?))");
                statement.setInt(1, itemToSave.getTransactionId());
                statement.setInt(2, itemToSave.getCustomerId());
                statement.setDouble(3,itemToSave.getTotalCost());
                statement.setDouble(4,itemToSave.getAmountPaid());
                statement.setDouble(5,itemToSave.getChange());
                statement.setDate(6, (java.sql.Date) itemToSave.getDate());
            }
            else {
                PreparedStatement statement = database.newStatement("UPDATE TransactionS SET TransactionID = ?, CustomerID = ?, TotalCost = ?, AmountPaid =?, Change=?, Date=? WHERE TransactionID = ?");
                statement.setInt(1, itemToSave.getTransactionId());
                statement.setInt(1, itemToSave.getTransactionId());
                statement.setInt(2, itemToSave.getCustomerId());
                statement.setDouble(3,itemToSave.getTotalCost());
                statement.setDouble(4,itemToSave.getAmountPaid());
                statement.setDouble(5,itemToSave.getChange());
                statement.setDate(6, (java.sql.Date) itemToSave.getDate());
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
