package dbIngredients;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import dbUtil.dbConnection;

public class IngredientsModel {
	
	public int getIngredientIDByName(String name) throws SQLException {

		Connection conn = dbConnection.getConnection();
		PreparedStatement pr = null;
		ResultSet rs = null;

		int ingredientId = -1; //How to do this r

		String sql = "SELECT IngredientID FROM Ingredients WHERE name = ?";
		try {
			pr = conn.prepareStatement(sql);
			pr.setString(1, name);

			rs = pr.executeQuery();
			if (rs.next()) {
				ingredientId = rs.getInt("IngredientID");
			}
			conn.close();
			return ingredientId;
		} catch (SQLException e) {
			return ingredientId;
		} finally {
			pr.close();
			rs.close();
			conn.close();
		}
	}
	
	public String getIngredientNameByID(int id) throws SQLException {

		Connection conn = dbConnection.getConnection();
		PreparedStatement pr = null;
		ResultSet rs = null;

		String ingredientName = new String();

		String sql = "SELECT Name FROM Ingredients WHERE IngredientID = ?";
		try {
			pr = conn.prepareStatement(sql);
			pr.setInt(1, id);

			rs = pr.executeQuery();
			while (rs.next()) {
				ingredientName=rs.getString("Name");
			}
			conn.close();
			return ingredientName;
		} catch (SQLException e) {
			return ingredientName;
		} finally {
			pr.close();
			rs.close();
			conn.close();
		}
	}

}
