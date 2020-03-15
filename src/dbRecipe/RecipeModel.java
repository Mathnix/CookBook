package dbRecipe;

import dbUtil.dbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RecipeModel {

	public ArrayList<String> getAllRecipes() throws SQLException {

		Connection conn = dbConnection.getConnection();
		PreparedStatement pr = null;
		ResultSet rs = null;
		
		ArrayList<String> recipes = new ArrayList<String>();

		String sql = "SELECT Title FROM Recipe";
		try {
			pr = conn.prepareStatement(sql);

			rs = pr.executeQuery();
			while(rs.next()) {
				recipes.add(rs.getString("Title"));	
			}
			conn.close();
			return recipes;
		} catch (SQLException e) {
			return recipes;
		} finally {
			pr.close();
			rs.close();
			conn.close();
		}
	}

}
