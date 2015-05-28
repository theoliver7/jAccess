package jdbc;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ArbeiterDAO {
	/**
	 * Holt alle informationen mithilfe des kuerzels aus der DB
	 * @param kuerzel
	 * @return
	 * @throws SQLException
	 */
	public Arbeiter findPersonBykuerzel(String kuerzel) throws SQLException;

	/**
	 * Liest alle Mitarbeiter aus der DB
	 * @return  ArrayList
	 * @throws SQLException
	 */
	public List<Arbeiter> getAllMitarbeiter() throws SQLException;

	/**
	 * Liest alle Arbeiter aus welche in der gleichen Abteilung arbeiten
	 * @param teamname
	 * @param kuerzel
	 * @return ArrayList
	 * @throws SQLException
	 */
	public List<Arbeiter> findTeam(String teamname, String kuerzel) throws SQLException;

	/**
	 * Tràgt den neuen Profilbild link in der Datenbank ein
	 * @param kuerzel
	 * @param pic
	 * @throws SQLException
	 */
	public void updatePic(String kuerzel, String pic) throws SQLException;

	/**
	 * Passt die angegebenen Personalien in die DB
	 * @param a
	 * @param kuerzel
	 * @return
	 * @throws SQLException
	 */
	public boolean updateUser(Arbeiter a, String kuerzel) throws SQLException;

	/**
	 * Erstellt einen neuen Benutzer in der DB
	 * @param a
	 * @return
	 * @throws SQLException
	 */
	public boolean createUser(Arbeiter a) throws SQLException;

	/**
	 * Loescht den benutzer in der DB
	 * @param uid
	 * @return
	 * @throws SQLException
	 */
	public boolean deleteUser(String uid) throws SQLException;

	/**
	 * Liest alle Abteilungen aus der DB aus
	 * @return
	 * @throws SQLException
	 */
	public List<String> getAllAbteilungen() throws SQLException;
}
