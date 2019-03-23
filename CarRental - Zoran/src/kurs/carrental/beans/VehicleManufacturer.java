package kurs.carrental.beans;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VehicleManufacturer {
	
	private int manufacturerID;
	private String name;
	
	public String toString() {
		return name;
	}

}
