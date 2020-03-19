package recipeClasses;

import java.util.HashMap;

/**
 * Represents the preparation that is performed within the cooking process. To
 * do so it contains:
 * 
 * - description - step number
 * 
 * combined as a key/value pair in preparation.
 * 
 * @author lena
 *
 */
public class Preparation {

	HashMap<Integer, String> preparation;

	/**
	 * Default constructor.
	 */
	public Preparation() {

	}

	/**
	 * Constructor that adds a key/value pair by the single values.
	 * 
	 * @param numberOfStep The number, when the step needs to be performed.
	 * @param description  The description what to do in the respective preparation
	 *                     step.
	 */
	public Preparation(int numberOfStep, String description) {
		this.preparation.put(numberOfStep, description);
	}

	/**
	 * Constructor by direct use of a key/value pair as HasMap.
	 * 
	 * @param preparation The preparation, consisting of step number and description
	 *                    of each preparation step.
	 */
	public Preparation(HashMap<Integer, String> preparation) {
		this.preparation = preparation;
	}

	/**
	 * Get the preparation as key/value pairs of preparation steps represented as
	 * HashMap.
	 * 
	 * @return The preparation, consisting of step number and description of each
	 *         preparation step.
	 */
	public HashMap<Integer, String> getPreparation() {

		return this.preparation;
	}

	/**
	 * Set the preparation as key/value pairs of preparation steps represented as
	 * HashMap.
	 * 
	 * @param preparation The preparation, consisting of step number and description
	 *                    of each preparation step.
	 */
	public void setPreparation(HashMap<Integer, String> preparation) {
		this.preparation = preparation;
	}

	/**
	 * Get the description of the respective preparation step, thus the description
	 * what to do.
	 * 
	 * @param key The number, when the step needs to be performed.
	 * @return A text telling you what to do in the respective preparation step.
	 */
	public String getDescription(int numberOfStep) {
		return preparation.get(numberOfStep);
	}

	/**
	 * Adds a single preparation step consisting of step number and description to
	 * whole preparation.
	 * 
	 * @param numberOfStep The number, when the step needs to be performed.
	 * @param description  The description what to do in the respective preparation
	 *                     step.
	 */
	public void addPreparationStep(int numberOfStep, String description) {

		preparation.put(numberOfStep, description);
	}

	public int getSize() {

		return preparation.size();
	}

}
