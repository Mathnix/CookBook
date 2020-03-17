package dbRecipe;

//TODO: Concrete GUI.

/**
 * Test Status for all actions on the database affecting the whole recipe.
 * 
 * In this version: Only prints the return values!
 * 
 * @author lena
 *
 */
public class RecipeView {

	public RecipeController recipeController = new RecipeController();

	public void printAllRecipes() {

		if (recipeController.getAllRecipesOfDB().isEmpty()) {
			System.out.println("No Recipes in DB.\n");
		} else {
			System.out.println("The titles of all recipes are:");
			System.out.println(recipeController.getAllRecipesOfDB() + "\n");
		}
	}

	public void printRecipeNameByID(int recipeId) {

		if (recipeController.getRecipeNameById(recipeId).isEmpty()) {
			System.out.println("No Rrecipe with ID " + recipeId + " found.\n");
		} else {
			System.out.println("Title of recipe with ID " + recipeId + ":");
			System.out.println(recipeController.getRecipeNameById(recipeId) + "\n");
		}
	}

	public void printRecipeIDByTitle(String recipeTitle) {

		if (recipeController.getRecipeIdByTitle(recipeTitle) == -1) {
			System.out.println("No Recipe with title " + recipeTitle + " found.\n ");
		} else {
			System.out.println("The recipe with title " + recipeTitle + " has the follwoing ID:");
			System.out.println(recipeController.getRecipeIdByTitle(recipeTitle) + "\n");
		}
	}

	public void printAllIngredientsByRecipeID(int recipeId) {

		if (recipeController.getAllIngredientsByRecipeId(recipeId).isEmpty()) {
			System.out.println("No recipe/ingredients to ID " + recipeId + " found. \n");
		} else {
			System.out.println("The recipe needs the following ingredients: ");
			System.out.println(recipeController.getAllIngredientsByRecipeId(recipeId) + "\n");
		}
	}

	public void printAllPreparationStepsByRecipeID(int recipeId) {

		if (recipeController.getAllPreparationStepsRecipeId(recipeId).isEmpty()) {
			System.out.println("No recipe/preparationsteps to ID " + recipeId + " found.\n");
		} else {
			System.out.println("The following preparation steps need to be done:");
			System.out.println(recipeController.getAllPreparationStepsRecipeId(recipeId) + "\n");
		}
	}

}
