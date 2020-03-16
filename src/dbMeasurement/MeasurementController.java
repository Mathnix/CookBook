package dbMeasurement;

import java.sql.SQLException;


public class MeasurementController {
	
	public MeasurementModel measurementModel = new MeasurementModel();

	public String measurementUnitName;
	public int measurementId;

	public String getMeasurementUnitNameByID(int id) {

		try {
			measurementUnitName = measurementModel.getMeasurementUnitNameByID(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return measurementUnitName;
	}

	public int getMeasurementIDByName(String name) {

		try {
			measurementId = measurementModel.getMeasurementIDByName(name);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return measurementId;
	}

}
