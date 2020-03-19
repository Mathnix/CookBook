package dbQuery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dbUtil.dbConnection;
import recipeClasses.Ingredient;
import recipeClasses.Preparation;
import recipeClasses.Recipe;

/**
 * Handles all complex querys that affects multiple tables in the database, as
 * inserting a whole recipe or deleting.
 * 
 * @author lena
 *
 */
public class QueryModel {

	/**
	 * Insert a whole recipe in the databse by passing a Recipe object
	 * ({@link recipeClasses.Recipe}) that contains all information of the recipe,
	 * including a list of ingredients and all preparation steps.
	 * 
	 * @param recipe A Recipe object that represents a whole recipe
	 *               ({@linkrecipeClasses.Recipe})
	 * @return The ID of the inserted recipe (newly inserted or the ID if the
	 *         element was already in the table) or -1 if the insertion failed.
	 * @throws SQLException
	 */
	public int insertWholeRecipe(Recipe recipe) throws SQLException {

		// TODO: Error handling for null and -1 IDs.

		// Insert Title, Description, CookTime, NumberofServings
		RecipeModel recipeModel = new RecipeModel();

		String title = recipe.getTitle();
		String cookingTime = recipe.getCookingTime();
		String desc = recipe.getDescription();
		int numberOfServings = recipe.getNumberOfServings();

		int recipeID = recipeModel.insertNewRawRecipe(title, desc, cookingTime, numberOfServings);

		// Insert Measurements , Ingredients and link table
		ArrayList<Ingredient> ingredients = recipe.getListOfIngredients();

		IngredientsModel ingredientModel = new IngredientsModel();
		MeasurementModel measureModel = new MeasurementModel();
		RecipeIngredientModel recipeIngredientModel = new RecipeIngredientModel();

		for (int i = 0; i < ingredients.size(); i++) {
			Ingredient currIngredient = ingredients.get(i);
			String currName = currIngredient.getName();
			String currMeasure = currIngredient.getMeasure();
			double currAmount = currIngredient.getAmount();

			int currIngredientID = ingredientModel.insertIngredient(currName);
			int currMeasureID = measureModel.insertMeasurementUnit(currMeasure);

			recipeIngredientModel.insert(recipeID, currIngredientID, currMeasureID, currAmount);
		}

		// Insert preparation steps and link table
		PreparationModel prepModel = new PreparationModel();
		RecipePreparationModel recipePrepModel = new RecipePreparationModel();

		Preparation preparation = recipe.getPreparation();

		for (int i = 0; i < preparation.getSize(); i++) {
			int stepNumber = i + 1;
			String currDesc = preparation.getDescription(stepNumber);

			int currPrepID = prepModel.insertPreparationStep(currDesc, stepNumber);

			recipePrepModel.insert(recipeID, currPrepID);

		}

		// Insert picture and link table
		ImageModel imgModel = new ImageModel();
		RecipeImageModel recipeImgModel = new RecipeImageModel();
		String imageFilename = recipe.getImageName();

		int imageID = imgModel.insertImage(imageFilename);
		recipeImgModel.insert(recipeID, imageID);

		return recipeID;

	}

	/**
	 * Get a list of all ingredients that are used in one recipe. To do so it querys
	 * an in-between table "RecipeIngredient" that connects ingredients and recipes.
	 * 
	 * @param recipeID The ID of the recipe.
	 * @return A list of all ingredients that are used in this recipe or null if
	 *         nothing was found. The list contains objects of type Ingredient
	 *         ({@link recipeClasses.Ingredient})
	 * @throws SQLException
	 */
	public ArrayList<Ingredient> getAllIngredientsByRecipeId(int recipeID) throws SQLException {

		// TODO: Error handling for null/-1 objects
		Connection conn = dbConnection.getConnection();
		PreparedStatement pr = null;
		ResultSet rs = null;

		ArrayList<Ingredient> listOfIngredients = new ArrayList<Ingredient>();
		IngredientsModel ingredientModel = new IngredientsModel();
		MeasurementModel measurementModel = new MeasurementModel();

		String sql = "SELECT IngredientID,MeasurementID, Amount FROM " + "RecipeIngredients WHERE RecipeID = ?";
		try {
			pr = conn.prepareStatement(sql);
			pr.setInt(1, recipeID);

			rs = pr.executeQuery();
			while (rs.next()) {
				int ingredientID = rs.getInt("IngredientID");
				int measurementID = rs.getInt("MeasurementID");
				double amount = rs.getDouble("Amount");

				String ingredientName = ingredientModel.getIngredientNameByID(ingredientID);
				String measurementUnit = measurementModel.getMeasurementUnitNameByID(measurementID);

				Ingredient currIngredient = new Ingredient(ingredientName, amount, measurementUnit);
				listOfIngredients.add(currIngredient);
			}
			conn.close();
			return listOfIngredients;
		} catch (SQLException e) {
			return null;
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
	 * @param recipeID The ID of the recipe.
	 * @return All prepraration steps that need to be performed during the cooking
	 *         process represented via a Preparation object
	 *         ({@link recipeClasses.Preparation}) or null if nothing was found.
	 * @throws SQLException
	 */
	public Preparation getAllPreparationStepsRecipeId(int recipeID) throws SQLException {

		Connection conn = dbConnection.getConnection();
		PreparedStatement pr = null;
		ResultSet rs = null;

		Preparation recipePreparation = new Preparation();

		String sql = "SELECT PreparationStep.StepNumber StepNumber, PreparationStep.Description Description "
				+ "FROM PreparationStep " + "INNER JOIN( SELECT RecipePreparation.RecipePreparationID id "
				+ "FROM RecipePreparation WHERE RecipeId =?) res " + "ON PreparationStep.PreparationStepID = res.id";
		try {
			pr = conn.prepareStatement(sql);
			pr.setInt(1, recipeID);

			rs = pr.executeQuery();
			while (rs.next()) {
				recipePreparation.addPreparationStep(rs.getInt("StepNumber"), rs.getString("Description"));
			}
			conn.close();
			return recipePreparation;
		} catch (SQLException e) {
			return null;
		} finally {
			pr.close();
			rs.close();
			conn.close();
		}
	}

	/**
	 * Get a whole Recipe object for one recipe stored in the database by passing
	 * its ID. The Recipe Object that is used to store the recipe is defined in
	 * {@link recipeClasses.Recipe}.
	 * 
	 * @param recipeID The ID of the recipe in the database.
	 * @return A Recipe object that stores the whole recipe or null if non was
	 *         found.
	 * @throws SQLException
	 */
	public Recipe getWholeRecipeByID(int recipeID) throws SQLException {

		// TODO: Error handling for null/-1 objects
		Preparation recipePreparation = new Preparation();
		ArrayList<Ingredient> listOfIngredients = new ArrayList<Ingredient>();

		// Get all preparation steps and ingredients
		recipePreparation = getAllPreparationStepsRecipeId(recipeID);
		listOfIngredients = getAllIngredientsByRecipeId(recipeID);

		// Get all raw recipe data
		RecipeModel recipeModel = new RecipeModel();
		ImageModel imageModel = new ImageModel();

		String title = recipeModel.getRecipeNameByID(recipeID);
		String description = recipeModel.getRecipeDescriptionByID(recipeID);
		String cookingTime = recipeModel.getRecipeCookTimeByID(recipeID);
		int numberOfServings = recipeModel.getRecipeNumberOfServingsByID(recipeID);

		String imageName = imageModel.getImageFilenameByID(recipeID);

		// Assemble the Recipe object
		Recipe recipe = new Recipe();

		recipe.setTitle(title);
		recipe.setDescription(description);
		recipe.setCookingTime(cookingTime);
		recipe.setNumberOfServings(numberOfServings);

		recipe.setListOfIngredients(listOfIngredients);
		recipe.setListOfPreparationSteps(recipePreparation);

		recipe.setImageName(imageName);

		return recipe;
	}

	/**
	 * Checks if a recipe contains a certain ingredient by passing both names.
	 * 
	 * @param ingredientName The name of the ingredient.
	 * @param recipeID       The ID of the recipe
	 * @return True if the recipe contains the specified ingredient and false
	 *         otherwise.
	 * @throws SQLException
	 */
	public boolean recipeContainsIngredientByIngredientAndRecipeName(String ingredientName, int recipeID)
			throws SQLException {

		// TODO: Error handling for null/-1 IDs
		IngredientsModel ingredientsModel = new IngredientsModel();
		RecipeIngredientModel recipeIngredientModel = new RecipeIngredientModel();

		int ingredientId = ingredientsModel.getIngredientIDByName(ingredientName);

		boolean contains = recipeIngredientModel.contains(recipeID, ingredientId);

		return contains;
	}

	/**
	 * Get a list of all recipes that contains one specific ingredient.
	 * 
	 * @param ingredientName Name of the ingredient to search for.
	 * @return A list of all recipes that contain one specific ingredient. Each
	 *         recipe is represented as an Recipe object
	 *         ({@link recipeClasses.Recipe})
	 * @throws SQLException
	 */
	public ArrayList<Recipe> getAllRecipeWithIngredientByIngredientName(String ingredientName) throws SQLException {

		// TODO: Error handlin/empty list.
		ArrayList<Recipe> listOfAllRecipes = new ArrayList<Recipe>();

		ArrayList<Integer> listOfAllRecipeIDs = new ArrayList<Integer>();
		RecipeModel recipeModel = new RecipeModel();

		listOfAllRecipeIDs = recipeModel.getAllRecipeIDs();

		for (int recipeID : listOfAllRecipeIDs) {
			boolean contains = recipeContainsIngredientByIngredientAndRecipeName(ingredientName, recipeID);

			// Recipe relevant
			if (contains) {
				Recipe relevantRecipe = getWholeRecipeByID(recipeID);
				listOfAllRecipes.add(relevantRecipe);
			}
		}

		return listOfAllRecipes;
	}

	/**
	 * Get the list of all recipe that use ALL the ingredients specified in a list.
	 * 
	 * @param listOfIngredients A list of ingredient names that should be used by
	 *                          the recipes.
	 * @return A list of all recipes that use ALL ingredients from the list. Each
	 *         recipe is represented as an Recipe object
	 *         ({@link recipeClasses.Recipe})
	 * @throws SQLException
	 */
	public ArrayList<Recipe> getAllRecipeContainsAllIngredientByIngredientNameList(ArrayList<String> listOfIngredients)
			throws SQLException {

		// TODO: Error handlin/empty list.
		ArrayList<Recipe> listOfAllRecipes = new ArrayList<Recipe>();

		ArrayList<Integer> listOfAllRecipeIDs = new ArrayList<Integer>();
		RecipeModel recipeModel = new RecipeModel();

		listOfAllRecipeIDs = recipeModel.getAllRecipeIDs();

		for (int recipeID : listOfAllRecipeIDs) {
			boolean contains = false;

			for (String ingredientName : listOfIngredients) {
				contains = recipeContainsIngredientByIngredientAndRecipeName(ingredientName, recipeID);

				// Ingredient not used in recipe -> recipe irrelevant
				if (!contains) {
					break;
				}
			}
			// Recipe relevant
			if (contains) {
				Recipe relevantRecipe = getWholeRecipeByID(recipeID);
				listOfAllRecipes.add(relevantRecipe);
			}

		}

		return listOfAllRecipes;
	}

}
