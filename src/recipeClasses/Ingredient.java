package recipeClasses;

/**
 * Represents one ingredient in the recipe that consists of: - name - amount -
 * measure
 * 
 * @author lena
 *
 */
public class Ingredient {

	private String name;
	private double amount;
	private String measure;

	/**
	 * Default constructor
	 */
	public Ingredient() {

	}

	/**
	 * Constructor for a whole ingredient.
	 * 
	 * @param name    The name of the ingredient (for example a fruit, vegetable,
	 *                seasoning).
	 * @param amount  The amount of the ingredient to use .
	 * @param measure The measure to use for the value stored in amount.
	 */
	public Ingredient(String name, double amount, String measure) {
		this.name = name;
		this.amount = amount;
		this.measure = measure;

	}

	/**
	 * Get the name of the ingredient.
	 * 
	 * @return The name of the ingredient (for example a fruit, vegetable,
	 *         seasoning).
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set the name of the ingredient.
	 * 
	 * @param name The name of the ingredient (for example a fruit, vegetable,
	 *             seasoning).
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Get the amount of the respective ingredient.
	 * 
	 * @return The numerical value of the amount of the respective ingredient.
	 */
	public double getAmount() {
		return amount;
	}

	/**
	 * Set the amount of the respective ingredient.
	 * 
	 * @param amount The numerical value of the amount of the respective ingredient.
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}

	/**
	 * Get the measure used for the respective ingredient and amount as text (for
	 * example piece, tbsp, tsp, g, l).
	 * 
	 * @return A string that represents the measure used for the respective
	 *         ingredient and amount.
	 */
	public String getMeasure() {
		return measure;
	}

	/**
	 * Set the measure used for the respective ingredient and amount as text (for
	 * example piece, tbsp, tsp, g, l).
	 * 
	 * @param measure A string that represents the measure used for the respective
	 *                ingredient and amount.
	 */
	public void setMeasure(String measure) {
		this.measure = measure;
	}
}
