package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class ZeitJDBCDAO extends Datenbank implements ZeitDAO {
	private static Zeit z;
	private Connection con = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	private String s;

	@Override
	public Zeit arbeitszeitauslesen() throws SQLException {
		z = new Zeit();
		String sql = "SELECT idZeit, timestamp, ArbeiterID FROM zeit WHERE ArbeiterID ='45-459-5415'";
		con = getCon();
		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();

		while (rs.next()) {
			z.setIdZeit(rs.getString("idZeit"));
			z.setTimestamp(rs.getTimestamp("timestamp"));
			z.setArbeiterID(rs.getString("ArbeiterID"));
			System.out.println(z.getTimestamp());
			break;
		}
		return z;

	}

	@Override
	public Zeit zeiteintragen() throws SQLException {
		z = new Zeit();
		String sql = "INSERT INTO `zeit`(`ArbeiterID`) VALUES (?)";
		con = getCon();
		ps = con.prepareStatement(sql);
		ps.setString(1, z.getArbeiterID());
		ps.executeUpdate();
		return null;
	}

	@Override
	public Zeit totalberechnen() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public static Zeit getZ() {
		return z;
	}

	public static void setZ(Zeit z) {
		ZeitJDBCDAO.z = z;
	}

}
