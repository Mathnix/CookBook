package dbMeasurement;

public class MeasurementView {
	
	public MeasurementController measurementController = new MeasurementController();
	
	public void printMeasurementUnitName(int measurementId) {
		
		if(measurementController.getMeasurementUnitNameByID(measurementId).isEmpty()) {
			System.out.println("No Measurement unit with ID " + measurementId + " found.");
		}
		else {
		System.out.println("Measurement Unit with ID " + measurementId + " is:");
		System.out.println(measurementController.getMeasurementUnitNameByID(measurementId) + "\n");
		}
	}
	
	public void printMeasurementId(String measurementUnitName) {
		
		if(measurementController.getMeasurementIDByName(measurementUnitName) == -1) {
			System.out.println("No Measurement with Name " + measurementUnitName + " found.");
		}
		else {
			System.out.println("Unit " + measurementUnitName + " has ID:");
		System.out.println(measurementController.getMeasurementIDByName(measurementUnitName)+ "\n");
		}
	}

}
