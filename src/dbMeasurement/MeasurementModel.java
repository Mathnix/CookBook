package dbMeasurement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dbUtil.dbConnection;

public class MeasurementModel {
	
	public int getMeasurementIDByName(String name) throws SQLException {

		Connection conn = dbConnection.getConnection();
		PreparedStatement pr = null;
		ResultSet rs = null;

		int measurementId = -1; //How to do this r

		String sql = "SELECT MeasurementID FROM Measurements WHERE Unit = ?";
		try {
			pr = conn.prepareStatement(sql);
			pr.setString(1, name);

			rs = pr.executeQuery();
			if (rs.next()) {
				measurementId = rs.getInt("MeasurementID");
			}
			conn.close();
			return measurementId;
		} catch (SQLException e) {
			return measurementId;
		} finally {
			pr.close();
			rs.close();
			conn.close();
		}
	}
	
	public String getMeasurementUnitNameByID(int id) throws SQLException {

		Connection conn = dbConnection.getConnection();
		PreparedStatement pr = null;
		ResultSet rs = null;

		String measurementUnitName = new String();

		String sql = "SELECT Unit FROM Measurements WHERE MeasurementID = ?";
		try {
			pr = conn.prepareStatement(sql);
			pr.setInt(1, id);

			rs = pr.executeQuery();
			while (rs.next()) {
				measurementUnitName=rs.getString("Unit");
			}
			conn.close();
			return measurementUnitName;
		} catch (SQLException e) {
			return measurementUnitName;
		} finally {
			pr.close();
			rs.close();
			conn.close();
		}
	}

}
