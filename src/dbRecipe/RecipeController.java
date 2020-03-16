package dbRecipe;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class RecipeController {

	public RecipeModel recipeModel = new RecipeModel();

	public ArrayList<String> recipes;
	public String recipeName;
	public int recipeId;
	public ArrayList<String> recipeIngredients;
	HashMap<Integer, String> recipePreparation;

	public ArrayList<String> getAllRecipesOfDB() {

		try {
			recipes = recipeModel.getAllRecipes();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return recipes;
	}

	public String getRecipeNameById(int id) {

		try {
			recipeName = recipeModel.getRecipeNameByID(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return recipeName;

	}

	public int getRecipeIdByTitle(String title) {

		try {
			recipeId = recipeModel.getRecipeIdByTitle(title);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return recipeId;
	}

	public ArrayList<String> getAllIngredientsByRecipeId(int id) {

		try {
			recipeIngredients = recipeModel.getAllIngredientsByRecipeId(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return recipeIngredients;
	}

	public HashMap<Integer, String> getAllPreparationStepsRecipeId(int id) {

		try {
			recipePreparation = recipeModel.getAllPreparationStepsRecipeId(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return recipePreparation;

	}

}
