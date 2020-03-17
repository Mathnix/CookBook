package dbPreparation;

import java.sql.SQLException;

/**
 * Handles all database actions that affect the preparation steps.
 * 
 * @author lena
 *
 */
public class PreparationController {

	PreparationModel preparationModel = new PreparationModel();

	public String description;
	public int stepNumber;
	public int preparationId;

	/**
	 * Get a preparation step/description passing its database ID.
	 * 
	 * @param id The ID of the preparation step in the database.
	 * @return The text of the preparation step that describes what to do in this
	 *         step.
	 */
	public String getPrepDescriptionByID(int id) {

		try {
			description = preparationModel.getPrepDescriptionByID(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return description;
	}

	/**
	 * Get the number of a preparaton step by passing its database ID.
	 * 
	 * @param id The ID of the preparation step used in the database.
	 * @return The number of the respective step, thus when to perform this step in
	 *         the cooking process.
	 */
	public int getPrepStepNumberByID(int id) {

		try {
			stepNumber = preparationModel.getPrepStepNumberByID(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return stepNumber;
	}

	/**
	 * Insert a new preparation step in the database. A preparation step is a pair
	 * of information. It contains the step number, that is the number that tells,
	 * when the action has to be performed in the cooking process and the
	 * description what to do.
	 *
	 * @param description Text that describes what to to in this preparation step.
	 * @param stepNumber  The number of this preparation step within the cooking
	 *                    process.
	 * @return The database ID of the inserted preparation step.
	 */
	public int insertPreparationStep(String description, int stepNumber) {

		try {
			preparationId = preparationModel.insertPreparationStep(description, stepNumber);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return preparationId;

	}

	// TODO: See model.
}
