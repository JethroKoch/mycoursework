package Models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CustomerService {
    public static void selectAll(List<Customer> targetList, DatabaseConnection database) {

        PreparedStatement statement = database.newStatement("SELECT CustomerID, FirstName, LastName, DOB, ContactNo, House, Street, City, County, Postcode FROM CUSTOMERS ORDER BY x");

        try {
            if (statement != null) {

                ResultSet results = database.runQuery(statement);

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
}
