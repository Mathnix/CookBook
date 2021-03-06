package dbQuery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dbUtil.dbConnection;

/**
 * Handles all database actions affecting the preparation(steps) table. One
 * preparation step consists of its step number saved as int and a description,
 * saved as string.
 * 
 * @author lena
 *
 */
public class PreparationModel {

	/**
	 * Get a preparation step/description passing its database ID.
	 * 
	 * @param preparationStepID The ID of the preparation step in the database.
	 * @return The text of the preparation step that describes what to do in this
	 *         step or null if no preparation step was found.
	 * @throws SQLException
	 */
	public String getPrepDescriptionByID(int preparationStepID) throws SQLException {

		Connection conn = dbConnection.getConnection();
		PreparedStatement pr = null;
		ResultSet rs = null;

		String description = new String();

		String sql = "SELECT Description FROM PreparationStep WHERE PreparationStepID = ?";
		try {
			pr = conn.prepareStatement(sql);
			pr.setInt(1, preparationStepID);

			rs = pr.executeQuery();
			while (rs.next()) {
				description = rs.getString("Description");
			}
			conn.close();
			return description;
		} catch (SQLException e) {
			return null;
		} finally {
			pr.close();
			rs.close();
			conn.close();
		}
	}

	/**
	 * Get the number of a preparaton step by passing its database ID.
	 * 
	 * @param preparationStepID The ID of the preparation step used in the database.
	 * @return The number of the respective step, thus when to perform this step in
	 *         the cooking process or -1 if no preparation step was found.
	 * @throws SQLException
	 */
	public int getPrepStepNumberByID(int preparationStepID) throws SQLException {

		Connection conn = dbConnection.getConnection();
		PreparedStatement pr = null;
		ResultSet rs = null;

		int stepNumber = -1;

		String sql = "SELECT StepNumber FROM PreparationStep WHERE PreparationStepID = ?";
		try {
			pr = conn.prepareStatement(sql);
			pr.setInt(1, preparationStepID);

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
	 * Get the ID of a preparation step by passing its description.
	 * 
	 * @param description The text of the description.
	 * @return The ID of the corresponding preparation step as int or -1 if non was
	 *         found.
	 * @throws SQLException
	 */
	public int getPreparationIDByDescription(String description) throws SQLException {

		// TODO: What happens with identical descriptions?
		Connection conn = dbConnection.getConnection();
		PreparedStatement pr = null;
		ResultSet rs = null;

		int ingredientId = -1;

		String sql = "SELECT PreparationStepID FROM PreparationStep WHERE Description = ?";
		try {
			pr = conn.prepareStatement(sql);
			pr.setString(1, description);

			rs = pr.executeQuery();
			if (rs.next()) {
				ingredientId = rs.getInt("PreparationStepID");
			}
			conn.close();
			return ingredientId;
		} catch (SQLException e) {
			return ingredientId;
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
	 * @param description Text that describes what to do in this preparation step.
	 * @param stepNumber  The number of this preparation step within the cooking
	 *                    process.
	 * @return The database ID of the inserted preparation step as int or -1 if the
	 *         insertaion process failed.
	 * @throws SQLException
	 */
	public int insertPreparationStep(String description, int stepNumber) throws SQLException {

		Connection conn = dbConnection.getConnection();
		PreparedStatement pr = null;

		int rs = -1;
		int preparationId = -1;

		String sql = "INSERT INTO PreparationStep (Description, StepNumber) "
				+ "SELECT ?,? WHERE NOT EXISTS(SELECT * FROM PreparationStep WHERE "
				+ "Description=? AND StepNumber=?)";
		try {
			pr = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			pr.setString(1, description);
			pr.setInt(2, stepNumber);

			pr.setString(3, description);
			pr.setInt(4, stepNumber);

			rs = pr.executeUpdate();

			// New INSERT
			if (rs > 0) {

				try (ResultSet generatedKeys = pr.getGeneratedKeys()) {
					generatedKeys.next();
					preparationId = generatedKeys.getInt(1);
					generatedKeys.close();
				}

			}

			// Item already in table
			if (rs == 0) {
				preparationId = getPreparationIDByDescription(description);
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
}
