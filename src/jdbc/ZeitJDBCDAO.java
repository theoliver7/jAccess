package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 * Querys für die Zeittabellen
 * @author Oliver Aschwanden, ICT Berufsbildungscenter AG, oliver.aschwanden@bbcag.ch
 * @version 0.1.0.2.1
 *
 */
public class ZeitJDBCDAO extends Datenbank implements ZeitDAO {

	private Connection con = null;
	
	/**
	 * Eine Methode welche die Arbeitszeit nach der UID der NFC Karte ausliest
	 * @param String
	 * @throws SQLException 
	 */
	
	@Override
	public List<Timestamp> arbeitszeitauslesen(String uid) throws SQLException {
		List<Timestamp> daten = new ArrayList<Timestamp>();
		String sql = "SELECT idZeit, timestamp, ArbeiterID FROM zeit WHERE ArbeiterID =(?)ORDER BY timestamp ASC";
		con = Datenbank.getCon();
		ps = con.prepareStatement(sql);
		ps.setString(1, uid);
		rs = ps.executeQuery();

		while (rs.next()) {
			daten.add(rs.getTimestamp("timestamp"));
		}
		return daten;
	}

	/**
	 * Trägt die UID und einen Timestamp in die Dantenbank ab
	 * @param String
	 * @throws SQLException 
	 */
	@Override
	public void zeiteintragen(String uid) throws SQLException {
		String sql = "INSERT INTO `zeit`(`ArbeiterID`) VALUES (?)";
		con = Datenbank.getCon();
		ps = con.prepareStatement(sql);
		ps.setString(1, uid);
		ps.executeUpdate();
	}
}
