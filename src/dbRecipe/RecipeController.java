package dbRecipe;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Handles all database action connected to the whole recipe.
 * 
 * @author lena
 *
 */
public class RecipeController {

	public RecipeModel recipeModel = new RecipeModel();

	public ArrayList<String> recipes;
	public String recipeName;
	public int recipeId;
	public ArrayList<String> recipeIngredients;
	HashMap<Integer, String> recipePreparation;

	/**
	 * Get a list of all titles/names of recipes currently saved in the database.
	 * 
	 * @return A list of the titles of the recipes currently saved in the database
	 */
	public ArrayList<String> getAllRecipesOfDB() {

		try {
			recipes = recipeModel.getAllRecipes();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return recipes;
	}

	/**
	 * Get the title/name of one specific recipe by passing its ID.
	 * 
	 * @param id The database ID of the recipe.
	 * @return The title/name of the recipe with the passed database ID.
	 */
	public String getRecipeNameById(int id) {

		try {
			recipeName = recipeModel.getRecipeNameByID(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return recipeName;

	}

	/**
	 * Get the ID of a recipe by passing its ID.
	 * 
	 * @param title The title/name of the recipe.
	 * @return The ID corresponding to the passed ID in the database.
	 */
	public int getRecipeIdByTitle(String title) {

		try {
			recipeId = recipeModel.getRecipeIdByTitle(title);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return recipeId;
	}

	/**
	 * Get a list of all ingredients that are used in one recipe. To do so it querys
	 * an in-between table "RecipeIngredient" that connects Ingredients and recipes.
	 * 
	 * @param id The ID of the recipe.
	 * @return A list of all ingredients that are used in this recipe.
	 */
	public ArrayList<String> getAllIngredientsByRecipeId(int id) {

		try {
			recipeIngredients = recipeModel.getAllIngredientsByRecipeId(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return recipeIngredients;
	}

	/**
	 * Get all preparation steps that are performed in one recipe. They are
	 * represented as key/value pairs via the step number and the description what
	 * to do.
	 * 
	 * @param id The ID of the recipe.
	 * @return All prepraration steps that need to be performes during the cooking
	 *         process represented via HashMap (key/value pair)
	 */
	public HashMap<Integer, String> getAllPreparationStepsRecipeId(int id) {

		try {
			recipePreparation = recipeModel.getAllPreparationStepsRecipeId(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return recipePreparation;

	}

	// TODO: See model
}
