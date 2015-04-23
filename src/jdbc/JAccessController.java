package jdbc;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JAccessController {
	private final static ArbeiterDAO ARBEITER_DAO = new ArbeiterJDBCDAO();
	private final static ZeitDAO Zeit_DAO = new ZeitJDBCDAO();

	public static void main(String[] args) throws SQLException, ParseException {
		ARBEITER_DAO.findPersonBykuerzel();
		Zeit_DAO.arbeitszeitauslesen();

		Zeit.totalberechnen(ZeitJDBCDAO.getZ().getBeginnMorgen(), ZeitJDBCDAO.getZ().getEndeMorgen(),ZeitJDBCDAO.getZ().getBeginnNachmittag(),ZeitJDBCDAO.getZ().getEndeMittag());
		String currenttime= new SimpleDateFormat("HH:mm").format(new Date());
		System.out.println(currenttime);
	}
}
