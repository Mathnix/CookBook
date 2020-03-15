package recipeClasses;

public class Ingredient {
	
	private String name;
	private double amount;
	private Measurements measure;
	
	
	
	public Ingredient() {
		
	}
	
	public Ingredient(String name, double amount, Measurements measure) {
		this.name = name;
		this.amount = amount;
		this.measure =measure;
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Measurements getMeasure() {
		return measure;
	}

	public void setMeasure(Measurements measure) {
		this.measure = measure;
	}
	
	

}
