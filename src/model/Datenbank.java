package model;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Datenbank {
	public static void main(String[] args) {
		Connection connection=null;
		String user = "";
		String database = "";
		String password = "";
		try (FileReader reader = new FileReader("config.properties")) {
			Properties properties = new Properties();
			properties.load(reader);
			user = properties.getProperty("user");
			password = properties.getProperty("passwort");
			database = properties.getProperty("database");

		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Treiber Problem");
			e.printStackTrace();
			return;
		}

	
		try {
			connection = DriverManager.getConnection(database, user, password);

		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return;
		}

		if (connection != null) {
			System.out.println("Connected");
		} else {
			System.out.println("Failed to make connection!");
		}
	}
}
