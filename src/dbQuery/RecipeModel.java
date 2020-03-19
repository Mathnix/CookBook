package dbQuery;

import dbUtil.dbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Handles all database action connected to the whole recipe. The table recipe
 * represents some basic information about a recipe. One row consists of the
 * title, a short description the cooking time and the number of serving.
 * 
 * Ingredients and preparation steps can be reached via the recipeID in the
 * table RecipeIngredients and RecipePreparation.
 * 
 * @author lena
 *
 */
public class RecipeModel {

	/**
	 * Get a list of all titles/names of recipes currently saved in the database.
	 * 
	 * @return A list of the titles of the recipes currently saved in the database
	 *         or null if nothing was found.
	 * @throws SQLException
	 */
	public ArrayList<String> getAllRecipeNames() throws SQLException {

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
			return null;
		} finally {
			pr.close();
			rs.close();
			conn.close();
		}
	}

	/**
	 * Get a list of all IDs of recipes currently saved in the database.
	 * 
	 * @return A list of the IDs of the recipes currently saved in the database or
	 *         null if nothing was found.
	 * @throws SQLException
	 */
	public ArrayList<Integer> getAllRecipeIDs() throws SQLException {

		Connection conn = dbConnection.getConnection();
		PreparedStatement pr = null;
		ResultSet rs = null;

		ArrayList<Integer> recipeIDs = new ArrayList<Integer>();

		String sql = "SELECT RecipeID FROM Recipe";
		try {
			pr = conn.prepareStatement(sql);

			rs = pr.executeQuery();
			while (rs.next()) {
				recipeIDs.add(rs.getInt("RecipeID"));
			}
			conn.close();
			return recipeIDs;
		} catch (SQLException e) {
			return null;
		} finally {
			pr.close();
			rs.close();
			conn.close();
		}
	}

	/**
	 * Get the title/name of one specific recipe by passing its ID.
	 * 
	 * @param recipeID The database ID of the recipe.
	 * @return The title/name of the recipe with the passed database ID or null if
	 *         non was found.
	 * @throws SQLException
	 */
	public String getRecipeNameByID(int recipeID) throws SQLException {

		Connection conn = dbConnection.getConnection();
		PreparedStatement pr = null;
		ResultSet rs = null;

		String recipeName = new String();

		String sql = "SELECT Title FROM Recipe WHERE RecipeID = ?";
		try {
			pr = conn.prepareStatement(sql);
			pr.setInt(1, recipeID);

			rs = pr.executeQuery();
			while (rs.next()) {
				recipeName = rs.getString("Title");
			}
			conn.close();
			return recipeName;
		} catch (SQLException e) {
			return null;
		} finally {
			pr.close();
			rs.close();
			conn.close();
		}
	}

	/**
	 * Get the ID of a recipe by passing its name/title.
	 * 
	 * @param title The title/name of the recipe.
	 * @return The ID corresponding to the passed name in the database as int or -1
	 *         if non was found.
	 * @throws SQLException
	 */
	public int getRecipeIdByTitle(String title) throws SQLException {

		Connection conn = dbConnection.getConnection();
		PreparedStatement pr = null;
		ResultSet rs = null;

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
	 * Get the short description of a recipe by passing its database ID.
	 * 
	 * @param recipeID The database ID of the recipe.
	 * @return The short description of the recipe as string or null if nothing was
	 *         found.
	 * @throws SQLException
	 */
	public String getRecipeDescriptionByID(int recipeID) throws SQLException {

		Connection conn = dbConnection.getConnection();
		PreparedStatement pr = null;
		ResultSet rs = null;

		String recipeDescription = new String();

		String sql = "SELECT Description FROM Recipe WHERE RecipeID = ?";
		try {
			pr = conn.prepareStatement(sql);
			pr.setInt(1, recipeID);

			rs = pr.executeQuery();
			while (rs.next()) {
				recipeDescription = rs.getString("Description");
			}
			conn.close();
			return recipeDescription;
		} catch (SQLException e) {
			return null;
		} finally {
			pr.close();
			rs.close();
			conn.close();
		}
	}

	/**
	 * Get the cooking time of a recipe by passing its database ID.
	 * 
	 * @param recipeID The database ID of the recipe
	 * @return The cooking time of the recipe as string or null if nothing was
	 *         found.
	 * @throws SQLException
	 */
	public String getRecipeCookTimeByID(int recipeID) throws SQLException {

		Connection conn = dbConnection.getConnection();
		PreparedStatement pr = null;
		ResultSet rs = null;

		String recipeCookTime = new String();

		String sql = "SELECT CookTime FROM Recipe WHERE RecipeID = ?";
		try {
			pr = conn.prepareStatement(sql);
			pr.setInt(1, recipeID);

			rs = pr.executeQuery();
			while (rs.next()) {
				recipeCookTime = rs.getString("CookTime");
			}
			conn.close();
			return recipeCookTime;
		} catch (SQLException e) {
			return null;
		} finally {
			pr.close();
			rs.close();
			conn.close();
		}
	}

	/**
	 * Get the number of servings of the recipe by passing its database ID.
	 * 
	 * @param recipeID The database ID of the recipe
	 * @return The number of servings the recipe serves or -1 if nothing was found.
	 * @throws SQLException
	 */
	public int getRecipeNumberOfServingsByID(int recipeID) throws SQLException {

		Connection conn = dbConnection.getConnection();
		PreparedStatement pr = null;
		ResultSet rs = null;

		int recipeNumberOfServings = -1;

		String sql = "SELECT NumberOfServings FROM Recipe WHERE RecipeID = ?";
		try {
			pr = conn.prepareStatement(sql);
			pr.setInt(1, recipeID);

			rs = pr.executeQuery();
			while (rs.next()) {
				recipeNumberOfServings = rs.getInt("NumberOfServings");
			}
			conn.close();
			return recipeNumberOfServings;
		} catch (SQLException e) {
			return recipeNumberOfServings;
		} finally {
			pr.close();
			rs.close();
			conn.close();
		}
	}

	/**
	 * Inserts a new raw recipe in the recipe database that only consists of title,
	 * short description, cooking time and the number of servings.
	 * 
	 * @param title            The name/title of the recipe.
	 * @param desc             A short description about the recipe.
	 * @param cookingTime      The cooking time of the recipe represented as String.
	 * @param numberOfServings The number of servings the recipe is meant for.
	 * @return The ID of the newly inserted recipe or -1 if the insertion failed.
	 * @throws SQLException
	 */
	public int insertNewRawRecipe(String title, String desc, String cookingTime, int numberOfServings)
			throws SQLException {

		Connection conn = dbConnection.getConnection();
		PreparedStatement pr = null;

		int rs = -1;
		int id = -1;

		String sql = "INSERT INTO Recipe (Title, Description, CookTime,NumberOfServings) " + "SELECT ?, ?, ?, ? "
				+ " WHERE NOT EXISTS (SELECT * FROM Recipe WHERE "
				+ "Title = ? AND Description = ? AND CookTime=? AND NumberOfServings = ?)";

		try {
			pr = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			pr.setString(1, title);
			pr.setString(2, desc);
			pr.setString(3, cookingTime);
			pr.setInt(4, numberOfServings);

			pr.setString(5, title);
			pr.setString(6, desc);
			pr.setString(7, cookingTime);
			pr.setInt(8, numberOfServings);

			rs = pr.executeUpdate();

			// New INSERT
			if (rs > 0) {

				try (ResultSet generatedKeys = pr.getGeneratedKeys()) {
					generatedKeys.next();
					id = generatedKeys.getInt(1);
					generatedKeys.close();
				} catch (SQLException e) {
					return id;
				}

			}

			// Already in table
			else {
				id = getRecipeIdByTitle(title);
				return id;
			}
		} finally {
			pr.close();
			conn.close();
		}

		return id;
	}

}
