package kurs.carrental.beans;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FuelType {
	
	private int fuelTypeID;
	private String fuelType;
	
	public String toString() {
		return fuelType;
	}

}
