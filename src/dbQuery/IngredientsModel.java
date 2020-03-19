package dbQuery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dbUtil.dbConnection;

/**
 * Handles als database actions that affect the ingredients table. An ingredient
 * is represented via its name as string.
 * 
 * 
 * @author lena
 *
 */
public class IngredientsModel {

	/**
	 * Get the database ID of an ingredient by passing the name.
	 * 
	 * @param ingredientName The name of the ingredient.
	 * @return The ID in the database of this ingredient as int or -1 if no
	 *         ingredient was found.
	 * @throws SQLException
	 */
	public int getIngredientIDByName(String ingredientName) throws SQLException {

		Connection conn = dbConnection.getConnection();
		PreparedStatement pr = null;
		ResultSet rs = null;

		int ingredientId = -1;

		String sql = "SELECT IngredientID FROM Ingredients WHERE name = ?";
		try {
			pr = conn.prepareStatement(sql);
			pr.setString(1, ingredientName);

			rs = pr.executeQuery();
			if (rs.next()) {
				ingredientId = rs.getInt("IngredientID");
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
	 * Get the name of an ingredient by passing its database ID.
	 * 
	 * @param ingredientID The ID of the ingredient used in the database.
	 * @return The name of the ingredient as string or null if no ingredient was
	 *         found.
	 * @throws SQLException
	 */
	public String getIngredientNameByID(int ingredientID) throws SQLException {

		Connection conn = dbConnection.getConnection();
		PreparedStatement pr = null;
		ResultSet rs = null;

		String ingredientName = new String();

		String sql = "SELECT Name FROM Ingredients WHERE IngredientID = ?";
		try {
			pr = conn.prepareStatement(sql);
			pr.setInt(1, ingredientID);

			rs = pr.executeQuery();
			while (rs.next()) {
				ingredientName = rs.getString("Name");
			}
			conn.close();
			return ingredientName;
		} catch (SQLException e) {
			return null;
		} finally {
			pr.close();
			rs.close();
			conn.close();
		}
	}

	/**
	 * Insert a new ingredient in the database.
	 * 
	 * @param ingredientName The name of the ingredient to insert.
	 * @return The ID of the ingredient (newly inserted or the corresponding ID if
	 *         the element was already in the table) or -1 if the insertion failed.
	 * @throws SQLException
	 */
	public int insertIngredient(String ingredientName) throws SQLException {

		Connection conn = dbConnection.getConnection();
		PreparedStatement pr = null;

		int rs = -1;
		int ingredientId = -1;

		String sql = "INSERT INTO Ingredients (Name) " + "SELECT ? "
				+ "WHERE NOT EXISTS(SELECT * FROM Ingredients WHERE Name = ?)";
		try {
			pr = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			pr.setString(1, ingredientName);
			pr.setString(2, ingredientName);

			rs = pr.executeUpdate();

			// New INSERT
			if (rs > 0) {

				try (ResultSet generatedKeys = pr.getGeneratedKeys()) {
					generatedKeys.next();
					ingredientId = generatedKeys.getInt(1);
					generatedKeys.close();
				}

			}

			// Item already in table
			if (rs == 0) {
				ingredientId = getIngredientIDByName(ingredientName);
			}

			conn.close();
			return ingredientId;
		} catch (SQLException e) {
			return ingredientId;
		} finally {
			pr.close();
			conn.close();
		}
	}

}
