package dbQuery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dbUtil.dbConnection;

/**
 * Handles all database actions that affect the image/picture table of a recipe.
 * The table contains the filename with extension for the picture that is part
 * of the recipe. The filename is represented as string. *
 * 
 * @author lena
 *
 */
public class ImageModel {

	/**
	 * Get the database ID of an image by using its filename.
	 * 
	 * @param filename The filename of the image.
	 * @return The databse ID of the image with corresponding filename as int or -1
	 *         if it is not found.
	 * @throws SQLException
	 */
	public int getImageIDByFilename(String filename) throws SQLException {

		Connection conn = dbConnection.getConnection();
		PreparedStatement pr = null;
		ResultSet rs = null;

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
	 * @param imageID The ID of the image/filename in the database.
	 * @return The image filename represented as string or null if no image was
	 *         found.
	 * @throws SQLException
	 */
	public String getImageFilenameByID(int imageID) throws SQLException {

		Connection conn = dbConnection.getConnection();
		PreparedStatement pr = null;
		ResultSet rs = null;

		String filename = new String();

		String sql = "SELECT Filename FROM Image WHERE ImageID = ?";
		try {
			pr = conn.prepareStatement(sql);
			pr.setInt(1, imageID);

			rs = pr.executeQuery();
			while (rs.next()) {
				filename = rs.getString("Filename");
			}
			conn.close();
			return filename;
		} catch (SQLException e) {
			return null;
		} finally {
			pr.close();
			rs.close();
			conn.close();
		}
	}

	/**
	 * Insert a new image/filename in the database.filename.
	 * 
	 * @param filename The filename of the image to insert into the databse.
	 * @return The ID of the inserted image as int (newly inserted or the
	 *         corresponding ID if the element was already in the table) or -1 if
	 *         the insertion failed.
	 * @throws SQLException
	 */
	public int insertImage(String filename) throws SQLException {

		Connection conn = dbConnection.getConnection();
		PreparedStatement pr = null;

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

			// Item already in table
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

}
