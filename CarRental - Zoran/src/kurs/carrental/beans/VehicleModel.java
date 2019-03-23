package kurs.carrental.beans;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VehicleModel {
	
	private int modelID;
	private String name;
	private int manufacturerID;
	
	public String toString() {
		return name;
	}

}
