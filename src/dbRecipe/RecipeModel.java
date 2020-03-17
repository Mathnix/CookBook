package dbRecipe;

import dbUtil.dbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Handles all database action connected to the whole recipe.
 * 
 * @author lena
 *
 */
public class RecipeModel {

	/**
	 * Get a list of all titles/names of recipes currently saved in the database.
	 * 
	 * @return A list of the titles of the recipes currently saved in the database
	 * @throws SQLException
	 */
	public ArrayList<String> getAllRecipes() throws SQLException {

		Connection conn = dbConnection.getConnection();
		PreparedStatement pr = null;
		ResultSet rs = null;

		ArrayList<String> recipes = new ArrayList<String>();

		String sql = "SELECT Title FROM Recipe";
		try {
			pr = conn.prepareStatement(sql);

			rs = pr.executeQuery();
			while (rs.next()) {
				recipes.add(rs.getString("Title"));
			}
			conn.close();
			return recipes;
		} catch (SQLException e) {
			return recipes;
		} finally {
			pr.close();
			rs.close();
			conn.close();
		}
	}

	/**
	 * Get the title/name of one specific recipe by passing its ID.
	 * 
	 * @param id The database ID of the recipe.
	 * @return The title/name of the recipe with the passed database ID.
	 * @throws SQLException
	 */
	public String getRecipeNameByID(int id) throws SQLException {

		Connection conn = dbConnection.getConnection();
		PreparedStatement pr = null;
		ResultSet rs = null;

		String recipeName = new String();

		String sql = "SELECT Title FROM Recipe WHERE RecipeID = ?";
		try {
			pr = conn.prepareStatement(sql);
			pr.setInt(1, id);

			rs = pr.executeQuery();
			while (rs.next()) {
				recipeName = rs.getString("Title");
			}
			conn.close();
			return recipeName;
		} catch (SQLException e) {
			return recipeName;
		} finally {
			pr.close();
			rs.close();
			conn.close();
		}
	}

	/**
	 * Get the ID of a recipe by passing its ID.
	 * 
	 * @param title The title/name of the recipe.
	 * @return The ID corresponding to the passed ID in the database.
	 * @throws SQLException
	 */
	public int getRecipeIdByTitle(String title) throws SQLException {

		Connection conn = dbConnection.getConnection();
		PreparedStatement pr = null;
		ResultSet rs = null;

		// TODO: Better representation of invalid ID.
		int recipeId = -1;

		String sql = "SELECT RecipeId FROM Recipe WHERE Title = ?";
		try {
			pr = conn.prepareStatement(sql);
			pr.setString(1, title);

			rs = pr.executeQuery();
			while (rs.next()) {
				recipeId = rs.getInt("RecipeId");
			}
			conn.close();
			return recipeId;
		} catch (SQLException e) {
			return recipeId;
		} finally {
			pr.close();
			rs.close();
			conn.close();
		}
	}

	/**
	 * Get a list of all ingredients that are used in one recipe. To do so it querys
	 * an in-between table "RecipeIngredient" that connects Ingredients and recipes.
	 * 
	 * @param id The ID of the recipe.
	 * @return A list of all ingredients that are used in this recipe.
	 * @throws SQLException
	 */
	public ArrayList<String> getAllIngredientsByRecipeId(int id) throws SQLException {

		Connection conn = dbConnection.getConnection();
		PreparedStatement pr = null;
		ResultSet rs = null;

		ArrayList<String> recipeIngredients = new ArrayList<String>();

		String sql = "SELECT Ingredients.Name Name FROM Ingredients INNER JOIN"
				+ "(SELECT RecipeIngredient.IngredientID id FROM RecipeIngredient WHERE RecipeId =?)res "
				+ "ON Ingredients.IngredientId = res.id";
		try {
			pr = conn.prepareStatement(sql);
			pr.setInt(1, id);

			rs = pr.executeQuery();
			while (rs.next()) {
				recipeIngredients.add(rs.getString("Name"));
			}
			conn.close();
			return recipeIngredients;
		} catch (SQLException e) {
			return recipeIngredients;
		} finally {
			pr.close();
			rs.close();
			conn.close();
		}
	}

	/**
	 * Get all preparation steps that are performed in one recipe. They are
	 * represented as key/value pairs via the step number and the description what
	 * to do.
	 * 
	 * @param id The ID of the recipe.
	 * @return All prepraration steps that need to be performes during the cooking
	 *         process represented via HashMap (key/value pair)
	 * @throws SQLException
	 */
	public HashMap<Integer, String> getAllPreparationStepsRecipeId(int id) throws SQLException {

		Connection conn = dbConnection.getConnection();
		PreparedStatement pr = null;
		ResultSet rs = null;

		HashMap<Integer, String> recipePreparation = new HashMap<Integer, String>();

		String sql = "SELECT PreparationStep.StepNumber StepNumber, PreparationStep.Description Description "
				+ "FROM PreparationStep " + "INNER JOIN( SELECT RecipePreparation.RecipePreparationID id "
				+ "FROM RecipePreparation WHERE RecipeId =?) res " + "ON PreparationStep.PreparationStepID = res.id";
		try {
			pr = conn.prepareStatement(sql);
			pr.setInt(1, id);

			rs = pr.executeQuery();
			while (rs.next()) {
				recipePreparation.put(rs.getInt("StepNumber"), rs.getString("Description"));
			}
			conn.close();
			return recipePreparation;
		} catch (SQLException e) {
			return recipePreparation;
		} finally {
			pr.close();
			rs.close();
			conn.close();
		}
	}

	// TODO: Delete Recipe -> Need to adjust References in in-between tables.
	// TODO: Update/Change Recipe -> Need to adjust References in in-between tables.
	// TODO: Insert whole Recipe -> needs update all in-between tables.

}
