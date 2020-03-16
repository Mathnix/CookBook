package dbPreparation;

public class PreparationView {
	
	public PreparationController preparationController = new PreparationController();
	
	public void printDescription(int preparationStepId) {
		
		if(preparationController.getPrepDescriptionByID(preparationStepId).isEmpty()) {
			System.out.println("No Preparation with ID " + preparationStepId + " found \n");
		}
		else {
			System.out.println("Preparation Description with ID " + preparationStepId + " is:");
		System.out.println(preparationController.getPrepDescriptionByID(preparationStepId) + "\n");
		}
	}
	
	public void printStepNumber(int preparationStepId) {
		
		if(preparationController.getPrepStepNumberByID(preparationStepId) == -1) {
			System.out.println("No Preparation and thus noch Step Number with ID " + preparationStepId + " found");
		}
		else {
			System.out.println("Step number with ID " + preparationStepId + " is:");
		System.out.println(preparationController.getPrepStepNumberByID(preparationStepId) + "\n");
		}
	}

}
