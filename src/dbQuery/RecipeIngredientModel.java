package dbQuery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dbUtil.dbConnection;

/**
 * Handles als actions that affects the link table RecipeIngredients in the
 * database. This table connects a recipe with its ingredients. For this a row
 * consists of the recipeID, the ingredientID, the measurementID and the amount
 * of the ingredient that is used.
 * 
 * @author lena
 *
 */
public class RecipeIngredientModel {

	/**
	 * Insert a new entry in the link table between Recipe and Ingredient.
	 * 
	 * @param recipeID     The ID of the recipe.
	 * @param ingredientID The ID of one ingredient that is used in the recipe.
	 * @param measureID    The ID of one measurement unit that is used in the
	 *                     recipe.
	 * @param amount       The numeric amount of the used ingredient.
	 * @throws SQLException
	 */
	public void insert(int recipeID, int ingredientID, int measureID, double amount) throws SQLException {

		Connection conn = dbConnection.getConnection();
		PreparedStatement pr = null;

		String sql = "INSERT INTO RecipeIngredient (RecipeID, IngredientID, MeasurementID, Amount) "
				+ "SELECT ?,?,?,? WHERE NOT EXISTS(SELECT * FROM RecipeIngredient WHERE "
				+ "RecipeID=? AND IngredientID = ? AND MeasurementID=? AND Amount=?)";

		try {
			pr = conn.prepareStatement(sql);
			pr.setInt(1, recipeID);
			pr.setInt(2, ingredientID);
			pr.setInt(3, measureID);
			pr.setDouble(4, amount);

			pr.setInt(5, recipeID);
			pr.setInt(6, ingredientID);
			pr.setInt(7, measureID);
			pr.setDouble(8, amount);

			pr.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			pr.close();
			conn.close();
		}

	}

	/**
	 * Checks if the link table between recipe and ingredients contains a specified pair of recipe ID and ingredient ID
	 * 
	 * @param recipeID The ID of the recipe.
	 * @param ingredientID The ID of the ingredient.
	 * @return True if there is a entry in the table that contains both values, false otherwise.
	 * @throws SQLException
	 */
	public boolean contains(int recipeID, int ingredientID) throws SQLException {

		Connection conn = dbConnection.getConnection();
		PreparedStatement pr = null;
		ResultSet rs = null;

		String sql = "SELECT * FROM RecipeIngredient WHERE RecipeID = ? AND IngredientID =?";

		try {

			pr = conn.prepareStatement(sql);
			pr.setInt(1, recipeID);
			pr.setInt(2, ingredientID);

			rs = pr.executeQuery();

			if (rs.next()) {
				conn.close();
				return true;
			}
		} catch (Exception e) {
			return false;
		} finally {
			pr.close();
			rs.close();
			conn.close();
		}
		return false;
	}

}
