package recipeClasses;

import dbIngredients.*;
import dbMeasurement.*;
import dbRecipe.*;
import dbImage.*;
import dbPreparation.*; 


public class MainClass { 

	public static void main(String[] args) { 
		
		System.out.println("----------------------------- ");
		System.out.println("TEST INGREDIENT VIEW ");
		System.out.println("----------------------------- \n");
		//Test IngredientView
		IngredientsView ingredientView = new IngredientsView();
		
		String ingredientName = "Tomaten";
		int ingredientId = 4;
		
		ingredientView.printIngredientId(ingredientName);
		ingredientView.printIngredientName(ingredientId);
		
		
		System.out.println("\n----------------------------- ");
		System.out.println("TEST MEASUREMENT VIEW ");
		System.out.println("----------------------------- \n");
		//Test MeasurementView
		MeasurementView measurementView = new MeasurementView();
		
		String measurementUnitName = "L";
		int measurementId = 7;
		
		measurementView.printMeasurementId(measurementUnitName);
		measurementView.printMeasurementUnitName(measurementId);
		
		
		System.out.println("\n----------------------------- ");
		System.out.println("TEST IMAGE VIEW ");
		System.out.println("----------------------------- \n");
		//Test ImageView
		ImageView imageView = new ImageView();
		
		String filename = "MeinBild.jpg";
		int imageId = 2;
		
		imageView.printImageId(filename);
		imageView.printFilename(imageId);
		
		
		System.out.println("\n----------------------------- ");
		System.out.println("TEST PREPARATION VIEW ");
		System.out.println("----------------------------- \n");
		//Test PreparationView
		PreparationView prepView = new PreparationView();
		
		int preparationStepId = 8;
		
		prepView.printDescription(preparationStepId);
		prepView.printStepNumber(preparationStepId);
		
		
		System.out.println("\n----------------------------- ");
		System.out.println("TEST RECIPE VIEW ");
		System.out.println("----------------------------- \n");
		//Test RecipeView
		RecipeView recipeView = new RecipeView();
		
		int recipeId = 1;
		String recipeTitle = "Spaghetti mit Tomatensoße";
		
		
		recipeView.printAllRecipes();
		
		recipeView.printRecipeIdByTitle(recipeTitle);
		recipeView.printRecipeNameById(recipeId);
		recipeView.printAllIngredientsByRecipeId(recipeId);
		recipeView.printAllPreparationStepsByRecipeId(recipeId);
		
	}

}
