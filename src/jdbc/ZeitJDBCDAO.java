package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ZeitJDBCDAO extends Datenbank implements ZeitDAO {
	private Zeit z;
	private Connection con = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	public List<String> daten = new ArrayList<String>();

	@Override
	public List<String> arbeitszeitauslesen(String uid) throws SQLException {

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

	@Override
	public Zeit totalberechnen(List<String> daten) throws SQLException, ParseException {
		int i = 0;
		System.out.println(daten);

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		Date beginnMorgen = dateFormat.parse(daten.get(0));
		Date endeMorgen = dateFormat.parse(daten.get(1));
		Date beginnNachmittag = dateFormat.parse(daten.get(2));
		Date endeNachmittag = dateFormat.parse(daten.get(3));
		System.out.println(beginnMorgen);
		System.out.println(endeMorgen);
		System.out.println(beginnNachmittag);
		System.out.println(endeNachmittag);
		long diff = (endeMorgen.getTime() - beginnMorgen.getTime() + endeNachmittag.getTime() - beginnNachmittag.getTime());

		long second = (diff / 1000) % 60;
		long minute = (diff / (1000 * 60)) % 60;
		long hour = (diff / (1000 * 60 * 60)) % 24;
		diff = diff % 1000;
		
		String time = String.format("%02d:%02d:%02d:%d", hour, minute, second, diff);
		System.out.println(time);
		return null;
	}

	public Zeit getZ() {
		return z;
	}

	public void setZ(Zeit z) {
		this.z = z;
	}

	public List<String> getZeiten() {
		return daten;
	}

	public void setZeiten(ArrayList<String> zeiten) {
		this.daten = zeiten;
	}

}
