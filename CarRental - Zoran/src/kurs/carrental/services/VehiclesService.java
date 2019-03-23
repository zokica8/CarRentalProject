package kurs.carrental.services;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import kurs.carrental.beans.Vehicles;
import kurs.carrental.connection.ConnectionInterface;
import kurs.carrental.connection.ConnectionRegular;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class VehiclesService {

	// fields(attributes, instance variables)
	private static ConnectionInterface connect;

	// static method for connection
	public static void connect(ConnectionInterface connect) {
		VehiclesService.connect = connect;
	}

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

	// searchVehicles
	public List<Vehicles> searchVehicles(Integer categoryID, Integer officeName, Integer status, Integer manufacturer)
			throws SQLException {
		List<Vehicles> bookings = new ArrayList<>();

		String sqlSet = "set @categoryID = ?,  @officeID = ?, @statusID = ?, @manufacturerID = ?; ";

		String sql = "select v.VehicleID, v.RegNumber, v.Doors, v.Seats, v.Volume, v.Power, "
				+ "v.ModelID, model.ManufacturerID, v.FuelTypeID, v.TransmissionTypeID, "
				+ "v.CategoryID, v.StatusID, v.OfficeID from vehicle v "
				+ "inner join vehiclemodel model on v.ModelID = model.ModelID "
				+ "where (@categoryID is null or v.CategoryID = @categoryID) "
				+ "AND (@officeID is null or v.OfficeID = @officeID) "
				+ "AND (@statusID is null or v.StatusID = @statusID) "
				+ "AND (@manufacturerID is null or model.ManufacturerID = @manufacturerID)";

		Connection conn = connect.returnConnection();

		try (PreparedStatement pstmt = conn.prepareStatement(sqlSet);
				PreparedStatement newPstmt = conn.prepareStatement(sql)) {

			if (categoryID == null) {
				pstmt.setNull(1, Types.INTEGER);
			} else {
				pstmt.setInt(1, categoryID);
			}

			if (officeName == null) {
				pstmt.setNull(2, Types.INTEGER);
			} else {
				pstmt.setInt(2, officeName);
			}

			if (status == null) {
				pstmt.setNull(3, Types.INTEGER);
			} else {
				pstmt.setInt(3, status);
			}

			if (manufacturer == null) {
				pstmt.setNull(4, Types.INTEGER);
			} else {
				pstmt.setInt(4, manufacturer);
			}

			pstmt.execute();

			try (ResultSet rs = newPstmt.executeQuery()) {
				while (rs.next()) {
					Vehicles vehicle = new Vehicles();
					vehicle.setVehicleID(rs.getInt("VehicleID"));
					vehicle.setRegNumber(rs.getString("RegNumber"));
					vehicle.setDoors(rs.getInt("Doors"));
					vehicle.setSeats(rs.getInt("Seats"));
					vehicle.setVolume(rs.getInt("Volume"));
					vehicle.setPower(rs.getInt("Power"));
					vehicle.setCategoryID(rs.getInt("CategoryID"));
					vehicle.setOfficeID(rs.getInt("OfficeID"));
					vehicle.setStatusID(rs.getInt("StatusID"));
					vehicle.setModelID(rs.getInt("ModelID"));
					vehicle.setFuelTypeID(rs.getInt("FuelTypeID"));
					vehicle.setTransmissionTypeID(rs.getInt("TransmissionTypeID"));

					bookings.add(vehicle);
				}
			}
		}
		return bookings;
	}
	
	// + test methods

	// insert
	public static Vehicles testInsertVehicle(VehiclesService service) throws SQLException {
		Vehicles vehicle = new Vehicles();

		vehicle.setRegNumber("NS-401-KK");
		vehicle.setDoors(5);
		vehicle.setSeats(5);
		vehicle.setVolume(1880);
		vehicle.setPower(88);
		vehicle.setModelID(3);
		vehicle.setOfficeID(3);
		vehicle.setCategoryID(3);
		vehicle.setFuelTypeID(1);
		vehicle.setStatusID(1);
		vehicle.setTransmissionTypeID(1);

		return service.insertVehicle(vehicle);
	}

	// update
	public static Vehicles testUpdateVehicle(VehiclesService service) throws SQLException {
		Vehicles vehicle = new Vehicles();

		vehicle.setStatusID(3);
		vehicle.setOfficeID(1);
		vehicle.setVehicleID(14);

		return service.updateVehicle(vehicle);
	}

	// delete
	public static void testDeleteVehicle(VehiclesService service) throws SQLException {
		service.deleteVehicle(14);
	}

	// search Vehicles
	public static void testSearchVehicles(VehiclesService service) throws SQLException {
		List<Vehicles> vehicles = service.searchVehicles(3, 3, null, null);
		for (Vehicles vehicle : vehicles) {
			log.info(vehicle);
		}
	}

	public static void main(String[] args) {

		try {
			VehiclesService service = new VehiclesService();
			connect(new ConnectionRegular());

			testSearchVehicles(service);
			// System.out.println();
			// testInsertVehicle(service);
			// System.out.println();
			// testUpdateVehicle(service);
			// System.out.println();
			//testDeleteVehicle(service);

		} catch (SQLException e) {
			log.error(e.getLocalizedMessage());
			log.error(e.getErrorCode() + " " + e.getSQLState());
		} finally {
			try {
				connect.close();
			} catch (SQLException e) {
				log.error(e.getLocalizedMessage());
				log.error(e.getErrorCode() + " " + e.getSQLState());
			}
		}

	}

}
