package Testcases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class DBTest {

    private Connection connection;
    private int expectedRowCount;

    @BeforeClass
    public void setUp() {
        try {
            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/Test";
            String username = "root";
            String password = "root";
            connection = DriverManager.getConnection(url, username, password);

            // Get the expected row count
            PreparedStatement countStatement = connection.prepareStatement("SELECT COUNT(*) AS total FROM users");
            ResultSet countResultSet = countStatement.executeQuery();
            if (countResultSet.next()) {
                expectedRowCount = countResultSet.getInt("total");
            }
            countResultSet.close();
            countStatement.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDatabase() {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users where id =1");
            ResultSet resultSet = statement.executeQuery();

            int actualRowCount = 0;
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                // Process the data as needed
                System.out.println("ID: " + id + ", Name: " + name);
                actualRowCount++;
            }
            
            // Close the ResultSet and PreparedStatement
            resultSet.close();
            statement.close();
            
            // Add assertion to validate the row count
            Assert.assertEquals(actualRowCount, expectedRowCount, "All rows should be read from the table.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @AfterClass
    public void tearDown() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
