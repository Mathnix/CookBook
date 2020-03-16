package dbPreparation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dbUtil.dbConnection;

public class PreparationModel {
	
	public String getPrepDescriptionByID(int id) throws SQLException {

		Connection conn = dbConnection.getConnection();
		PreparedStatement pr = null;
		ResultSet rs = null;

		String description = new String();

		String sql = "SELECT Description FROM PreparationStep WHERE PreparationStepID = ?";
		try {
			pr = conn.prepareStatement(sql);
			pr.setInt(1, id);

			rs = pr.executeQuery();
			while (rs.next()) {
				description=rs.getString("Description");
			}
			conn.close();
			return description;
		} catch (SQLException e) {
			return description;
		} finally {
			pr.close();
			rs.close();
			conn.close();
		}
	}
	
	public int getPrepStepNumberByID(int id) throws SQLException {

		Connection conn = dbConnection.getConnection();
		PreparedStatement pr = null;
		ResultSet rs = null;

		int stepNumber = -1;

		String sql = "SELECT StepNumber FROM PreparationStep WHERE PreparationStepID = ?";
		try {
			pr = conn.prepareStatement(sql);
			pr.setInt(1, id);

			rs = pr.executeQuery();
			while (rs.next()) {
				stepNumber=rs.getInt("StepNumber");
			}
			conn.close();
			return stepNumber;
		} catch (SQLException e) {
			return stepNumber;
		} finally {
			pr.close();
			rs.close();
			conn.close();
		}
	}

}
