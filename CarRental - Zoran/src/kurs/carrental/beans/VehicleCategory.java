package kurs.carrental.beans;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VehicleCategory {
	
	private int categoryID;
	private String name;
	private String description;
	
	public String toString() {
		return description;
	}

}
