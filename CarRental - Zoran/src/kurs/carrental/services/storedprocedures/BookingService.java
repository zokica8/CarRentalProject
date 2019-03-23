package kurs.carrental.services.storedprocedures;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import kurs.carrental.beans.storedprocedures.Booking;

public class BookingService {

	private String dbUrl;
	private String user;
	private String pswd;

	private Connection conn;

	public BookingService(String url, String usr, String psw) throws SQLException {
		dbUrl = url;
		user = usr;
		pswd = psw;
	}
	
	public void createConnection(boolean autoCommit) throws SQLException {
		conn = DriverManager.getConnection(dbUrl, user, pswd);
		conn.setAutoCommit(autoCommit);
	}
	
	public void commit() throws SQLException {
		conn.commit();
	}
	
	public void rollback() throws SQLException {
		conn.rollback();
	}
	
	public int insertBooking(Booking bk) throws SQLException {
		try (PreparedStatement stmt = conn.prepareStatement(
				"INSERT INTO booking(driverName, driverEmail, driverPhone, driverAge, " + 
				"pickupTime, dropoffTime, totalPrice, vehicleCategoryId, pickupOfficeId, dropoffOfficeId, statusId) " + 
				"VALUES (?,?,?,?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS)) {

			stmt.setString(1, bk.getDriverName());
			stmt.setString(2, bk.getDriverEmail());
			stmt.setString(3, bk.getDriverPhone());
			stmt.setInt(4, bk.getDriverAge());
			stmt.setTimestamp(5, new Timestamp(bk.getPickupTime().getTime()));
			stmt.setTimestamp(6, new Timestamp(bk.getDropoffTime().getTime()));
			stmt.setDouble(7, bk.getTotalPrice());
			stmt.setInt(8, bk.getVehicleCategoryId());
			stmt.setInt(9, bk.getPickupOfficeId());
			stmt.setInt(10, bk.getDropoffOfficeId());
			stmt.setInt(11, bk.getStatusId());

			int res = stmt.executeUpdate();
			
			ResultSet rs = stmt.getGeneratedKeys();

		    if (rs.next()) {
		        bk.setBookingId(rs.getInt(1));
		    } else {
		    	throw new SQLException("Couldn't generate PK!!!");
		    }
		    
		    return res;

		} catch (SQLException ex) {
			throw ex;
		}
	}
	
//	public List<Booking> searchBookings(Integer bookingId, String keyword, Date from, Date to) throws SQLException {
//		
//		List<Booking> bookings = new ArrayList<Booking>();
//
//		String sql = "SET @bookingId = ?, @from = ?, @to = ?; ";
//		String sql2 = "SELECT bookingId, vehicleCategoryId, pickupOfficeId, dropoffOfficeId, statusId, " +
//				"driverName, driverEmail, driverPhone, driverAge, pickupTime, dropoffTime, totalPrice " +
//				"FROM booking " +
//				"WHERE (@bookingId IS NULL OR bookingId = @bookingId) " +
//				"AND (@from IS NULL OR pickupTime >= @from) " +
//				"AND (@to IS NULL OR pickupTime <= @to) " +
//				"AND (driverName LIKE ? OR driverEmail LIKE ?) ";
//		
//	
//		
//				
//		try (PreparedStatement stm = conn.prepareStatement(sql);
//				PreparedStatement stmt = conn.prepareStatement(sql2)) {
//			String kw = keyword == null ? "" : keyword;
//			if (bookingId == null) stm.setNull(1, Types.INTEGER); 
//			else stm.setInt(1, bookingId);
//			if (from == null) stm.setNull(2, Types.DATE); 
//			else stm.setDate(2, new java.sql.Date(from.getTime()));
//			if (to == null) stm.setNull(3, Types.DATE); 
//			else stm.setDate(3, new java.sql.Date(to.getTime()));
//			
//			stmt.setString(1, "%" + kw + "%");
//			stmt.setString(2, "%" + kw + "%");
//			
//			boolean ok = stm.execute();
//			ok = stmt.execute();
//			boolean b = ok;
//			ResultSet rs = stmt.getResultSet();
//			while(rs.next()) {
//				Booking bk = new Booking();
//				bk.setBookingId(rs.getInt(1));
//				bk.setVehicleCategoryId(rs.getInt(2));
//				bk.setPickupOfficeId(rs.getInt(3));
//				bk.setDropoffOfficeId(rs.getInt(4));
//				bk.setStatusId(rs.getInt(5));
//				bk.setDriverName(rs.getString(6));
//				bk.setDriverEmail(rs.getString(7));
//				bk.setDriverPhone(rs.getString(8));
//				bk.setDriverAge(rs.getInt(9));
//				bk.setPickupTime(rs.getDate(10));
//				bk.setDropoffTime(rs.getDate(11));
//				
//				bookings.add(bk);
//			}
//			
//			return bookings;
//
//		} catch (SQLException ex) {
//			throw ex;
//		}
//	}
	
public List<Booking> searchBookings(Integer bookingId, String keyword, Date from, Date to) throws SQLException {
		
		List<Booking> bookings = new ArrayList<Booking>();

		String sql = "{call SearchBookings(?,?,?,?)}";
				
		try (CallableStatement stmt = conn.prepareCall(sql)) {
			if (bookingId == null) stmt.setNull(1, Types.INTEGER); else stmt.setInt(1, bookingId);
			if (from == null) stmt.setNull(2, Types.DATE); else stmt.setDate(2, new java.sql.Date(from.getTime()));
			if (to == null) stmt.setNull(3, Types.DATE); else stmt.setDate(3, new java.sql.Date(to.getTime()));
			if (keyword == null) stmt.setNull(4, Types.VARCHAR); else stmt.setString(4, keyword);			
			
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				Booking bk = new Booking();
				bk.setBookingId(rs.getInt(1));
				bk.setVehicleCategoryId(rs.getInt(2));
				bk.setPickupOfficeId(rs.getInt(3));
				bk.setDropoffOfficeId(rs.getInt(4));
				bk.setStatusId(rs.getInt(5));
				bk.setDriverName(rs.getString(6));
				bk.setDriverEmail(rs.getString(7));
				bk.setDriverPhone(rs.getString(8));
				bk.setDriverAge(rs.getInt(9));
				bk.setPickupTime(rs.getDate(10));
				bk.setDropoffTime(rs.getDate(11));
				
				bookings.add(bk);
			}
			
			return bookings;

		} catch (SQLException ex) {
			throw ex;
		}
	}


	public static void main(String[] args) {
		try {
			BookingService pse = new BookingService("jdbc:mysql://localhost/carrental", "zoran", "MySQLzoranvasilic1!");
			pse.createConnection(true);
			
			Booking bk = new Booking();
			bk.setVehicleCategoryId(1);
			bk.setPickupOfficeId(1);
			bk.setDropoffOfficeId(1);
			bk.setStatusId(1);
			bk.setDriverName("Mika");
			bk.setDriverEmail("Mikic");
			bk.setDriverPhone("+123456789");
			bk.setDriverAge(33);
			bk.setPickupTime(new Date());
			bk.setDropoffTime(new Date());
			
			@SuppressWarnings("unused")
			int res = pse.insertBooking(bk);
			
			System.out.println(bk);
			
			List<Booking> bks = pse.searchBookings(bk.getBookingId(), null, null, null);
			for (Booking b : bks) {
				System.out.println(b);
			}
			
			System.out.println();
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

}
