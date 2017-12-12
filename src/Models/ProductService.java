package Models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ProductService {
    public static void selectAll(List<ProductView> targetList, DatabaseConnection database) {

        PreparedStatement statement = database.newStatement("SELECT ProductID, ProductDescription, InStock, Price FROM PRODUCTS ORDER BY ProductID");

        try {
            if (statement != null) {

                ResultSet results = database.excecuteQuery(statement);

                if (results != null) {
                    while (results.next()) {
                        targetList.add(new ProductView(
                                results.getInt("ProductID"),
                                results.getString("ProductDescription"),
                                results.getInt("InStock"),
                                results.getDouble("Price")));
                    }
                }
            }
        } catch (SQLException resultsException) {
            System.out.println("Database select all error: " + resultsException.getMessage());
        }
    }
    public static ProductView selectById(int id, DatabaseConnection database) {
        ProductView result = null;
        PreparedStatement statement = database.newStatement("SELECT ProductID, ProductDescription, InStock, Price FROM PRODUCTS WHERE ProductID = ?");

        try {

            if (statement != null) {

                statement.setInt(1,id);
                ResultSet results = database.excecuteQuery(statement);

                if (results != null && !results.isAfterLast()) {
                    result = new ProductView(
                            results.getInt("ProductID"),
                            results.getString("ProductDescription"),
                            results.getInt("InStock"),
                            results.getDouble("Price"));
                }
            }
        } catch (SQLException resultsException) {
            System.out.println("! Database select by id error: " + resultsException.getMessage());
        }
        return result;
    }
    public static void save(ProductView itemToSave, DatabaseConnection database) {

        ProductView existingItem = null;
        if (itemToSave.getProductID() != 0) selectById(itemToSave.getProductID(), database);

        try {
            if (existingItem == null) {
                PreparedStatement statement = database.newStatement("INSERT INTO ProductView (ProductID, ProductDescription, InStock, Price) VALUES (?, ?, ?, ?, ?, ?)");
                statement.setInt(1, itemToSave.getProductID());
                statement.setString(2,itemToSave.getProductDescription());
                statement.setInt(3,itemToSave.getInStock());
                statement.setDouble(4,  itemToSave.getPrice());


            }
            else {
                PreparedStatement statement = database.newStatement("UPDATE PRODUCTS SET ProductID = ?, ProductDescription = ?, InStock = ?, Price =? WHERE CustomerID = ?");
                statement.setInt(1, itemToSave.getProductID());
                statement.setString(2,itemToSave.getProductDescription());
                statement.setInt(3,itemToSave.getInStock());
                statement.setDouble(4,  itemToSave.getPrice());
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
