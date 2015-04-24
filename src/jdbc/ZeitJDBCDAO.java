package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ZeitJDBCDAO extends Datenbank implements ZeitDAO {
	private static Zeit z;
	private Connection con = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;

	@Override
	public Zeit arbeitszeitauslesen() throws SQLException {
		z = new Zeit();
		String sql = "SELECT idZeit, BeginnMorgen, EndeMorgen, BeginnNachmittag, EndeNachmittag, Datum, Total, ArbeiterID FROM zeit WHERE 45-459-5415";
		con = getCon();
		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();
		while (rs.next()) {
			z = new Zeit();
			z.setIdZeit(rs.getString("idZeit"));
			z.setBeginnMorgen(rs.getString("BeginnMorgen"));
			z.setEndeMorgen(rs.getString("EndeMorgen"));
			z.setBeginnNachmittag(rs.getString("BeginnNachmittag"));
			z.setEndeMittag(rs.getString("EndeNachmittag"));
			z.setDatum(rs.getString("Datum"));
			z.setTotal(rs.getString("Total"));
			z.setArbeiterID(rs.getString("ArbeiterID"));
			// System.out.println(z.getIdZeit());
			// System.out.println(z.getBeginnMorgen());
			// System.out.println(z.getEndeMorgen());
			// System.out.println(z.getBeginnNachmittag());
			// System.out.println(z.getEndeMittag());
			// System.out.println(z.getDatum());
			// System.out.println(z.getTotal());
			// System.out.println(z.getArbeiterID());
			break;
		}
		return z;

	}

	@Override
	public Zeit totaleinfuegen() throws SQLException {
		con = getCon();
		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();
		return null;
	}

	public static Zeit getZ() {
		return z;
	}

	public static void setZ(Zeit z) {
		ZeitJDBCDAO.z = z;
	}

}
