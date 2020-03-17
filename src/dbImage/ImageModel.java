package dbImage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dbUtil.dbConnection;

/**
 * Handles all database actions that affect the image/picture of a recipe.
 * 
 * 
 * @author lena
 *
 */
public class ImageModel {

	/**
	 * Get the database ID of an image by using its filename.
	 * 
	 * @param filename The filename of the image.
	 * @return The databse ID of the image with corresponding filename.
	 * @throws SQLException
	 */
	public int getImageIDByFilename(String filename) throws SQLException {

		Connection conn = dbConnection.getConnection();
		PreparedStatement pr = null;
		ResultSet rs = null;

		// TODO: Better representation of invalid ID
		int imageId = -1;

		String sql = "SELECT ImageID FROM Image WHERE Filename = ?";
		try {
			pr = conn.prepareStatement(sql);
			pr.setString(1, filename);

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

	/**
	 * Get the filname of the image by using its ID in the database.
	 * 
	 * @param id The ID of the image/filename in the database.
	 * @return The image filename represented as string.
	 * @throws SQLException
	 */
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
				filename = rs.getString("Filename");
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

	/**
	 * Insert a new image/filename in the database.
	 * 
	 * @param filename The filename of the image to insert into the databse.
	 * @return The ID of the inserted image.
	 * @throws SQLException
	 */
	public int insertImage(String filename) throws SQLException {

		Connection conn = dbConnection.getConnection();
		PreparedStatement pr = null;
		
		//TODO: Better representation for invalid ID and the success of the SQL Statement.
		int rs = -1;
		int imageId = -1;

		String sql = "INSERT INTO Image (Filename) " + "SELECT ? "
				+ "WHERE NOT EXISTS(SELECT * FROM Image WHERE Filename = ?)";
		try {
			pr = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			pr.setString(1, filename);
			pr.setString(2, filename);

			rs = pr.executeUpdate();

			// New INSERT
			if (rs > 0) {

				try (ResultSet generatedKeys = pr.getGeneratedKeys()) {
					generatedKeys.next();
					imageId = generatedKeys.getInt(1);
					generatedKeys.close();
				}

			}

			// Item already in Table
			if (rs == 0) {
				imageId = getImageIDByFilename(filename);
			}

			conn.close();
			return imageId;
		} catch (SQLException e) {
			return imageId;
		} finally {
			pr.close();
			conn.close();
		}
	}

	// TODO: Delete Image -> Need to adjust References in in-between tables.
	// TODO: Update/Change Image -> Need to adjust References in in-between tables.

}
