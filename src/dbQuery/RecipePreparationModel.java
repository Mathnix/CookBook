package dbQuery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import dbUtil.dbConnection;

/**
 * Handles all database action that affect the link table RecipePreparation
 * between a recipe and its preparation steps. A row in this table consists of the recipeID and 
 * the preparationStepID, so every step that is performed in the cooking process is linked to its recipe.
 * 
 * @author lena
 *
 */
public class RecipePreparationModel {

	/**
	 * Insert a new entry in the link table between a recipe and its preparation
	 * steps.
	 * 
	 * @param recipeID          The ID of the recipe.
	 * @param preparationStepID The ID of one preparation step.
	 * @throws SQLException
	 */
	public void insert(int recipeID, int preparationStepID) throws SQLException {

		Connection conn = dbConnection.getConnection();
		PreparedStatement pr = null;

		String sql = "INSERT INTO RecipePreparation (RecipeID,PreparationStepID) "
				+ "SELECT ?,? WHERE NOT EXISTS (SELECT * FROM RecipePreparation WHERE "
				+ "RecipeID = ? AND PreparationStepID =?)";

		try {
			pr = conn.prepareStatement(sql);
			pr.setInt(1, recipeID);
			pr.setInt(2, preparationStepID);

			pr.setInt(3, recipeID);
			pr.setInt(4, preparationStepID);

			pr.executeUpdate();

		}catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			pr.close();
			conn.close();
		}

	}

}
