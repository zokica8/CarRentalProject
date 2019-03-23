package kurs.carrental.beans;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
public class Bookings {
	
	@ToString.Exclude
	private int bookingID;
	private String driverName;
	private int driverAge;
	private String driverEmail;
	private String driverPhone;
	private LocalDateTime pickupTime;
	private LocalDateTime dropoffTime;
	private double price;
	private int vehicleCategoryID;
	private int pickupOfficeID;
	private int dropoffOfficeID;
	private int statusID;

}
