package dbImage;

public class ImageView {
	
	public ImageController imageController = new ImageController();
	
	public void printFilename(int imageId) {
		
		if(imageController.getFilenameByID(imageId).isEmpty()) {
			System.out.println("No Image with ID " + imageId + " found.");
		}
		else {
		System.out.println("The Image with ID " + imageId + " is:");
		System.out.println(imageController.getFilenameByID(imageId) + "\n");
		}
	}
	
	public void printImageId(String filename) {
		
		if(imageController.getImageIDByName(filename) == -1) {
			System.out.println("No Image with filename " + filename + " found.");
		}
		else{
		System.out.println("The Image " + filename + " has the following ID:");
		System.out.println(imageController.getImageIDByName(filename) + "\n");
		}
	}
	
	

}
