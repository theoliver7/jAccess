package jdbc;

import java.sql.Connection;
import java.sql.SQLException;

public class ArbeiterJDBCDAO extends Datenbank implements ArbeiterDAO {

	private Connection con = null;

	public Arbeiter findPersonBykuerzel() throws SQLException {
		Arbeiter a = new Arbeiter();
		String sql = "SELECT idArbeiter, Name, Nachname, Wohnort, Funktion FROM Arbeiter WHERE kuerzel ='" + System.getProperty("user.name") + "'";
		con = getCon();
		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();
		while (rs.next()) {
			a = new Arbeiter();
			a.setIdarbeiter(rs.getString("idArbeiter"));
			a.setName(rs.getString("Name"));
			a.setNachname(rs.getString("Nachname"));
			a.setWohnohrt(rs.getString("Wohnort"));
			a.setFunktion(rs.getString("Funktion"));
			// System.out.println(System.getProperty("user.name"));
			// System.out.println(a.getIdArbeiter());
			// System.out.println(a.getNachname());
			// System.out.println(a.getName());
			// System.out.println(a.getWohnohrt());
			// System.out.println(a.getFunktion());
			break;
		}
		return a;
	}

}
