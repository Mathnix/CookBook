package dbPreparation;

import java.sql.SQLException;

public class PreparationController {
	
	PreparationModel preparationModel = new PreparationModel();
	
	public String description;
	public int stepNumber;
	
	public String getPrepDescriptionByID(int id) {

		try {
			description = preparationModel.getPrepDescriptionByID(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return description;
	}
	
	public int getPrepStepNumberByID(int id) {

		try {
			stepNumber = preparationModel.getPrepStepNumberByID(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return stepNumber;
	}

}
