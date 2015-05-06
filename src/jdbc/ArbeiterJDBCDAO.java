package jdbc;

import java.sql.Connection;
import java.sql.SQLException;

public class ArbeiterJDBCDAO extends Datenbank implements ArbeiterDAO {

	private Connection con = null;

	public Arbeiter findPersonBykuerzel(String kuerzel) throws SQLException {
		Arbeiter a = new Arbeiter();
		String sql = "SELECT A.idArbeiter, A.Name, A.Nachname, A.kuerzel, "
				+ "A.Wohnort, A.Funktion, ABT.Abteilungsname FROM arbeiter as A "
				+ "INNER JOIN abteilung as ABT ON A.AbteilungID = ABT.idAbteilung "
				+ "WHERE kuerzel = '" + kuerzel + "';";
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

}
