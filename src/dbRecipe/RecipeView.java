package dbRecipe;


public class RecipeView {
	
	public RecipeController recipeController = new RecipeController();
	
	public void printResult() {
		
		System.out.println(recipeController.getAllRecipesOfDB());
	}

}
