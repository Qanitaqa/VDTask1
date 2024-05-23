package DataBase;

import java.sql.*;
public class DbConnection {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection connection = null;
        try {
            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            String url = "jdbc:mysql://localhost:3306/Test";
            String username = "root";
            String password = "root";
            connection = DriverManager.getConnection(url, username, password);
            
            
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users where  id=1");
            ResultSet resultSet = statement.executeQuery() ;
            
           while (resultSet.next()) {
               int id = resultSet.getInt("id");
               String name = resultSet.getString("name");
               // Process the data as needed
               System.out.println("ID: " + id + ", Name: " + name);
           }
            
        }catch(ClassNotFoundException  e) {
        	e.printStackTrace();
        } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}