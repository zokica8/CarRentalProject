package kurs.carrental.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import kurs.carrental.beans.Bookings;
import kurs.carrental.beans.Office;
import kurs.carrental.beans.Status;
import kurs.carrental.beans.VehicleCategory;
import kurs.carrental.beans.Vehicles;
import kurs.carrental.connection.ConnectionInterface;
import kurs.carrental.connection.ConnectionRegular;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class BookingsService {

	// fields (attributes, instance variables)
	private static ConnectionInterface connect;
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	// static method for connection
	public static void connect(ConnectionInterface connect) {
		BookingsService.connect = connect;
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

	// insertBooking
	public Bookings insertBooking(Bookings booking) throws SQLException {

		String sql = "insert into booking (DriverName, DriverAge, DriverEmail, DriverPhone, PickupTime, "
				+ "DropoffTime, TotalPrice, VehicleCategoryID, PickupOfficeID, DropoffOfficeID, StatusID)"
				+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

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
		} catch (SQLException e) {
			log.error("Record insert not successful!!");
			log.error(e.getMessage());
		}

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

	// helper methods
	public List<Office> getOffices(String pk, String name, String table) throws SQLException {
		List<Office> offices = new ArrayList<>();
		String sql = String.format("select %s, %s from %s", pk, name, table);

		ConnectionInterface connect = new ConnectionRegular();
		try (PreparedStatement pstmt = connect.returnConnection().prepareStatement(sql)) {
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					Office office = new Office();
					office.setOfficeID(rs.getInt("OfficeID"));
					office.setName(rs.getString("Name"));

					offices.add(office);
				}
			}
		}

		return offices;
	}

	public List<VehicleCategory> getCategories(String pk, String name, String table) throws SQLException {
		List<VehicleCategory> categories = new ArrayList<>();
		String sql = String.format("select %s, %s from %s", pk, name, table);

		ConnectionInterface connect = new ConnectionRegular();
		try (PreparedStatement pstmt = connect.returnConnection().prepareStatement(sql)) {
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					VehicleCategory category = new VehicleCategory();
					category.setCategoryID(rs.getInt("CategoryID"));
					// category.setName(rs.getString("Name"));
					category.setDescription(rs.getString("Description"));

					categories.add(category);
				}
			}
		}

		return categories;
	}
	
	public List<Status> getAllStatuses(String pk, String name, String table) throws SQLException {
		List<Status> statuses = new ArrayList<>();
		String sql = String.format("select %s, %s from %s", pk, name, table);
		
		ConnectionInterface connect = new ConnectionRegular();
		try(PreparedStatement pstmt = connect.returnConnection().prepareStatement(sql)) {
			try(ResultSet rs = pstmt.executeQuery()) {
				while(rs.next()) {
					Status status = new Status();
					status.setStatusID(rs.getInt("StatusID"));
					status.setStatus(rs.getString("Status"));
					
					statuses.add(status);
				}
			}
		}
		return statuses;
	}

	// + test methods

	// insert
	public static Bookings testInsertBooking(BookingsService service) throws SQLException {
		Bookings booking = new Bookings();

		booking.setDriverName("Zoran");
		booking.setDriverAge(29);
		booking.setDriverEmail("zokivasilic8@gmail.com");
		booking.setDriverPhone("+381631968643");
		booking.setPickupTime(LocalDateTime.now());
		booking.setDropoffTime(LocalDateTime.now().plusDays(3));
		booking.setPrice(132.21);
		booking.setVehicleCategoryID(1);
		booking.setPickupOfficeID(1);
		booking.setDropoffOfficeID(1);
		booking.setStatusID(1);

		return service.insertBooking(booking);
	}

	// update
	public static Bookings testUpdateBooking(BookingsService service) throws SQLException {
		Bookings booking = new Bookings();

		booking.setStatusID(3);
		booking.setBookingID(5);

		return service.updateBooking(booking);
	}

	// search
	public static void testSearchBookings(BookingsService service) throws SQLException {
		List<Bookings> bookings = service.searchBookings("Z", null, null, null,
				LocalDateTime.parse("2019-02-03 12:35:53", formatter));
		for (Bookings booking : bookings) {
			log.info(booking);
		}
	}

	// search Vehicles
	public static void testSearchVehicles(BookingsService service) throws SQLException {
		List<Vehicles> vehicles = service.searchVehicles(3, 3, null, null);
		for (Vehicles vehicle : vehicles) {
			log.info(vehicle);
		}
	}

	public static void main(String[] args) {

		try {
			BookingsService service = new BookingsService();
			connect(new ConnectionRegular());

			testSearchBookings(service);
			// System.out.println();
			//testInsertBooking(service);
			// System.out.println();
			// testUpdateBooking(service);
			// System.out.println();
			// testSearchVehicles(service);
			System.out.println();

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
