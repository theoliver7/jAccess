package jdbc;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public class JAccessController {
	private final static ArbeiterDAO ARBEITER_DAO = new ArbeiterJDBCDAO();
	private final static ZeitDAO Zeit_DAO = new ZeitJDBCDAO();
	private final static Zeit Zeit = new Zeit();

	public static void main(String[] args) throws SQLException, ParseException {

		Zeit.totalberechnen(Zeit.zeitenorganisieren(Zeit_DAO.arbeitszeitauslesen("45-459-5415"))).toString();
		
		
	}
}
