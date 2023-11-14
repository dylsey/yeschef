package com.yeschef.api.daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MariaDbUtil {

	private static String connectionUrl = "jdbc:mariadb://localhost:3306/yeschef?user=root&password=Luna";

	public static Connection getConnection() {
		Connection connection = null;

		try {
			Class.forName("org.mariadb.jdbc.Driver");
			connection = DriverManager.getConnection(connectionUrl);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return connection;
	}

}
