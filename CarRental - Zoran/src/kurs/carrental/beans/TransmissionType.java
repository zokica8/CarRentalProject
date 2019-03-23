package kurs.carrental.beans;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TransmissionType {
	
	private int transmissionTypeID;
	private String name;
	
	public String toString() {
		return name;
	}

}
