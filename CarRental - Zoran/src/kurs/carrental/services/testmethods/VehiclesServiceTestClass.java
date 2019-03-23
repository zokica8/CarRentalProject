package kurs.carrental.services.testmethods;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;

import kurs.carrental.beans.Vehicles;
import kurs.carrental.connection.ConnectionForUnitTests;
import kurs.carrental.connection.ConnectionInterface;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class VehiclesServiceTestClass {

	private static ConnectionInterface connect = new ConnectionForUnitTests();

	// insert
	public Vehicles insertVehicle(Vehicles vehicle) throws SQLException {

		String sql = "{call insertVehicle(?,?,?,?,?,?,?,?,?,?,?,?)}";

		try (CallableStatement cstmt = connect.returnConnection().prepareCall(sql)) {
			cstmt.setString(1, vehicle.getRegNumber());
			cstmt.setInt(2, vehicle.getDoors());
			cstmt.setInt(3, vehicle.getSeats());
			cstmt.setInt(4, vehicle.getVolume());
			cstmt.setInt(5, vehicle.getPower());
			cstmt.setInt(6, vehicle.getModelID());
			cstmt.setInt(7, vehicle.getOfficeID());
			cstmt.setInt(8, vehicle.getCategoryID());
			cstmt.setInt(9, vehicle.getFuelTypeID());
			cstmt.setInt(10, vehicle.getStatusID());
			cstmt.setInt(11, vehicle.getTransmissionTypeID());

			cstmt.registerOutParameter(12, Types.INTEGER);

			int insert = cstmt.executeUpdate();
			int pk = cstmt.getInt(12);
			vehicle.setVehicleID(pk);
			log.info("Record inserted! " + insert);
			log.info("Value of the primary key: " + pk);
		} catch (SQLException e) {
			log.error("Record insert not successful!!");
			log.error(e.getMessage());
		}

		return vehicle;
	}

	// update
	public Vehicles updateVehicle(Vehicles vehicle) throws SQLException {

		String sql = "{call updateVehicle(?,?,?)}";

		try (CallableStatement cstmt = connect.returnConnection().prepareCall(sql)) {
			cstmt.setInt(1, vehicle.getStatusID());
			cstmt.setInt(2, vehicle.getOfficeID());
			cstmt.setInt(3, vehicle.getVehicleID());

			int update = cstmt.executeUpdate();
			log.info("Vehicle updated! " + update);

		} catch (SQLException e) {
			log.error("Record update not successful!!");
			log.error(e.getMessage());
		}

		return vehicle;
	}

	// delete
	public void deleteVehicle(int vehicleID) throws SQLException {

		Vehicles vehicle = new Vehicles();

		vehicle.setVehicleID(vehicleID);

		String sql = "{call deleteVehicle(?)}";

		try (CallableStatement cstmt = connect.returnConnection().prepareCall(sql)) {
			cstmt.setInt(1, vehicle.getVehicleID());

			int delete = cstmt.executeUpdate();
			log.info("Vehicle deleted from the database! Not usable anymore! " + delete + "\n");

		} catch (SQLException e) {
			log.error("Record delete not successful!!");
			log.error(e.getMessage());
		}

	}

}
