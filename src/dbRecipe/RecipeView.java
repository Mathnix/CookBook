package dbRecipe;

public class RecipeView {

	public RecipeController recipeController = new RecipeController();

	public void printAllRecipes() {
		
		if(recipeController.getAllRecipesOfDB().isEmpty()) {
			System.out.println("No Recipes in DB.\n");
		}
		else{
			System.out.println("The titles of all recipes are:");
		System.out.println(recipeController.getAllRecipesOfDB()+ "\n");
		}
	}

	public void printRecipeNameById(int recipeId) {
		
		if(recipeController.getRecipeNameById(recipeId).isEmpty()) {
			System.out.println("No Rrecipe with ID " + recipeId + " found.\n");
		}
		else {
		System.out.println("Title of recipe with ID " + recipeId + ":");
		System.out.println(recipeController.getRecipeNameById(recipeId)+ "\n");
		}
	}

	public void printRecipeIdByTitle(String recipeTitle) {
		
		if(recipeController.getRecipeIdByTitle(recipeTitle) == -1) {
			System.out.println("No Recipe with title " + recipeTitle + " found.\n ");
		}
		else {
			System.out.println("The recipe with title " + recipeTitle + " has the follwoing ID:");
		System.out.println(recipeController.getRecipeIdByTitle(recipeTitle)+ "\n");
		}
	}

	public void printAllIngredientsByRecipeId(int recipeId) {
		
		if(recipeController.getAllIngredientsByRecipeId(recipeId).isEmpty()) {
			System.out.println("No recipe/ingredients to ID " + recipeId + " found. \n");
		}
		else {
			System.out.println("The recipe needs the following ingredients: ");
		System.out.println(recipeController.getAllIngredientsByRecipeId(recipeId)+ "\n");
		}
	}
	
	public void printAllPreparationStepsByRecipeId(int recipeId) {
		
		if(recipeController.getAllPreparationStepsRecipeId(recipeId).isEmpty()) {
			System.out.println("No recipe/preparationsteps to ID " + recipeId + " found.\n");
		}
		else {
			System.out.println("The following preparation steps need to be done:");
		System.out.println(recipeController.getAllPreparationStepsRecipeId(recipeId) + "\n");
		}
	}

}
