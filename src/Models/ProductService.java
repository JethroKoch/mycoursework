package Models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductService {
    public static List<ProductModel> selectByDescriptionList(ArrayList<ProductModel> targetList, String productDescription, DatabaseConnection database) {

        PreparedStatement statement = database.newStatement("SELECT ProductID, ProductDescription, InStock, Price FROM PRODUCTS WHERE ProductDescription LIKE ?");

        try {
            if (statement != null) {

                statement.setString(1,productDescription);
                ResultSet results = database.excecuteQuery(statement);

                if (results != null) {
                    while (results.next()) {
                        targetList.add(new ProductModel(
                                results.getInt("ProductID"),
                                results.getString("ProductDescription"),
                                results.getInt("InStock"),
                                results.getDouble("Price")));
                    }
                }
            }
        } catch (SQLException resultsException) {
            System.out.println("Database select by description error: " + resultsException.getMessage());
        }
        return targetList;
    }
    public static ProductModel SelectByDescription(String description, DatabaseConnection database) {
        ProductModel result = null;
        PreparedStatement statement = database.newStatement("SELECT ProductID, ProductDescription, InStock, Price FROM PRODUCTS WHERE ProductDescription = ?");

        try {

            if (statement != null) {

                statement.setString(1,description);
                ResultSet results = database.excecuteQuery(statement);

                if (results != null && !results.isAfterLast()) {
                    result = new ProductModel(
                            results.getInt("ProductID"),
                            results.getString("ProductDescription"),
                            results.getInt("InStock"),
                            results.getDouble("Price"));
                }
            }
        } catch (SQLException resultsException) {
            System.out.println("Database select by description error: " + resultsException.getMessage());
        }
        return result;
    }
    public static ProductModel selectById(int id, DatabaseConnection database) {
        ProductModel result = null;
        //the result will store the result of the query
        PreparedStatement statement = database.newStatement("SELECT ProductID, ProductDescription, InStock, Price FROM PRODUCTS WHERE ProductID = ?");
        //statement created to select an item from the product table
        try {

            if (statement != null) {
                statement.setInt(1,id);
                ResultSet results = database.excecuteQuery(statement);
                //created a variable to temporarily store the result and executes the query
                if (results != null && !results.isAfterLast()) {
                    result = new ProductModel(
                            results.getInt("ProductID"),
                            results.getString("ProductDescription"),
                            results.getInt("InStock"),
                            results.getDouble("Price"));
                }
                //if there is a result it is stored in the result variable
            }
        } catch (SQLException resultsException) {
            System.out.println("Database select by id error: " + resultsException.getMessage());
        }
        //any errors are caught and displayed to console
        return result;
        //the result is returned to whatever called the function
    }
    public static void save(ProductModel itemToSave, DatabaseConnection database) {

        ProductModel existingItem = null;
        if (itemToSave.getProductID() != 0) existingItem = selectById(itemToSave.getProductID(), database);

        try {
            if (existingItem == null) {
                PreparedStatement statement = database.newStatement("INSERT INTO PRODUCTS (ProductID, ProductDescription, InStock, Price) VALUES (?, ?, ?, ?)");
                statement.setInt(1, itemToSave.getProductID());
                statement.setString(2,itemToSave.getProductDescription());
                statement.setInt(3,itemToSave.getInStock());
                statement.setDouble(4,  itemToSave.getPrice());
                database.executeUpdate(statement);


            }
            else {
                PreparedStatement statement = database.newStatement("UPDATE PRODUCTS SET ProductDescription = ?, InStock = ?, Price =? WHERE ProductID = ?");;
                statement.setString(1,itemToSave.getProductDescription());
                statement.setInt(2,itemToSave.getInStock());
                statement.setDouble(3,  itemToSave.getPrice());
                statement.setInt(4,itemToSave.getProductID());
                database.executeUpdate(statement);
            }
        } catch (SQLException resultsException) { System.out.println("Database saving error: " + resultsException.getMessage());
        }
    }
    public static void deleteById(int id, DatabaseConnection database) {

        PreparedStatement statement = database.newStatement("DELETE FROM PRODUCTS WHERE ProductID = ?");
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
