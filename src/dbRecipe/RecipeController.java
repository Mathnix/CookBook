package dbRecipe;

import java.sql.SQLException;
import java.util.ArrayList;

public class RecipeController {
	
	public RecipeModel recipeModel = new RecipeModel();
	
	public  ArrayList<String> recipes;
	
	public ArrayList<String> getAllRecipesOfDB(){
		
		try {
			recipes = recipeModel.getAllRecipes();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return recipes;
	}
	
}
