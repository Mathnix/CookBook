package dbPreparation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dbUtil.dbConnection;

/**
 * Handles all database actions affecting the preparation(steps).
 * 
 * @author lena
 *
 */
public class PreparationModel {

	/**
	 * Get a preparation step/description passing its database ID.
	 * 
	 * @param id The ID of the preparation step in the database.
	 * @return The text of the preparation step that describes what to do in this
	 *         step.
	 * @throws SQLException
	 */
	public String getPrepDescriptionByID(int id) throws SQLException {

		Connection conn = dbConnection.getConnection();
		PreparedStatement pr = null;
		ResultSet rs = null;

		String description = new String();

		String sql = "SELECT Description FROM PreparationStep WHERE PreparationStepID = ?";
		try {
			pr = conn.prepareStatement(sql);
			pr.setInt(1, id);

			rs = pr.executeQuery();
			while (rs.next()) {
				description = rs.getString("Description");
			}
			conn.close();
			return description;
		} catch (SQLException e) {
			return description;
		} finally {
			pr.close();
			rs.close();
			conn.close();
		}
	}

	/**
	 * Get the number of a preparaton step by passing its database ID.
	 * 
	 * @param id The ID of the preparation step used in the database.
	 * @return The number of the respective step, thus when to perform this step in
	 *         the cooking process.
	 * @throws SQLException
	 */
	public int getPrepStepNumberByID(int id) throws SQLException {

		Connection conn = dbConnection.getConnection();
		PreparedStatement pr = null;
		ResultSet rs = null;

		// TODO: Better representation for invalid ID;
		int stepNumber = -1;

		String sql = "SELECT StepNumber FROM PreparationStep WHERE PreparationStepID = ?";
		try {
			pr = conn.prepareStatement(sql);
			pr.setInt(1, id);

			rs = pr.executeQuery();
			while (rs.next()) {
				stepNumber = rs.getInt("StepNumber");
			}
			conn.close();
			return stepNumber;
		} catch (SQLException e) {
			return stepNumber;
		} finally {
			pr.close();
			rs.close();
			conn.close();
		}
	}

	/**
	 * Insert a new preparation step in the database. A preparation step is a pair
	 * of information. It contains the step number, that is the number that tells,
	 * when the action has to be performed in the cooking process and the
	 * description what to do.
	 *
	 * @param description Text that describes what to to in this preparation step.
	 * @param stepNumber  The number of this preparation step within the cooking
	 *                    process.
	 * @return The database ID of the inserted preparation step.
	 * @throws SQLException
	 */
	public int insertPreparationStep(String description, int stepNumber) throws SQLException {

		Connection conn = dbConnection.getConnection();
		PreparedStatement pr = null;

		// TODO: Better representation for invalid ID and success of SQL statement.
		int rs = -1;
		int preparationId = -1;

		String sql = "INSERT INTO PreparationStep (Description, StepNumber) " + "VALUES (?,?) ";
		try {
			pr = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			pr.setString(1, description);
			pr.setInt(2, stepNumber);

			rs = pr.executeUpdate();

			// New INSERT
			if (rs > 0) {

				try (ResultSet generatedKeys = pr.getGeneratedKeys()) {
					generatedKeys.next();
					preparationId = generatedKeys.getInt(1);
					generatedKeys.close();
				}

			}

			conn.close();
			return preparationId;
		} catch (SQLException e) {
			return preparationId;
		} finally {
			pr.close();
			conn.close();
		}
	}
	// TODO: Delete Prepraration step -> Need to adjust References in in-between
	// tables.
	// TODO: Update/Change Preparation step -> Need to adjust References in
	// in-between tables.

}
