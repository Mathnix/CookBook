package dbRecipe;

import dbUtil.dbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class RecipeModel {

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
	
	public ArrayList<String> getAllIngredientsByRecipeId(int id) throws SQLException {

		Connection conn = dbConnection.getConnection();
		PreparedStatement pr = null;
		ResultSet rs = null;

		ArrayList<String> recipeIngredients = new ArrayList<String>();

		String sql = "SELECT Ingredients.Name Name FROM Ingredients INNER JOIN"+
					 "(SELECT RecipeIngredient.IngredientID id FROM RecipeIngredient WHERE RecipeId =?)res "+
					 "ON Ingredients.IngredientId = res.id";
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
	
	public HashMap<Integer, String> getAllPreparationStepsRecipeId(int id) throws SQLException {

		Connection conn = dbConnection.getConnection();
		PreparedStatement pr = null;
		ResultSet rs = null;
		
		HashMap<Integer, String> recipePreparation = new HashMap<Integer,String>();

		String sql = "SELECT PreparationStep.StepNumber StepNumber, PreparationStep.Description Description "+
					 "FROM PreparationStep "+
					 "INNER JOIN( SELECT RecipePreparation.RecipePreparationID id "+
					 "FROM RecipePreparation WHERE RecipeId =?) res "+
					 "ON PreparationStep.PreparationStepID = res.id";
		try {
			pr = conn.prepareStatement(sql);
			pr.setInt(1, id);

			rs = pr.executeQuery();
			while (rs.next()) {
				recipePreparation.put(rs.getInt("StepNumber"),rs.getString("Description"));
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
	
	
	
	
	

}
