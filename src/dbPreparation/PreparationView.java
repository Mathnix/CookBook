package dbPreparation;

//TODO: Concrete GUI.

/**
 * Test Status for all actions on the database affecting the preparation.
 * 
 * In this version: Only prints the return values!
 * 
 * @author lena
 *
 */
public class PreparationView {

	public PreparationController preparationController = new PreparationController();

	public void printDescription(int preparationStepId) {

		if (preparationController.getPrepDescriptionByID(preparationStepId).isEmpty()) {
			System.out.println("No Preparation with ID " + preparationStepId + " found \n");
		} else {
			System.out.println("Preparation Description with ID " + preparationStepId + " is:");
			System.out.println(preparationController.getPrepDescriptionByID(preparationStepId) + "\n");
		}
	}

	public void printStepNumber(int preparationStepId) {

		if (preparationController.getPrepStepNumberByID(preparationStepId) == -1) {
			System.out.println("No Preparation and thus noch Step Number with ID " + preparationStepId + " found");
		} else {
			System.out.println("Step number with ID " + preparationStepId + " is:");
			System.out.println(preparationController.getPrepStepNumberByID(preparationStepId) + "\n");
		}
	}

	public void printInsertPreparationStep(String description, int stepNumber) {

		int rs = preparationController.insertPreparationStep(description, stepNumber);

		if (rs == -1) {
			System.out.println("No INSERT for " + description + " with Stepnumber:" + stepNumber + " possible.");
		} else {
			System.out.println("Preparation with description " + description + " and Stepnumber:" + stepNumber
					+ " was inserted and has ID:");
			System.out.println(rs + "\n");
		}
	}

}
