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

	@Override
	public void updatePic(String kuerzel, String pic) throws SQLException {
		String sql = "UPDATE arbeiter SET Pic = '" + pic + "' WHERE kuerzel = '" + kuerzel + "';";
		con = Datenbank.getCon();
		ps = con.prepareStatement(sql);
		ps.executeUpdate();
	}
}
