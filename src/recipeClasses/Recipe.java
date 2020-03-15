package recipeClasses;
import java.util.ArrayList;

public class Recipe {
	
	private String title;
	private String description;
	private String cookingTime;
	private int numberOfServings;
	
	private ArrayList<Ingredient> listOfIngredients;
	private ArrayList<PreparationStep> listOfPreparationSteps;
	
	private String imageName;
	
	public Recipe() {
		
	}
	
	public Recipe(String title, String description, String cookingTime, int numberOfServings,
			ArrayList<Ingredient> listOfIngredients, ArrayList<PreparationStep> listOfPreparationSteps,
			String imageName) {
		this.title = title;
		this.description = description;
		this.cookingTime = cookingTime;
		this.numberOfServings = numberOfServings;
		this.listOfIngredients = listOfIngredients;
		this.listOfPreparationSteps = listOfPreparationSteps;
		this.imageName = imageName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCookingTime() {
		return cookingTime;
	}

	public void setCookingTime(String cookingTime) {
		this.cookingTime = cookingTime;
	}

	public int getNumberOfServings() {
		return numberOfServings;
	}

	public void setNumberOfServings(int numberOfServings) {
		this.numberOfServings = numberOfServings;
	}

	public ArrayList<Ingredient> getListOfIngredients() {
		return listOfIngredients;
	}

	public void setListOfIngredients(ArrayList<Ingredient> listOfIngredients) {
		this.listOfIngredients = listOfIngredients;
	}

	public ArrayList<PreparationStep> getListOfPreparationSteps() {
		return listOfPreparationSteps;
	}

	public void setListOfPreparationSteps(ArrayList<PreparationStep> listOfPreparationSteps) {
		this.listOfPreparationSteps = listOfPreparationSteps;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	
}
