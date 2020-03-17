package dbMeasurement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dbUtil.dbConnection;

/**
 * Handles all database actions that affect the measurements.
 * 
 * @author lena
 *
 */
public class MeasurementModel {

	/**
	 * Get the ID of a measurement by passing its name.
	 * 
	 * @param name The name of the mearurement (for example tbsp., l, kg) as String.
	 * @return The ID of the measurement in the database.
	 * @throws SQLException
	 */
	public int getMeasurementIDByName(String name) throws SQLException {

		Connection conn = dbConnection.getConnection();
		PreparedStatement pr = null;
		ResultSet rs = null;

		// TODO: Better representation for invalid ID.
		int measurementId = -1;

		String sql = "SELECT MeasurementID FROM Measurements WHERE Unit = ?";
		try {
			pr = conn.prepareStatement(sql);
			pr.setString(1, name);

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
	 * @param id The ID of the measurement in the database.
	 * @return The name of the measurement unit that corresponds to the ID ind the
	 *         database.
	 * @throws SQLException
	 */
	public String getMeasurementUnitNameByID(int id) throws SQLException {

		Connection conn = dbConnection.getConnection();
		PreparedStatement pr = null;
		ResultSet rs = null;

		String measurementUnitName = new String();

		String sql = "SELECT Unit FROM Measurements WHERE MeasurementID = ?";
		try {
			pr = conn.prepareStatement(sql);
			pr.setInt(1, id);

			rs = pr.executeQuery();
			while (rs.next()) {
				measurementUnitName = rs.getString("Unit");
			}
			conn.close();
			return measurementUnitName;
		} catch (SQLException e) {
			return measurementUnitName;
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
	 * @param name The name of the measurement unit to insert. This could be for
	 *             example tbsp., l, kg.
	 * @return The ID of the measurement in the database.
	 * @throws SQLException
	 */
	public int insertMeasurementUnit(String name) throws SQLException {

		Connection conn = dbConnection.getConnection();
		PreparedStatement pr = null;

		// TODO: Better representation of invalid ID and sucess of SQL statement.
		int rs = -1;
		int measurementId = -1;

		String sql = "INSERT INTO Measurements (Unit) " + "SELECT ? "
				+ "WHERE NOT EXISTS(SELECT * FROM Measurements WHERE Unit = ?)";
		try {
			pr = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			pr.setString(1, name);
			pr.setString(2, name);

			rs = pr.executeUpdate();

			// New INSERT
			if (rs > 0) {

				try (ResultSet generatedKeys = pr.getGeneratedKeys()) {
					generatedKeys.next();
					measurementId = generatedKeys.getInt(1);
					generatedKeys.close();
				}

			}

			// Item already in Table
			if (rs == 0) {
				measurementId = getMeasurementIDByName(name);
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

	// TODO: Delete Measurement -> Need to adjust References in in-between tables.
	// TODO: Update/Change Measurement -> Need to adjust References in in-between tables.

}
