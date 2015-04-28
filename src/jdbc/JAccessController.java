package jdbc;

import java.sql.SQLException;
import java.text.ParseException;

public class JAccessController {
	private final static ArbeiterDAO ARBEITER_DAO = new ArbeiterJDBCDAO();
	private final static ZeitDAO Zeit_DAO = new ZeitJDBCDAO();

	public static void main(String[] args) throws SQLException, ParseException {
		// ARBEITER_DAO.findPersonBykuerzel();
		// Zeit_DAO.zeiteintragen("45-459-5415");
		Zeit_DAO.totalberechnen(Zeit_DAO.arbeitszeitauslesen("45-459-5415"));
//		 String currenttime= new SimpleDateFormat("HH:mm:ss ").format(new
//		 Date());
//		 Date timestamp= new Date();
//		 System.out.println(currenttime);
//		 System.out.println(timestamp);
	}
}
