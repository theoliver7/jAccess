package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ZeitJDBCDAO extends Datenbank implements ZeitDAO {
	private Zeit z;
	private Connection con = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	

	@Override
	public List<String> arbeitszeitauslesen(String uid) throws SQLException {
		List<String> daten = new ArrayList<String>();

		String sql = "SELECT idZeit, timestamp, ArbeiterID FROM zeit WHERE ArbeiterID ='" + uid + "'";
		con = getCon();
		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();

		while (rs.next()) {
			z = new Zeit();
			z.setIdZeit(rs.getString("idZeit"));
			// z.setTimestamp(rs.getString("timestamp"));
			z.setArbeiterID(rs.getString("ArbeiterID"));
			daten.add(rs.getString("timestamp"));

		}
		return daten;

	}

	public Zeit zeiteintragen(String uid) throws SQLException {
		String sql = "INSERT INTO `zeit`(`ArbeiterID`) VALUES (?)";
		con = getCon();
		ps = con.prepareStatement(sql);
		ps.setString(1, uid);
		ps.executeUpdate();
		return null;
	}
	

	public Zeit getZ() {
		return z;
	}

	public void setZ(Zeit z) {
		this.z = z;
	}


}
