package kurs.carrental.beans;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Status {
	
	private int statusID;
	private String status;
	
	public String toString() {
		return status;
	}

}
