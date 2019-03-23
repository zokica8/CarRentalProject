package kurs.carrental.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionRegular implements ConnectionInterface {
	
	private String url = "jdbc:mysql://localhost/carrental?useSSL=false&allowPublicKeyRetrieval=true&allowMultiQueries=true";
	private String user = "zoran";
	private String password = "MySQLzoranvasilic1!";
	private Connection conn = null;

	@Override
	public Connection returnConnection() throws SQLException {
		conn = DriverManager.getConnection(url, user, password);
		return conn;
	}

	@Override
	public void close() throws SQLException {
		conn.close();
	}

}
