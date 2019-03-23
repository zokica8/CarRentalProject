package kurs.carrental.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionForUnitTests implements ConnectionInterface {
	
	private String urlTest = "jdbc:mysql://localhost/carrentaltest?useSSL=false&allowPublicKeyRetrieval=true&allowMultiQueries=true";
	private String user = "zoran";
	private String password = "MySQLzoranvasilic1!";
	private Connection conn = null;

	@Override
	public Connection returnConnection() throws SQLException {
		conn = DriverManager.getConnection(urlTest, user, password);
		return conn;
	}

	@Override
	public void close() throws SQLException {
		conn.close();
	}

}
