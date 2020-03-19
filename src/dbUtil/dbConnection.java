package dbUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Handles the connection the the database. In this case it uses a sqlite file
 * with name "recipes".
 * 
 * @author lena
 *
 */
public class dbConnection {

	/**
	 * Establishes a connection to the database "recipes".
	 * 
	 * @return A connection/session with the database.
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException {

		try {
			Class.forName("org.sqlite.JDBC");

			return DriverManager.getConnection("jdbc:sqlite:recipes");
		} catch (ClassNotFoundException | SQLException ex) {
			ex.printStackTrace();
		}

		return null;
	}
}
