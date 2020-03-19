package recipeClasses;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.fxml.FXML;
import javafx.scene.control.Control;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.util.Callback;

public class FXMLController {

	@FXML
	private ImageView imgID;
	@FXML
	private ListView<String> list_Ingredients;
	@FXML
	private ListView<String> list_preparation;

	@FXML
	public void initialize() {

		Image image = new Image(getClass().getResource("/Rezept.jpeg").toExternalForm());
		imgID.setImage(image);

		Recipe testRecipe = new Recipe();

		testRecipe.setTitle("Scharfe Zucchini-Nudeln");
		testRecipe.setCookingTime("40 min");
		testRecipe.setDescription("Mit würzigem Speck-Mix für ein " + "unschlagbares Pasta-Vergnügen");
		testRecipe.setImageName("ZucchiniNudeln.jpg");
		testRecipe.setNumberOfServings(4);

		Ingredient ingredient1 = new Ingredient("Spaghetti", 400, "g");
		Ingredient ingredient2 = new Ingredient("Zucchini", 2, "Stck");
		Ingredient ingredient3 = new Ingredient("Kirschtomaten", 8, "Stck");
		Ingredient ingredient4 = new Ingredient("rote Zwiebeln", 2, "Stck");
		Ingredient ingredient5 = new Ingredient("Chilischote", 1, "Stck");
		Ingredient ingredient6 = new Ingredient("geräuscherter Speck", 150, "g");
		Ingredient ingredient7 = new Ingredient("Petersilie", 0.5, "Bund");
		Ingredient ingredient8 = new Ingredient("Olivenöl", 4, "EL");
		Ingredient ingredient9 = new Ingredient("Knoblauchzehen", 2, "Stck");

		ArrayList<Ingredient> listOfIngredients = new ArrayList<Ingredient>();

		listOfIngredients.add(ingredient1);
		listOfIngredients.add(ingredient2);
		listOfIngredients.add(ingredient3);
		listOfIngredients.add(ingredient4);
		listOfIngredients.add(ingredient5);
		listOfIngredients.add(ingredient6);
		listOfIngredients.add(ingredient7);
		listOfIngredients.add(ingredient8);
		listOfIngredients.add(ingredient9);

		testRecipe.setListOfIngredients(listOfIngredients);

		HashMap<Integer, String> preparation = new HashMap<Integer, String>();

		Integer step1 = 1;
		String desc1 = "Die Nudeln nach Packungsangabe in reichlich Salzwasser bissfest garen.";

		Integer step2 = 2;
		String desc2 = "Inzwischen die Zucchini putzen, waschen und in 1cm große Würfel schneiden. "
				+ "Die Tomaten waschen und halbieren oder vierteln, den Stielansatz entfernen. "
				+ "Die Zwiebeln schälen, halbieren und in feine Streifen schneiden. Den Knoblauch "
				+ "schälen und fein hacken. Die Chilischote halbieren, Trennwände und Kerne entfernen,"
				+ "die Hälften waschen und winzig klein würfeln."
				+ "Den Speck ebenfalls in kleine Würfel schneiden. Die Petersilie waschen und trocken "
				+ "schütteln, die Blätter abzupfen und klein schneiden.";

		Integer step3 = 3;
		String desc3 = "Das Öl in einer großen Pfanne erhitzen. Speck, Zwiebeln und Knoblauch dazugeben "
				+ "und bei mittlerer Hitze ca. 5 Min. braten, bis der Speck knusprig ist. "
				+ "Chilischote und Zucchini zum Speck geben und 2 Min. unter Wenden mitbraten. "
				+ "Die Tomaten hinzufügen. Ein Achtel Liter des Nudelwassers dazugießen und alles "
				+ "2-3 Min. bei schwacher Hitze kochen lassen. Mit Salz und Pfeffer abschmecken.";

		Integer step4 = 4;
		String desc4 = "Die Nudeln abgieße, kurz abtropfen lassen und unter die Sauce mischen.";

		Integer step5 = 5;
		String desc5 = "TIPP: Mit frisch geriebenem Parmesan obendrauf wird die Pasta zum perfekten "
				+ "Genuss alla mamma.";

		preparation.put(step1, desc1);
		preparation.put(step2, desc2);
		preparation.put(step3, desc3);
		preparation.put(step4, desc4);
		preparation.put(step5, desc5);

		Preparation wholePrep = new Preparation(preparation);

		testRecipe.setListOfPreparationSteps(wholePrep);
		list_Ingredients.getItems().add("ZUTATEN:");
		list_Ingredients.getItems().add("");
		for (int i = 0; i < testRecipe.getListOfIngredients().size(); i++) {
			Ingredient currIng = testRecipe.getListOfIngredients().get(i);
			String text = currIng.getAmount() + " " + currIng.getMeasure() + " " + currIng.getName();

			list_Ingredients.getItems().add(text);
		}
		list_Ingredients.getItems().add("");

		list_Ingredients.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
			@Override
			public ListCell<String> call(ListView<String> param) {
				return new ListCell<String>() {
					{
						prefWidthProperty().bind(list_Ingredients.widthProperty().subtract(20));
						setMaxWidth(Control.USE_PREF_SIZE);
					}

					@Override
					protected void updateItem(String item, boolean empty) {
						if (item != null && !empty) {
							this.setWrapText(true);
							setText(item);
							setFont(Font.font(16));
						} else {
							setText(null);
							setFont(Font.font(16));
						}
					}

				};
			}
		});

		list_Ingredients.setPrefHeight(15 * 24);

		Preparation currPrep = testRecipe.getPreparation();
		list_preparation.getItems().add("ZUBEREITUNG:");
		list_preparation.getItems().add("");

		for (int i = 0; i < currPrep.getSize(); i++) {
			int index = i + 1;
			String currdesc = currPrep.getDescription(index);
			String text = index + ". " + currdesc;

			list_preparation.getItems().add(text);
			list_preparation.getItems().add("");

		}

		list_preparation.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
			@Override
			public ListCell<String> call(ListView<String> param) {
				return new ListCell<String>() {
					{
						prefWidthProperty().bind(list_preparation.widthProperty().subtract(20));
						setMaxWidth(Control.USE_PREF_SIZE);
					}

					@Override
					protected void updateItem(String item, boolean empty) {
						if (item != null && !empty) {
							this.setWrapText(true);
							setText(item);
							setFont(Font.font(16));
						} else {
							setText(null);
							setFont(Font.font(16));
						}
					}

				};
			}
		});
		list_preparation.setPrefHeight(29 * 25);
	}

}
