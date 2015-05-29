package jdbc;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.sql.PreparedStatement;

public class Datenbank {

	public static Connection con = null;
	protected PreparedStatement ps = null;
	protected ResultSet rs = null;

	public static Connection getCon() throws SQLException {
		String user = "";
		String database = "";
		String password = "";
		// Informationen aus dem properties File auslesen
		try (FileReader reader = new FileReader("./config.properties")) {
			Properties properties = new Properties();
			properties.load(reader);
			user = properties.getProperty("user");
			password = properties.getProperty("passwort");
			database = properties.getProperty("database");

		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			// Treiber laden
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			System.out.println("Treiber Problem");
			e.printStackTrace();

		}

		try {
			// Verbindung mit der DB erstellen
			con = DriverManager.getConnection(database, user, password);

		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
		}

		if (con != null) {

		} else {
			System.out.println("Failed to make connection!");
		}

		return con;
	}

	public void setCon(Connection con) {
		this.con = con;
	}

	/**
	 * Schliesst die Verbindung mit der DB
	 */
	public void closeCon() {
		try {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (con != null) {
				con.close();
				con = null;
			}
		} catch (SQLException e) {

		}
	}
}
