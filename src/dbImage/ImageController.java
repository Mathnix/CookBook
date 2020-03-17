package dbImage;

import java.sql.SQLException;

/**
 * The Controller to perform database actions affecting the image/picture of a
 * recipe.
 * 
 * @author lena
 *
 */
public class ImageController {

	ImageModel imageModel = new ImageModel();

	public String filename;
	public int imageId;

	/**
	 * Get the filename via its ID.
	 * 
	 * @param id The ID of the image/filename in the database.
	 * @return A String representing the filename of the image.
	 */
	public String getFilenameByID(int id) {

		try {
			filename = imageModel.getImageFilenameByID(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return filename;
	}

	/**
	 * Get the ID of the image in the database based on the filename.
	 * 
	 * @param filename The filename of the image.
	 * @return The ID of the image/filename in the databse.
	 */
	public int getImageIDByName(String filename) {

		try {
			imageId = imageModel.getImageIDByFilename(filename);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return imageId;
	}

	/**
	 * Insert a new image in the database
	 * 
	 * @param filename The filename of the image to insert in the database.
	 * @return The ID of the image/filename in the database.
	 */
	public int insertImage(String filename) {

		try {
			imageId = imageModel.insertImage(filename);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return imageId;
	}

	// TODO: As in ImageModel

}
