package dbQuery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import dbUtil.dbConnection;

/**
 * Handles all database action that affect the link table between a recipe and
 * its picture. This table contains the recipeID and the imageID as row if the
 * picture and recipe are linked together.
 * 
 * @author lena
 *
 */
public class RecipeImageModel {

	/**
	 * Insert a new entry in the link table between a recipe and its picture.
	 * 
	 * @param recipeID The ID of recipe.
	 * @param imageID  The ID of the image linked to the recipe.
	 * @throws SQLException
	 */
	public void insert(int recipeID, int imageID) throws SQLException {

		Connection conn = dbConnection.getConnection();
		PreparedStatement pr = null;

		String sql = "INSERT INTO RecipeImage (RecipeID,ImageID) SELECT ?,? "
				+ "WHERE NOT EXISTS(SELECT * FROM RecipeImage WHERE RecipeID =? AND ImageID =?)";

		try {
			pr = conn.prepareStatement(sql);
			pr.setInt(1, recipeID);
			pr.setInt(2, imageID);

			pr.setInt(3, recipeID);
			pr.setInt(4, imageID);

			pr.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			pr.close();
			conn.close();
		}

	}

}
