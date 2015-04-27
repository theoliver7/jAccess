package jdbc;

import java.sql.SQLException;

public interface ZeitDAO {
	public  Zeit arbeitszeitauslesen() throws SQLException;
	public  Zeit totalberechnen() throws SQLException;
	public Zeit zeiteintragen() throws SQLException;
	
	
}
