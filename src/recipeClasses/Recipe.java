package recipeClasses;

import java.util.ArrayList;

/**
 * Represents a whole recipe that consists of: - title/name - description -
 * cooking time - number of servings
 * 
 * and contains two lists: - list of ingredients - list of preparation steps
 * 
 * @author lena
 *
 */

public class Recipe {

	private String title;
	private String description;
	private String cookingTime;
	private int numberOfServings;

	private ArrayList<Ingredient> listOfIngredients;
	Preparation preparation;

	private String imageName;

	/**
	 * Default constructor.
	 */
	public Recipe() {

	}

	/**
	 * Constructor for a whole known recipe with all properties.
	 * 
	 * @param title             The title or name of the recipe.
	 * @param description       A short description of the recipe.
	 * @param cookingTime       The cooking time, saved as string.
	 * @param numberOfServings  The number of servings, saved as int.
	 * @param listOfIngredients Objects of the list are of type Ingredient
	 *                          {@link recipeClasses.Ingredient}.
	 * @param preparation       A HashMap of key/value pairs for step number and
	 *                          preparation step description.
	 *                          {@link recipeClasses,PreparationStep}.
	 * @param imageName         The filename of the image
	 */
	public Recipe(String title, String description, String cookingTime, int numberOfServings,
			ArrayList<Ingredient> listOfIngredients, Preparation preparation, String imageName) {
		this.title = title;
		this.description = description;
		this.cookingTime = cookingTime;
		this.numberOfServings = numberOfServings;
		this.listOfIngredients = listOfIngredients;
		this.preparation = preparation;
		this.imageName = imageName;
	}

	/**
	 * Get the title/name of the recipe.
	 * 
	 * @return Title/Name of the recipe.
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Set the title/name of the recipe.
	 * 
	 * @param title The title/name of the recipe.
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Get the description of the recipe. That can contain a short information about
	 * the dish and/or its cultural background.
	 * 
	 * @return The description text.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Set the description of the recipe. That can contain a short information about
	 * the dish and/or its cultural background.
	 * 
	 * @param description The description text
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Get the cooking time. Since this is saved as String you can get the
	 * information in min/h or any other measure.
	 * 
	 * @return The cooking time as string.
	 */
	public String getCookingTime() {
		return cookingTime;
	}

	/**
	 * Set the cooking time. Since this is saved as String you can set the
	 * information in min/h or any other measure.
	 * 
	 * @param cookingTime The cooking time as string.
	 */
	public void setCookingTime(String cookingTime) {
		this.cookingTime = cookingTime;
	}

	/**
	 * Get the number of servings the recipe is made for.
	 * 
	 * @return The number od servings the recipe is made for saved as int.
	 */
	public int getNumberOfServings() {
		return numberOfServings;
	}

	/**
	 * Set the number of servings the recipe is made for.
	 * 
	 * @param numberOfServings The number of servings the recipe is made for as int.
	 */
	public void setNumberOfServings(int numberOfServings) {
		this.numberOfServings = numberOfServings;
	}

	/**
	 * Get the list of all ingredients that are used in the recipe. The list
	 * contains objects of type ingredient which is defined here
	 * ({@link recipeClasses.Ingredient}).
	 * 
	 * @return List of ingredient objects that represent all ingredients used in the
	 *         recipe.
	 */
	public ArrayList<Ingredient> getListOfIngredients() {
		return listOfIngredients;
	}

	/**
	 * Set the list of all ingredients that are used in the recipe. The list
	 * contains objects of type Ingredient which is defined here
	 * ({@link recipeClasses.Ingredient}).
	 * 
	 * 
	 * @param listOfIngredients List of Ingredient objects that represent all
	 *                          ingredients used in the recipe.
	 */
	public void setListOfIngredients(ArrayList<Ingredient> listOfIngredients) {
		this.listOfIngredients = listOfIngredients;
	}

	/**
	 * Get all preparation steps that are used to cook the recipe. The Preparation
	 * Object is defined here: ({@link recipeClasses.Preparation}).
	 * 
	 * @return Preparation object that represents all steps that have to be
	 *         performed to cook the recipe as HashMap and key/value pair of step
	 *         number and description.
	 */
	public Preparation getPreparation() {
		return preparation;
	}

	/**
	 * Set all preparation steps that are used to cook the recipe. The Preparation
	 * Object is defined here: ({@link recipeClasses.Preparation}).
	 * 
	 * @param preparation Preparation object that represents all steps that have to
	 *                    be performed to cook the recipe as HashMap and key/value
	 *                    pair of step number and description.
	 */
	public void setListOfPreparationSteps(Preparation preparation) {
		this.preparation = preparation;
	}

	/**
	 * Get the filename (with extension) of the image that is used to represent the
	 * cooked dish.
	 * 
	 * @return The filename with extension that represents the cooked dished.
	 */
	public String getImageName() {
		return imageName;
	}

	/**
	 * Set the filename (with extension) of the image that is used to represent the
	 * cooked dish.
	 * 
	 * @param imageName The filename with extension that represents the cooked
	 *                  dished.
	 */
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	/**
	 * Adds an Ingredient object to the list of all ingredients The Ingredient
	 * object is defined here ({@link recipeClasses.Ingredient}).
	 * 
	 * @param ingredient An Ingredient object, represents an ingredient used in the
	 *                   recipe.
	 */
	public void addIngredient(Ingredient ingredient) {
		this.listOfIngredients.add(ingredient);
	}

	// TODO: Add unique ingredients
	// TODO: Find Ingredient/Preparation step -> could need comparison operator
	// TODO: Delete Ingredient/Preparation step
}
