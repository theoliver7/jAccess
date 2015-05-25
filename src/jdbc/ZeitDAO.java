package jdbc;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public interface ZeitDAO {
	/**
	 * List die Arbeitszeiten eines Benutzers von der DB.
	 * 
	 * @param uid
	 * @return List<Timestamp>
	 * @throws SQLException
	 */
	public List<Timestamp> arbeitszeitauslesen(String uid) throws SQLException;

	/**
	 * Trägt automatisch einen Timestamp mit ensprechender uid in DB.
	 * 
	 * @param uid
	 * @throws SQLException
	 */
	public void zeiteintragen(String uid) throws SQLException;

	/**
	 * List die Arbeitszeiten des angemeldeten Benutzer und nach einem
	 * speziellen Monat
	 * 
	 * @param uid
	 * @throws SQLException
	 */

	public List<Timestamp> arbeitszeitauslesennachMonat(String uid, int monat,
			int jahr) throws SQLException;
}
