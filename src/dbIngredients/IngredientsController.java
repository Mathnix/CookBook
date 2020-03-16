package dbIngredients;

import java.sql.SQLException;


public class IngredientsController {

	public IngredientsModel ingredientModel = new IngredientsModel();

	public String ingredientName;
	public int ingredientId;

	public String getIngredientNameByID(int id) {

		try {
			ingredientName = ingredientModel.getIngredientNameByID(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ingredientName;
	}

	public int getIngredientIDByName(String name) {

		try {
			ingredientId = ingredientModel.getIngredientIDByName(name);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ingredientId;
	}

}
