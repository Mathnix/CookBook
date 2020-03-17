package dbIngredients;

import java.sql.SQLException;

/**
 * Handles the database actions affecting the ingredients.
 * 
 * @author lena
 *
 */
public class IngredientsController {

	public IngredientsModel ingredientModel = new IngredientsModel();

	public String ingredientName;
	public int ingredientId;

	/**
	 * Get the name of an ingredient by passing its databaseID.
	 * 
	 * @param id The ID of the ingredient in the database.
	 * @return The name of the ingredient.
	 */
	public String getIngredientNameByID(int id) {

		try {
			ingredientName = ingredientModel.getIngredientNameByID(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ingredientName;
	}

	/**
	 * Get the ID of the ingredient in the database by passing its name.
	 * 
	 * @param name The name of the ingredient.
	 * @return The ID of the ingredient in the database.
	 */
	public int getIngredientIDByName(String name) {

		try {
			ingredientId = ingredientModel.getIngredientIDByName(name);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ingredientId;
	}

	/**
	 * Insert a new ingredient in the database by passing its name.
	 * 
	 * @param name The name of the ingredient that will be inserted.
	 * @return The ID of the ingredient.
	 */
	public int insertIngredient(String name) {
		try {
			ingredientId = ingredientModel.insertIngredient(name);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ingredientId;
	}

	// TODO: See model.

}
