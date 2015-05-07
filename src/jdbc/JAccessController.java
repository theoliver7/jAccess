package jdbc;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public class JAccessController {
	private final static ArbeiterDAO ARBEITER_DAO = new ArbeiterJDBCDAO();

	public static void main(String[] args) throws SQLException, ParseException {
		 List<Arbeiter> liste = ARBEITER_DAO.findTeam("Informatik");
		 for (Arbeiter a : liste) {
			 System.out.println(a.getName() + " " + a.getNachname() + " " + a.getKuerzel() + "\n");
		 }
	}
}
