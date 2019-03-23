package kurs.carrental.beans.storedprocedures;

import java.util.Date;

public class Booking {
	private int bookingId;
	private int vehicleCategoryId;
	private int pickupOfficeId;
	private int dropoffOfficeId;
	private int statusId;
	private String driverName;
	private String driverEmail;
	private String driverPhone;
	private int driverAge;
	private Date pickupTime;
	private Date dropoffTime;
	private double totalPrice;
	
	public int getBookingId() {
		return bookingId;
	}
	public void setBookingId(int bokingId) {
		this.bookingId = bokingId;
	}
	public int getVehicleCategoryId() {
		return vehicleCategoryId;
	}
	public void setVehicleCategoryId(int vehicleCategoryId) {
		this.vehicleCategoryId = vehicleCategoryId;
	}
	public int getPickupOfficeId() {
		return pickupOfficeId;
	}
	public void setPickupOfficeId(int pickupOfficeId) {
		this.pickupOfficeId = pickupOfficeId;
	}
	public int getDropoffOfficeId() {
		return dropoffOfficeId;
	}
	public void setDropoffOfficeId(int dropoffOfficeId) {
		this.dropoffOfficeId = dropoffOfficeId;
	}
	public int getStatusId() {
		return statusId;
	}
	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}
	public String getDriverName() {
		return driverName;
	}
	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}
	public String getDriverEmail() {
		return driverEmail;
	}
	public void setDriverEmail(String driverEmail) {
		this.driverEmail = driverEmail;
	}
	public String getDriverPhone() {
		return driverPhone;
	}
	public void setDriverPhone(String driverPhone) {
		this.driverPhone = driverPhone;
	}
	public int getDriverAge() {
		return driverAge;
	}
	public void setDriverAge(int driverAge) {
		this.driverAge = driverAge;
	}
	public Date getPickupTime() {
		return pickupTime;
	}
	public void setPickupTime(Date pickupTime) {
		this.pickupTime = pickupTime;
	}
	public Date getDropoffTime() {
		return dropoffTime;
	}
	public void setDropoffTime(Date dropoffTime) {
		this.dropoffTime = dropoffTime;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	@Override
	public String toString() {
		return bookingId + " " + driverName + " "  + driverEmail + " "  + driverPhone + " "  + driverAge + " "  + pickupTime + " "  + dropoffTime;
	}

}
