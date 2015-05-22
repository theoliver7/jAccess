package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArbeiterJDBCDAO implements ArbeiterDAO {

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
		return a;
	}

	@Override
	public List<Arbeiter> findTeam(String teamname, String kuerzel) throws SQLException {
		List<Arbeiter> team = new ArrayList<Arbeiter>();
		String sql = "Select A.Name, A.Nachname, A.kuerzel, A.Wohnort, A.Funktion, ABT.Abteilungsname, A.Pic " + "FROM arbeiter as A INNER JOIN abteilung as ABT on A.AbteilungID = ABT.idAbteilung "
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
		return team;
	}

	public List<Arbeiter> getAllMitarbeiter() throws SQLException {
		List<Arbeiter> mitarbeiter = new ArrayList<Arbeiter>();
		String sql = "select * from arbeiter right join abteilung on arbeiter.AbteilungID = abteilung.idAbteilung;";
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
		return mitarbeiter;
	}

	@Override
	public boolean updateUser(Arbeiter a, String kuerzel) throws SQLException {
		String sql = "Select * from abteilung where Abteilungsname='" + a.getAbteilung() + "';";
		con = Datenbank.getCon();
		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();
		int abteilungid = rs.getInt("idAbteilung");
		String sql1 = "UPDATE arbeiter set idArbeiter='" + a.getIdarbeiter() + "', Name='" + a.getName() + "', Nachname='" + a.getNachname() + "', " + "Wohnort='" + a.getWohnort() + "', "
				+ "Funktion='" + a.getFunktion() + "', AbteilungID=" + abteilungid + ", kuerzel='" + a.getKuerzel() + "' where kuerzel='" + kuerzel + "'";
		ps = con.prepareStatement(sql1);
		ps.executeUpdate();
		return true;
	}

	@Override
	public boolean createUser(Arbeiter a) throws SQLException {
		String sql = "Select * from abteilung where Abteilungsname='" + a.getAbteilung() + "';";
		con = Datenbank.getCon();
		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();
		int abteilungid = rs.getInt("idAbteilung");
		ps = null;
		rs = null;
		String sql2 = "INSERT INTO arbeiter (idArbeiter, Name, Nachname, Wohnort, Funktion, AbteilungID, kuerzel) values ('" + a.getIdarbeiter() + "', " + "'" + a.getName() + "', '" + a.getNachname()
				+ "', '" + a.getWohnort() + "', '" + a.getFunktion() + "', " + abteilungid + ", " + "'" + a.getKuerzel() + "')";
		ps = con.prepareStatement(sql2);
		rs = ps.executeQuery();
		return true;
	}

	@Override
	public void updatePic(String kuerzel, String pic) throws SQLException {
		String sql = "UPDATE arbeiter SET Pic = '" + pic + "' WHERE kuerzel = '" + kuerzel + "';";
		con = Datenbank.getCon();
		ps = con.prepareStatement(sql);
		ps.executeUpdate();
	}

	@Override
	public List<String> getAllAbteilungen() throws SQLException {
		List<String> abteilungen = new ArrayList<String>();
		String sql = "Select * from abteilung;";
		con = Datenbank.getCon();
		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();
		while (rs.next()) {
			abteilungen.add(new String(rs.getString("Abteilungsname")));
		}
		return abteilungen;
	}
}
