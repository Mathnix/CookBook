package recipeClasses;

public class PreparationStep {

	private String  shortInstruction;
	private String instruction;
	private int numberOfStep;
	
	public PreparationStep() {
		
	}
	
	public PreparationStep(String shortInstruction, String instruction, int numberOfStep) {
		this.shortInstruction = shortInstruction;
		this.instruction = instruction;
		this.numberOfStep = numberOfStep;
		
	}

	public String getShortInstruction() {
		return shortInstruction;
	}

	public void setShortInstruction(String shortInstruction) {
		this.shortInstruction = shortInstruction;
	}

	public String getInstruction() {
		return instruction;
	}

	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}

	public int getNumberOfStep() {
		return numberOfStep;
	}

	public void setNumberOfStep(int numberOfStep) {
		this.numberOfStep = numberOfStep;
	}
}

