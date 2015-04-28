package jdbc;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public interface ZeitDAO {
	public  List<String> arbeitszeitauslesen(String uid) throws SQLException;
	public Zeit zeiteintragen(String uid) throws SQLException;
	public Zeit totalberechnen(List<String> list) throws SQLException, ParseException;
	
	
}
