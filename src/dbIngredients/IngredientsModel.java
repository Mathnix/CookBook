package dbIngredients;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dbUtil.dbConnection;

/**
 * Handles als database actions that affect the ingredients.
 * 
 * 
 * @author lena
 *
 */
public class IngredientsModel {

	/**
	 * Get the database ID of an ingredient by passing the name.
	 * 
	 * @param name The name of the ingredient.
	 * @return The ID in the database of this ingredient.
	 * @throws SQLException
	 */
	public int getIngredientIDByName(String name) throws SQLException {

		Connection conn = dbConnection.getConnection();
		PreparedStatement pr = null;
		ResultSet rs = null;

		// TODO: Better representation of invalid ID
		int ingredientId = -1;

		String sql = "SELECT IngredientID FROM Ingredients WHERE name = ?";
		try {
			pr = conn.prepareStatement(sql);
			pr.setString(1, name);

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
	 * @param id The ID of the ingredient used in the database.
	 * @return The name of the ingredient.
	 * @throws SQLException
	 */
	public String getIngredientNameByID(int id) throws SQLException {

		Connection conn = dbConnection.getConnection();
		PreparedStatement pr = null;
		ResultSet rs = null;

		String ingredientName = new String();

		String sql = "SELECT Name FROM Ingredients WHERE IngredientID = ?";
		try {
			pr = conn.prepareStatement(sql);
			pr.setInt(1, id);

			rs = pr.executeQuery();
			while (rs.next()) {
				ingredientName = rs.getString("Name");
			}
			conn.close();
			return ingredientName;
		} catch (SQLException e) {
			return ingredientName;
		} finally {
			pr.close();
			rs.close();
			conn.close();
		}
	}

	/**
	 * Insert a new ingredient in the database. This method returns the ID of the
	 * newly inserted ingredient. If the ingredient is already in the database it
	 * returns its ID.
	 * 
	 * @param name The name of the ingredient to insert.
	 * @return The ID of the ingredient (newly inserted or already in database).
	 * @throws SQLException
	 */
	public int insertIngredient(String name) throws SQLException {

		Connection conn = dbConnection.getConnection();
		PreparedStatement pr = null;

		// TODO: Better representation for invalid ID and the success of the SQL
		// Statement.
		int rs = -1;
		int ingredientId = -1;

		String sql = "INSERT INTO Ingredients (Name) " + "SELECT ? "
				+ "WHERE NOT EXISTS(SELECT * FROM Ingredients WHERE Name = ?)";
		try {
			pr = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			pr.setString(1, name);
			pr.setString(2, name);

			rs = pr.executeUpdate();

			// New INSERT
			if (rs > 0) {

				try (ResultSet generatedKeys = pr.getGeneratedKeys()) {
					generatedKeys.next();
					ingredientId = generatedKeys.getInt(1);
					generatedKeys.close();
				}

			}

			// Item already in Table
			if (rs == 0) {
				ingredientId = getIngredientIDByName(name);
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

	// TODO: Delete Ingredient -> Need to adjust References in in-between tables.
	// TODO: Update/Change Ingredient -> Need to adjust References in in-between tables.

}
