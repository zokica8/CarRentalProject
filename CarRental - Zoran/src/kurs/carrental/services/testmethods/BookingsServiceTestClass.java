package kurs.carrental.services.testmethods;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import kurs.carrental.beans.Bookings;
import kurs.carrental.beans.Vehicles;
import kurs.carrental.connection.ConnectionForUnitTests;
import kurs.carrental.connection.ConnectionInterface;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class BookingsServiceTestClass {
	
	// fields(attributes, instance variables)
	private static ConnectionInterface connect = new ConnectionForUnitTests();

	// insertBooking
	public Bookings insertBooking(Bookings booking) throws SQLException {

		String sql = "insert into booking (DriverName, DriverAge, DriverEmail, DriverPhone, PickupTime, "
				+ "DropoffTime, TotalPrice, VehicleCategoryID, PickupOfficeID, DropoffOfficeID, StatusID)"
				+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?),";

		try (PreparedStatement pstmt = connect.returnConnection().prepareStatement(sql,
				Statement.RETURN_GENERATED_KEYS)) {
			pstmt.setString(1, booking.getDriverName());
			pstmt.setInt(2, booking.getDriverAge());
			pstmt.setString(3, booking.getDriverEmail());
			pstmt.setString(4, booking.getDriverPhone());
			pstmt.setTimestamp(5, Timestamp.valueOf(booking.getPickupTime()));
			pstmt.setTimestamp(6, Timestamp.valueOf(booking.getDropoffTime()));
			pstmt.setDouble(7, booking.getPrice());
			pstmt.setInt(8, booking.getVehicleCategoryID());
			pstmt.setInt(9, booking.getPickupOfficeID());
			pstmt.setInt(10, booking.getDropoffOfficeID());
			pstmt.setInt(11, booking.getStatusID());

			int insert = pstmt.executeUpdate();
			log.info("Record inserted! " + insert);
			try (ResultSet rs = pstmt.getGeneratedKeys()) {
				rs.next();
				int value = rs.getInt(1);
				log.info(value);
			}
		} 
//		catch (SQLException e) {
//			log4j2.error("Record insert not successful!!");
//			log4j2.error(e.getMessage());
//		}

		return booking;
	}

	// updateBooking
	public Bookings updateBooking(Bookings booking) throws SQLException {

		String sql = "update booking set StatusID = ? where BookingID = ?";

		try (PreparedStatement pstmt = connect.returnConnection().prepareStatement(sql)) {
			pstmt.setInt(1, booking.getStatusID());
			pstmt.setInt(2, booking.getBookingID());

			int update = pstmt.executeUpdate();
			log.info("Record updated! " + update + "\n");

		} catch (SQLException e) {
			log.error("Record update not successful!!");
			log.error(e.getMessage());
		}

		return booking;
	}

	// searchBookings
	public List<Bookings> searchBookings(String keyword, Integer bookingID, Integer statusID, LocalDateTime pickup,
			LocalDateTime dropoff) throws SQLException {

		List<Bookings> bookings = new ArrayList<>();

		// main sql statement
		String sql = "select DriverName, DriverAge, DriverEmail, DriverPhone, "
				+ "TotalPrice, VehicleCategoryID, PickupOfficeID, DropoffOfficeID, "
				+ "BookingID, StatusID, PickupTime, DropoffTime from booking where " + "(DriverName like ?)";

		// additional sql statement
		if (bookingID != null) {
			sql = sql + " AND BookingID = '" + bookingID + "'";
		}

		if (statusID != null) {
			sql = sql + " AND StatusID = '" + statusID + "'";
		}

		if (pickup != null) {
			sql = sql + " AND PickupTime = '" + pickup + "'";
		}

		if (dropoff != null) {
			sql = sql + " AND DropoffTime = '" + dropoff + "'";
		}

		try (PreparedStatement pstmt = connect.returnConnection().prepareStatement(sql)) {
			String kw = keyword == null ? "" : keyword;
			pstmt.setString(1, kw + "%");
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					Bookings booking = new Bookings();
					booking.setDriverName(rs.getString("DriverName"));
					booking.setDriverAge(rs.getInt("DriverAge"));
					booking.setDriverEmail(rs.getString("DriverEmail"));
					booking.setDriverPhone(rs.getString("DriverPhone"));
					booking.setBookingID(rs.getInt("BookingID"));
					booking.setStatusID(rs.getInt("StatusID"));
					booking.setPickupTime(rs.getTimestamp("PickupTime").toLocalDateTime());
					booking.setDropoffTime(rs.getTimestamp("DropoffTime").toLocalDateTime());
					booking.setPrice(rs.getDouble("TotalPrice"));
					booking.setVehicleCategoryID(rs.getInt("VehicleCategoryID"));
					booking.setPickupOfficeID(rs.getInt("PickupOfficeID"));
					booking.setDropoffOfficeID(rs.getInt("DropoffOfficeID"));
					bookings.add(booking);
				}
			}
		} catch (SQLException e) {
			log.error("Search not working!");
			log.error(e.getLocalizedMessage() + " " + e.getCause());
		}

		return bookings;
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

}
