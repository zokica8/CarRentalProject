package kurs.carrental.test;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import kurs.carrental.beans.Bookings;
import kurs.carrental.beans.Vehicles;
import kurs.carrental.connection.ConnectionForUnitTests;
import kurs.carrental.connection.ConnectionInterface;
import kurs.carrental.services.testmethods.BookingsServiceTestClass;
import kurs.carrental.services.testmethods.VehiclesServiceTestClass;

class CarRentalUnitTest {
	
	// fields(attributes, instance variables)
	private static ConnectionInterface connect;
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	
	// establishing connection with database before testing
	@BeforeAll
	public static void testingDatabaseConnection() throws SQLException {
		connect = new ConnectionForUnitTests();
		connect.returnConnection();
	}
	
	// test methods
	@Test
	@Disabled
	public void testingInsertingBookingsMethod() throws SQLException {
		BookingsServiceTestClass service = new BookingsServiceTestClass();
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
		
		assertEquals(booking, service.insertBooking(booking));
	}
	
	@Test
	public void testingUpdatingBookingsMethod() throws SQLException {
		BookingsServiceTestClass service = new BookingsServiceTestClass();
		Bookings booking = new Bookings();
		
		booking.setStatusID(3);
		booking.setBookingID(1);
		
		assertEquals(booking, service.updateBooking(booking));
	}
	
	@Test
	public void testingSearchingBookingsMethod() throws SQLException {
		BookingsServiceTestClass service = new BookingsServiceTestClass();
		List<Bookings> bookings = service.searchBookings(null, null, 
				null, null, LocalDateTime.parse("2019-02-03 13:09:31", formatter));
		assertEquals(1, bookings.size());
	}
	
	@Test
	@Disabled
	public void testingSearchingVehiclesMethod() throws SQLException {
		BookingsServiceTestClass service = new BookingsServiceTestClass();
		List<Vehicles> vehicles = service.searchVehicles(3, 3, null, null);
		assertEquals(6, vehicles.size());
	}
	
	@Test
	public void testingThrowingException() {
		BookingsServiceTestClass service = new BookingsServiceTestClass();
		Bookings booking = new Bookings();
		
		assertThrows(NullPointerException.class, new Executable() {
			@Override
			public void execute() throws Throwable {
				service.insertBooking(booking);
			}
		});
	}
	
	@Test
	public void testingThrowingSQLException() throws SQLException {
		BookingsServiceTestClass service = new BookingsServiceTestClass();
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
		
		assertThrows(SQLException.class, () -> {
			service.insertBooking(booking);
		});
	}
	
	@Test
	public void testingInsertingVehicleMethod() throws SQLException {
		VehiclesServiceTestClass service = new VehiclesServiceTestClass();
		Vehicles vehicle = new Vehicles();
		
		vehicle.setRegNumber("NS-401-KK");
		vehicle.setDoors(5);
		vehicle.setSeats(5);
		vehicle.setVolume(1880);
		vehicle.setPower(88);
		vehicle.setModelID(3);
		vehicle.setOfficeID(1);
		vehicle.setCategoryID(1);
		vehicle.setFuelTypeID(1);
		vehicle.setStatusID(1);
		vehicle.setTransmissionTypeID(1);
		
		assertEquals(vehicle, service.insertVehicle(vehicle));
	}
	
	@Test
	public void testingUpdatingVehicleMethod() throws SQLException {
		VehiclesServiceTestClass service = new VehiclesServiceTestClass();
		Vehicles vehicle = new Vehicles();
		
		vehicle.setStatusID(3);
		vehicle.setOfficeID(1);
		vehicle.setVehicleID(13);
		
		assertEquals(vehicle, service.updateVehicle(vehicle));
	}
	
	@Test
	public void testingDeletingVehiclesMethod() throws SQLException {
		VehiclesServiceTestClass service = new VehiclesServiceTestClass();
		Vehicles vehicle = new Vehicles();
		service.deleteVehicle(13);
		assertTrue(vehicle.getVehicleID() != 13);
	}
	
	// closing connection with database after executing methods
	@AfterAll
	public static void closingDatabaseConnection() throws SQLException {
		connect.close();
	}
	

}
