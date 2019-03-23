package kurs.carrental.beans;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Office {
	
	private int officeID;
	private String name;
	private int cityID;
	
	public String toString() {
		return name;
	}

}
