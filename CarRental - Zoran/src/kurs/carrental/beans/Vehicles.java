package kurs.carrental.beans;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Vehicles {
	
	private int vehicleID;
	private String regNumber;
	private int doors;
	private int seats;
	private int volume;
	private int power;
	private int modelID;
	private int officeID;
	private int categoryID;
	private int fuelTypeID;
	private int statusID;
	private int transmissionTypeID;

}
