package dbIngredients;

//TODO: Concrete GUI.

/**
 * Test Status for all actions on the database affecting the ingredients.
 * 
 * In this version: Only prints the return values!
 * 
 * @author lena
 *
 */
public class IngredientsView {

	public IngredientsController ingredientController = new IngredientsController();

	public void printIngredientName(int ingredientId) {

		if (ingredientController.getIngredientNameByID(ingredientId).isEmpty()) {
			System.out.println("No Ingredients with ID " + ingredientId + " found.");
		} else {
			System.out.println("The ingredient with ID " + ingredientId + " is:");
			System.out.println(ingredientController.getIngredientNameByID(ingredientId) + "\n");
		}
	}

	public void printIngredientID(String ingredientName) {

		if (ingredientController.getIngredientIDByName(ingredientName) == -1) {
			System.out.println("No ingredient with name " + ingredientName + " found.");
		} else {
			System.out.println("Ingredient " + ingredientName + " has ID:");
			System.out.println(ingredientController.getIngredientIDByName(ingredientName) + "\n");
		}
	}

	public void printInsertedIngredientID(String ingredientName) {

		if (ingredientController.insertIngredient(ingredientName) == -1) {
			System.out.println("No INSERT for " + ingredientName + " possible.");
		} else {
			System.out.println("Ingredient " + ingredientName + " was inserted and has ID:");
			System.out.println(ingredientController.insertIngredient(ingredientName) + "\n");
		}

	}

}
