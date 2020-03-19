package dbQuery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dbUtil.dbConnection;

/**
 * Handles all database actions that affect the measurements table. The
 * measurement table contains all measurements used in a recipe as string.
 * Examples for measurements would be "g", "kg", "l", "ml" but also "tbsp",
 * "pcs". Since a measurement is saved as string you can set what ever
 * measurement unit you want.
 * 
 * @author lena
 *
 */
public class MeasurementModel {

	/**
	 * Get the ID of a measurement by passing its name.
	 * 
	 * @param UnitName The name of the measurement (for example tbsp., l, kg) as
	 *                 string.
	 * @return The ID of the measurement in the database as int or -1 if no
	 *         measurement was found.
	 * @throws SQLException
	 */
	public int getMeasurementIDByName(String UnitName) throws SQLException {

		Connection conn = dbConnection.getConnection();
		PreparedStatement pr = null;
		ResultSet rs = null;

		int measurementId = -1;

		String sql = "SELECT MeasurementID FROM Measurements WHERE Unit = ?";
		try {
			pr = conn.prepareStatement(sql);
			pr.setString(1, UnitName);

			rs = pr.executeQuery();
			if (rs.next()) {
				measurementId = rs.getInt("MeasurementID");
			}
			conn.close();
			return measurementId;
		} catch (SQLException e) {
			return measurementId;
		} finally {
			pr.close();
			rs.close();
			conn.close();
		}
	}

	/**
	 * Get the name of a measurement unit by passing its database ID.
	 * 
	 * @param measurementID The ID of the measurement in the database.
	 * @return The name of the measurement unit that corresponds to the ID in the
	 *         database as string or null if no measurement was found.
	 * @throws SQLException
	 */
	public String getMeasurementUnitNameByID(int measurementID) throws SQLException {

		Connection conn = dbConnection.getConnection();
		PreparedStatement pr = null;
		ResultSet rs = null;

		String measurementUnitName = new String();

		String sql = "SELECT Unit FROM Measurements WHERE MeasurementID = ?";
		try {
			pr = conn.prepareStatement(sql);
			pr.setInt(1, measurementID);

			rs = pr.executeQuery();
			while (rs.next()) {
				measurementUnitName = rs.getString("Unit");
			}
			conn.close();
			return measurementUnitName;
		} catch (SQLException e) {
			return null;
		} finally {
			pr.close();
			rs.close();
			conn.close();
		}
	}

	/**
	 * Insert a new measurement unit in the database by passing its name.
	 * 
	 * 
	 * @param UnitName The name of the measurement unit to insert. This could be for
	 *                 example tbsp., l, kg.
	 * @return The ID of the measurement in the database as int (newly inserted or
	 *         the corresponding ID if the element was already in the table) or -1
	 *         if the insertion failed.
	 * @throws SQLException
	 */
	public int insertMeasurementUnit(String UnitName) throws SQLException {

		Connection conn = dbConnection.getConnection();
		PreparedStatement pr = null;

		int rs = -1;
		int measurementId = -1;

		String sql = "INSERT INTO Measurements (Unit) " + "SELECT ? "
				+ "WHERE NOT EXISTS(SELECT * FROM Measurements WHERE Unit = ?)";
		try {
			pr = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			pr.setString(1, UnitName);
			pr.setString(2, UnitName);

			rs = pr.executeUpdate();

			// New INSERT
			if (rs > 0) {

				try (ResultSet generatedKeys = pr.getGeneratedKeys()) {
					generatedKeys.next();
					measurementId = generatedKeys.getInt(1);
					generatedKeys.close();
				}

			}

			// Item already in table
			if (rs == 0) {
				measurementId = getMeasurementIDByName(UnitName);
			}

			conn.close();
			return measurementId;
		} catch (SQLException e) {
			return measurementId;
		} finally {
			pr.close();
			conn.close();
		}
	}

}
