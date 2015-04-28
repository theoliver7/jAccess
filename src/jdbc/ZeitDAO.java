package jdbc;

import java.sql.SQLException;
import java.util.List;

public interface ZeitDAO {
	public List<String> arbeitszeitauslesen(String uid) throws SQLException;
	public Zeit zeiteintragen(String uid) throws SQLException;

}
