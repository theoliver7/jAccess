package jdbc;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public interface ZeitDAO {
	public List<Date> arbeitszeitauslesen(String uid) throws SQLException;
	public Zeit zeiteintragen(String uid) throws SQLException;
}
