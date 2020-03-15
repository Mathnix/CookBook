package dbUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbConnection {
	
	public static Connection getConnection() throws SQLException{
		
		try {
			Class.forName("org.sqlite.JDBC");
			
			return DriverManager.getConnection("jdbc:sqlite:recipes");
		}catch (ClassNotFoundException|SQLException ex) {
			ex.printStackTrace();
		}
		
		return null;
	}

}
