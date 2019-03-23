package kurs.carrental.services.storedprocedures;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
//singleton pattern
public class ConnectionSingleton {
	
	private static String url = "jdbc:mysql://localhost/carrental?useSSL=false&allowPublicKeyRetrieval=true&allowMultiQueries=true";
	private static String user = "zoran";
	private static String password = "MySQLzoranvasilic1!";
	private static Connection conn;
	
	private ConnectionSingleton() {
		
	}
	
	public static Connection returnConnection() throws SQLException {
		if(conn == null) {
			conn = DriverManager.getConnection(url, user, password);
		}
		return conn;
	}

}
