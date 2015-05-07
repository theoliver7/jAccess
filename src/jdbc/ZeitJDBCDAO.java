package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 * Queris für die Zeittabellen
 * @author Oliver Aschwanden, ICT Berufsbildungscenter AG, oliver.aschwanden@bbcag.ch
 * @version 0.1.0.2.1
 *
 */

public class ZeitJDBCDAO extends Datenbank implements ZeitDAO {
	private Zeit z;
	private Connection con = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	/**
	 * Eine Methode welche die Arbeitszeit nach der UID der NFC Karte ausliest
	 * @param String
	 * @throws SQLException 
	 */
	
	@Override
	public List<String> arbeitszeitauslesen(String uid) throws SQLException {
		List<String> daten = new ArrayList<String>();
		String sql = "SELECT idZeit, timestamp, ArbeiterID FROM zeit WHERE ArbeiterID =(?)ORDER BY timestamp ASC";
		con = getCon();
		ps = con.prepareStatement(sql);
		ps.setString(1, uid);
		rs = ps.executeQuery();

		while (rs.next()) {
			z = new Zeit();
			z.setIdZeit(rs.getString("idZeit"));
			// z.setTimestamp(rs.getString("timestamp"));
//			z.setArbeiterID(rs.getString("ArbeiterID"));
			daten.add(rs.getString("timestamp"));
		}
		return daten;
	}

	/**
	 * Trägt die UID und einen Timestamp in die Dantenbank ab
	 * @param String
	 * @throws SQLException 
	 */
	@Override
	public Zeit zeiteintragen(String uid) throws SQLException {
		String sql = "INSERT INTO `zeit`(`ArbeiterID`) VALUES (?)";
		con = getCon();
		ps = con.prepareStatement(sql);
		ps.setString(1, uid);
		ps.executeUpdate();
		return null;
	}
}
