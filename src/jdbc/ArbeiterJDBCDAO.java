package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArbeiterJDBCDAO extends Datenbank implements ArbeiterDAO {

	private Connection con = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;

	@Override
	public Arbeiter findPersonBykuerzel(String kuerzel) throws SQLException {
		Arbeiter a = new Arbeiter();
		String sql = "SELECT A.idArbeiter, A.Name, A.Nachname, A.kuerzel, " + "A.Wohnort, A.Funktion, ABT.Abteilungsname, A.Pic FROM arbeiter as A "
				+ "INNER JOIN abteilung as ABT ON A.AbteilungID = ABT.idAbteilung " + "WHERE kuerzel = '" + kuerzel + "';";
		con = Datenbank.getCon();
		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();
		while (rs.next()) {
			a.setIdarbeiter(rs.getString("idArbeiter"));
			a.setName(rs.getString("Name"));
			a.setNachname(rs.getString("Nachname"));
			a.setKuerzel(rs.getString("kuerzel"));
			a.setWohnort(rs.getString("Wohnort"));
			a.setFunktion(rs.getString("Funktion"));
			a.setAbteilung(rs.getString("Abteilungsname"));
			a.setPic(rs.getString("Pic"));
			break;
		}
		closeCon();
		return a;
	}

	@Override
	public List<Arbeiter> findTeam(String teamname, String kuerzel) throws SQLException {
		List<Arbeiter> team = new ArrayList<Arbeiter>();
		String sql = "SELECT A.Name, A.Nachname, A.kuerzel, A.Wohnort, A.Funktion, ABT.Abteilungsname, A.Pic " + "FROM arbeiter AS A INNER JOIN abteilung AS ABT ON A.AbteilungID = ABT.idAbteilung "
				+ "WHERE abteilungsname = '" + teamname + "';";
		con = Datenbank.getCon();
		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();
		while (rs.next()) {
			Arbeiter a = new Arbeiter();
			a.setName(rs.getString("Name"));
			a.setNachname(rs.getString("Nachname"));
			a.setKuerzel(rs.getString("kuerzel"));
			a.setWohnort(rs.getString("Wohnort"));
			a.setFunktion(rs.getString("Funktion"));
			a.setAbteilung(rs.getString("Abteilungsname"));
			a.setPic(rs.getString("Pic"));
			team.add(a);
		}
		closeCon();
		return team;
	}

	public List<Arbeiter> getAllMitarbeiter() throws SQLException {
		List<Arbeiter> mitarbeiter = new ArrayList<Arbeiter>();
		String sql = "SELECT * FROM arbeiter RIGHT JOIN abteilung ON arbeiter.AbteilungID = abteilung.idAbteilung ORDER BY Name ASC;";
		con = Datenbank.getCon();
		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();
		while (rs.next()) {
			Arbeiter a = new Arbeiter();
			a.setIdarbeiter(rs.getString("idArbeiter"));
			a.setName(rs.getString("Name"));
			a.setNachname(rs.getString("Nachname"));
			a.setWohnort(rs.getString("Wohnort"));
			a.setFunktion(rs.getString("Funktion"));
			a.setAbteilung(rs.getString("Abteilungsname"));
			a.setKuerzel(rs.getString("kuerzel"));
			mitarbeiter.add(a);
		}
		closeCon();
		return mitarbeiter;
	}

	@Override
	public boolean updateUser(Arbeiter a, String kuerzel) throws SQLException {
		String sql = "SELECT * FROM abteilung WHERE Abteilungsname='" + a.getAbteilung() + "';";
		con = Datenbank.getCon();
		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();
		int abteilungid = 0;
		while(rs.next()) {
			 abteilungid = rs.getInt("idAbteilung");
		}
		ps = null;
		rs = null;
		String sql1 = "UPDATE arbeiter SET Name='" + a.getName() + "', Nachname='" + a.getNachname() + "', " + "Wohnort='" + a.getWohnort() + "', "
				+ "Funktion='" + a.getFunktion() + "', AbteilungID=" + abteilungid + ", kuerzel='" + a.getKuerzel() + "' WHERE kuerzel='" + kuerzel + "'";
		ps = con.prepareStatement(sql1);
		ps.executeUpdate();
		closeCon();
		return true;
	}

	@Override
	public boolean createUser(Arbeiter a) throws SQLException {
		String sql = "SELECT * FROM abteilung WHERE Abteilungsname='" + a.getAbteilung() + "';";
		con = Datenbank.getCon();
		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();
		int abteilungid = 0;
		while(rs.next()) {
			 abteilungid = rs.getInt("idAbteilung");
		}
		ps = null;
		rs = null;
		String sql2 = "INSERT INTO arbeiter (idArbeiter, Name, Nachname, Wohnort, Funktion, AbteilungID, kuerzel) values ('" + a.getIdarbeiter() + "', " + "'" + a.getName() + "', '" + a.getNachname()
				+ "', '" + a.getWohnort() + "', '" + a.getFunktion() + "', " + abteilungid + ", " + "'" + a.getKuerzel() + "')";
		ps = con.prepareStatement(sql2);
		ps.executeUpdate();
		closeCon();
		return true;
	}
	
	@Override
	public boolean deleteUser(String uid) throws SQLException {
		String sql = "DELETE FROM arbeiter WHERE idArbeiter='" + uid + "';";
		con = Datenbank.getCon();
		ps = con.prepareStatement(sql);
		ps.executeUpdate();
		closeCon();
		return true;
	}

	@Override
	public void updatePic(String kuerzel, String pic) throws SQLException {
		String sql = "UPDATE arbeiter SET Pic = '" + pic + "' WHERE kuerzel = '" + kuerzel + "';";
		con = Datenbank.getCon();
		ps = con.prepareStatement(sql);
		ps.executeUpdate();
		closeCon();
	}

	@Override
	public List<String> getAllAbteilungen() throws SQLException {
		List<String> abteilungen = new ArrayList<String>();
		String sql = "SELECT * FROM abteilung;";
		con = Datenbank.getCon();
		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();
		while (rs.next()) {
			abteilungen.add(new String(rs.getString("Abteilungsname")));
		}
		return abteilungen;
	}
}
