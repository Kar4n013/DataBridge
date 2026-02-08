package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class GetConnection {
public static Connection getConnection(String db_name,String user,String password) {
	Connection connection = null;
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	}
	String url = "jdbc:mysql://localhost:3306/";
	try {
		connection = DriverManager.getConnection((url+db_name),user,password);
		if (connection != null) {
			System.out.println("Connection established");
			
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}

	return connection;
	
}
}
