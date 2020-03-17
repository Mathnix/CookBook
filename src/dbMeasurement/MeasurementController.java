package dbMeasurement;

import java.sql.SQLException;

/**
 * Handles all actions on the database affecting the measurements.
 * 
 * @author lena
 *
 */
public class MeasurementController {

	public MeasurementModel measurementModel = new MeasurementModel();

	public String measurementUnitName;
	public int measurementId;

	/**
	 * Get the name of the measurement/unit with repective database ID.
	 * 
	 * @param id The ID of the measurement in the database.
	 * @return The name of the corresponding measurement/unit.
	 */
	public String getMeasurementUnitNameByID(int id) {

		try {
			measurementUnitName = measurementModel.getMeasurementUnitNameByID(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return measurementUnitName;
	}

	/**
	 * Get the ID corresponding to a measurement in the database.
	 * 
	 * @param name The name of the measurement/unit.
	 * @return The ID of the measurement/unit corresponding in the database.
	 */
	public int getMeasurementIDByName(String name) {

		try {
			measurementId = measurementModel.getMeasurementIDByName(name);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return measurementId;
	}

	/**
	 * Insert a new measurement/unit in the database. Returns its ID.
	 * 
	 * @param name The name of the measurement/unit.
	 * @return The ID of the measurement/unit in the database.
	 */
	public int insertMeasurementUnit(String name) {

		try {
			measurementId = measurementModel.insertMeasurementUnit(name);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return measurementId;
	}

	// TODO: See model.

}
