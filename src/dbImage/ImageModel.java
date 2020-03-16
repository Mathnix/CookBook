package dbImage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dbUtil.dbConnection;

public class ImageModel {
	
	public int getImageIDByFilename(String name) throws SQLException {

		Connection conn = dbConnection.getConnection();
		PreparedStatement pr = null;
		ResultSet rs = null;

		int imageId = -1; //How to do this r

		String sql = "SELECT ImageID FROM Image WHERE Filename = ?";
		try {
			pr = conn.prepareStatement(sql);
			pr.setString(1, name);

			rs = pr.executeQuery();
			if (rs.next()) {
				imageId = rs.getInt("ImageID");
			}
			conn.close();
			return imageId;
		} catch (SQLException e) {
			return imageId;
		} finally {
			pr.close();
			rs.close();
			conn.close();
		}
	}
	
	public String getImageFilenameByID(int id) throws SQLException {

		Connection conn = dbConnection.getConnection();
		PreparedStatement pr = null;
		ResultSet rs = null;

		String filename = new String();

		String sql = "SELECT Filename FROM Image WHERE ImageID = ?";
		try {
			pr = conn.prepareStatement(sql);
			pr.setInt(1, id);

			rs = pr.executeQuery();
			while (rs.next()) {
				filename=rs.getString("Filename");
			}
			conn.close();
			return filename;
		} catch (SQLException e) {
			return filename;
		} finally {
			pr.close();
			rs.close();
			conn.close();
		}
	}

}
