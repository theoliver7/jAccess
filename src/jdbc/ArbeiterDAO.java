package jdbc;

import java.sql.SQLException;
import java.util.List;

public interface ArbeiterDAO {
	public Arbeiter findPersonBykuerzel(String kuerzel) throws SQLException;

	public List<Arbeiter> getAllMitarbeiter() throws SQLException;
	
	public List<Arbeiter> findTeam(String teamname, String kuerzel) throws SQLException;
	
	public void updatePic(String kuerzel, String pic) throws SQLException; 
	
	public boolean updateUser(Arbeiter a, String kuerzel) throws SQLException;
	
	public boolean createUser(Arbeiter a) throws SQLException;
	
	public List<String> getAllAbteilungen() throws SQLException;
}
