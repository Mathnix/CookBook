package recipeClasses;

import dbPreparation.*;

/**
 * Main class to test things.
 * 
 * 
 * @author lena
 *
 */
public class MainClass {

	public static void main(String[] args) {

		/*
		 * System.out.println("----------------------------- ");
		 * System.out.println("TEST INGREDIENT VIEW ");
		 * System.out.println("----------------------------- \n"); //Test IngredientView
		 * IngredientsView ingredientView = new IngredientsView();
		 * 
		 * String ingredientName = "Tomaten"; int ingredientId = 4; String
		 * ingredientName2 = "Mehl";
		 * 
		 * ingredientView.printIngredientID(ingredientName);
		 * ingredientView.printIngredientName(ingredientId);
		 * ingredientView.printInsertedIngredientID(ingredientName);
		 * 
		 * 
		 * System.out.println("\n----------------------------- ");
		 * System.out.println("TEST MEASUREMENT VIEW ");
		 * System.out.println("----------------------------- \n"); //Test
		 * MeasurementView MeasurementView measurementView = new MeasurementView();
		 * 
		 * String measurementUnitName = "L"; int measurementId = 7; String
		 * measurementUnitName2 = "Banane";
		 * 
		 * measurementView.printMeasurementID(measurementUnitName);
		 * measurementView.printMeasurementUnitName(measurementId);
		 * measurementView.printInsertedMeasurementUnitID(measurementUnitName2);
		 * 
		 * 
		 * System.out.println("\n----------------------------- ");
		 * System.out.println("TEST IMAGE VIEW ");
		 * System.out.println("----------------------------- \n"); //Test ImageView
		 * ImageView imageView = new ImageView();
		 * 
		 * String filename = "MeinBild.jpg"; int imageId = 2;
		 * 
		 * imageView.printImageID(filename); imageView.printFilename(imageId);
		 * imageView.printInsertedImageID(filename);
		 */

		System.out.println("\n----------------------------- ");
		System.out.println("TEST PREPARATION VIEW ");
		System.out.println("----------------------------- \n");
		// Test PreparationView
		PreparationView prepView = new PreparationView();

		int preparationStepId = 8;
		String description = "Erstmal einen Topf suchen";
		int step = 1;
		String description2 = "Zwiebeln schälen";
		int step2 = 5;

		prepView.printDescription(preparationStepId);
		prepView.printStepNumber(preparationStepId);

		prepView.printInsertPreparationStep(description, step);
		prepView.printInsertPreparationStep(description2, step2);

		/*
		 * System.out.println("\n----------------------------- ");
		 * System.out.println("TEST RECIPE VIEW ");
		 * System.out.println("----------------------------- \n"); //Test RecipeView
		 * RecipeView recipeView = new RecipeView();
		 * 
		 * int recipeId = 1; String recipeTitle = "Spaghetti mit Tomatensoße";
		 * 
		 * 
		 * recipeView.printAllRecipes();
		 * 
		 * recipeView.printRecipeIDByTitle(recipeTitle);
		 * recipeView.printRecipeNameByID(recipeId);
		 * recipeView.printAllIngredientsByRecipeID(recipeId);
		 * recipeView.printAllPreparationStepsByRecipeID(recipeId);
		 * 
		 */
	}

}
