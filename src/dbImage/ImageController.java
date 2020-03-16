package dbImage;

import java.sql.SQLException;

public class ImageController {
	
	ImageModel imageModel = new ImageModel();
	
	public String filename;
	public int imageId;
	
	public String getFilenameByID(int id) {

		try {
			filename = imageModel.getImageFilenameByID(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return filename;
	}

	public int getImageIDByName(String name) {

		try {
			imageId = imageModel.getImageIDByFilename(name);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return imageId;
	}

	

}
