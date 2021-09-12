package configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB_Connector {
	
	
	public static Connection connect() throws SQLException, ClassNotFoundException {
		
		Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/employee", "root","tiger");
		return con;
	}
	public static void main(String[] args) {
		try {
			DB_Connector.connect();
			System.out.println("Success");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
