package Models;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CustomerService {
    public static void selectAll(List<Customer> targetList, DatabaseConnection database) {

        PreparedStatement statement = database.newStatement("SELECT CustomerID, FirstName, LastName, DOB, ContactNo, House, Street, City, County, Postcode FROM CUSTOMERS ORDER BY x");

        try {
            if (statement != null) {

                ResultSet results = database.excecuteQuery(statement);

                if (results != null) {
                    while (results.next()) {
                        targetList.add(new Customer(
                                results.getInt("CustomerID"),
                                results.getString("FirstName"), 
                                results.getString("Lastname"),
                                results.getDate("DOB"),
                                results.getString("ContactNo"),
                                results.getString("House"),
                                results.getString("Street"),
                                results.getString("City"),
                                results.getString("County"),
                                results.getString("Postcode")));
                    }
                }
            }
        } catch (SQLException resultsException) {
            System.out.println("Database select all error: " + resultsException.getMessage());
        }
    }
    public static Customer selectById(int id, DatabaseConnection database) {

        Customer result = null;

        PreparedStatement statement = database.newStatement("SELECT CustomerID, FirstName, LastName, DOB, ContactNo, House, Street, City, County, Postcode FROM CUSTOMERS WHERE CustomerID = ?");

        try {
            if (statement != null) {

                statement.setInt(1, id);
                ResultSet results = database.excecuteQuery(statement);

                if (results != null) {
                    result = new Customer(results.getInt("CustomerID"),
                            results.getString("FirstName"),
                            results.getString("Lastname"),
                            results.getDate("DOB"),
                            results.getString("ContactNo"),
                            results.getString("House"),
                            results.getString("Street"),
                            results.getString("City"),
                            results.getString("County"),
                            results.getString("Postcode"));
                }
            }
        } catch (SQLException resultsException) {
            System.out.println("Database select by id error: " + resultsException.getMessage());
        }

        return result;
    }
    public static void save(Customer itemToSave, DatabaseConnection database) {

        Customer existingItem = null;
        if (itemToSave.getCustomerId() != 0) existingItem = selectById(itemToSave.getCustomerId(), database);

        try {
            if (existingItem == null) {
                PreparedStatement statement = database.newStatement("INSERT INTO CUSTOMER (CustomerID, FirstName, LastName, DOB, ContactNo, House, Street, City, County, Postcode) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?))");
                statement.setInt(1, itemToSave.getCustomerId());
                statement.setString(2, itemToSave.getFirstName());
                statement.setString(3, itemToSave.getLastName());
                statement.setDate(4, (Date) itemToSave.getDateOfBirth());
                statement.setString(4, itemToSave.getContactNumber());
                statement.setString(5, itemToSave.getHouse());
                statement.setString(6, itemToSave.getStreet());
                statement.setString(7,itemToSave.getCity());
                statement.setString(8,itemToSave.getCounty());
                statement.setString(9,itemToSave.getPostcode());
                database.executeUpdate(statement);
            }
            else {
                PreparedStatement statement = database.newStatement("UPDATE CUSTOMERS SET CustomerID = ?, FirstName = ?, LastName = ?, DOB=?, ContactNo=?, House=?, Street=?, City=?, County=?, Postcode=? WHERE CustomerID = ?");
                statement.setInt(1, itemToSave.getCustomerId());
                statement.setString(2, itemToSave.getFirstName());
                statement.setString(3, itemToSave.getLastName());
                statement.setDate(4, (Date) itemToSave.getDateOfBirth());
                statement.setString(4, itemToSave.getContactNumber());
                statement.setString(5, itemToSave.getHouse());
                statement.setString(6, itemToSave.getStreet());
                statement.setString(7,itemToSave.getCity());
                statement.setString(8,itemToSave.getCounty());
                statement.setString(9,itemToSave.getPostcode());
                database.executeUpdate(statement);
            }
        } catch (SQLException resultsException) {
            System.out.println("Database saving error: " + resultsException.getMessage());
        }
    }
    public static void deleteById(int id, DatabaseConnection database) {

        PreparedStatement statement = database.newStatement("DELETE FROM CUSTOMERS WHERE CustomerID  = ?");

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
