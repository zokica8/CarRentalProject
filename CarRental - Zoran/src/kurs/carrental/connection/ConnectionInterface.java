package kurs.carrental.connection;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionInterface {
	
	Connection returnConnection() throws SQLException;
	void close() throws SQLException;

}
