package dbMeasurement;

//TODO: Concrete GUI.

/**
 * Test Status for all actions on the database affecting the measurement.
 * 
 * In this version: Only prints the return values!
 * 
 * @author lena
 *
 */

public class MeasurementView {

	public MeasurementController measurementController = new MeasurementController();

	public void printMeasurementUnitName(int measurementId) {

		if (measurementController.getMeasurementUnitNameByID(measurementId).isEmpty()) {
			System.out.println("No Measurement unit with ID " + measurementId + " found.");
		} else {
			System.out.println("Measurement Unit with ID " + measurementId + " is:");
			System.out.println(measurementController.getMeasurementUnitNameByID(measurementId) + "\n");
		}
	}

	public void printMeasurementID(String measurementUnitName) {

		if (measurementController.getMeasurementIDByName(measurementUnitName) == -1) {
			System.out.println("No Measurement with Name " + measurementUnitName + " found.");
		} else {
			System.out.println("Unit " + measurementUnitName + " has ID:");
			System.out.println(measurementController.getMeasurementIDByName(measurementUnitName) + "\n");
		}
	}

	public void printInsertedMeasurementUnitID(String measurementUnitName) {

		int rs = measurementController.insertMeasurementUnit(measurementUnitName);

		if (rs == -1) {
			System.out.println("No INSERT for " + measurementUnitName + " possible.");
		} else {
			System.out.println("Measurement " + measurementUnitName + " was inserted and has ID:");
			System.out.println(rs + "\n");
		}

	}
}
