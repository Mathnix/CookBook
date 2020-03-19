package recipeClasses;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;

/**
 * Main class to test things.
 * 
 * 
 * @author lena
 *
 */
public class MainClass extends Application {

	@FXML
	public void start(Stage stage) throws Exception {

		Parent root = (Parent) FXMLLoader.load(getClass().getResource("/Application.fxml"));

		Scene scene = new Scene(root);

		stage.setScene(scene);
		stage.setTitle("CookBook");
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
