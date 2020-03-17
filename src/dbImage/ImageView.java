package dbImage;

//TODO: Concrete GUI.

/**
 * Test Status for all actions on the database affecting the image.
 * 
 * In this version: Only prints the return values!
 * 
 * @author lena
 *
 */

public class ImageView {

	public ImageController imageController = new ImageController();

	public void printFilename(int imageId) {

		if (imageController.getFilenameByID(imageId).isEmpty()) {
			System.out.println("No Image with ID " + imageId + " found.");
		} else {
			System.out.println("The Image with ID " + imageId + " is:");
			System.out.println(imageController.getFilenameByID(imageId) + "\n");
		}
	}

	public void printImageID(String filename) {

		if (imageController.getImageIDByName(filename) == -1) {
			System.out.println("No Image with filename " + filename + " found.");
		} else {
			System.out.println("The Image " + filename + " has the following ID:");
			System.out.println(imageController.getImageIDByName(filename) + "\n");
		}
	}

	public void printInsertedImageID(String filename) {

		int rs = imageController.insertImage(filename);

		if (rs == -1) {
			System.out.println("No INSERT for " + filename + " possible.");
		} else {
			System.out.println("Measurement " + filename + " was inserted and has ID:");
			System.out.println(rs + "\n");
		}

	}

}
