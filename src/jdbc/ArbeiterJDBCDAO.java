package jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArbeiterJDBCDAO extends Datenbank implements ArbeiterDAO {

	private Connection con = null;

	@Override
	public Arbeiter findPersonBykuerzel(String kuerzel) throws SQLException {
		Arbeiter a = new Arbeiter();
		String sql = "SELECT A.idArbeiter, A.Name, A.Nachname, A.kuerzel, " + "A.Wohnort, A.Funktion, ABT.Abteilungsname FROM arbeiter as A "
				+ "INNER JOIN abteilung as ABT ON A.AbteilungID = ABT.idAbteilung " + "WHERE kuerzel = '" + kuerzel + "';";
		con = getCon();
		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();
		while (rs.next()) {
			a = new Arbeiter();
			a.setIdarbeiter(rs.getString("idArbeiter"));
			a.setName(rs.getString("Name"));
			a.setNachname(rs.getString("Nachname"));
			a.setKuerzel(rs.getString("kuerzel"));
			a.setWohnort(rs.getString("Wohnort"));
			a.setFunktion(rs.getString("Funktion"));
			a.setAbteilung(rs.getString("Abteilungsname"));
			break;
		}
		return a;
	}

	@Override
	public List<Arbeiter> findTeam(String teamname, String kuerzel) throws SQLException {
		List<Arbeiter> team = new ArrayList<Arbeiter>();
		String sql = "Select A.Name, A.Nachname, A.kuerzel, A.Wohnort, A.Funktion, ABT.Abteilungsname " + "FROM arbeiter as A INNER JOIN abteilung as ABT on A.AbteilungID = ABT.idAbteilung "
				+ "WHERE abteilungsname = '" + teamname + "';";
		con = getCon();
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
			team.add(a);
		}
		return team;
	}
}
