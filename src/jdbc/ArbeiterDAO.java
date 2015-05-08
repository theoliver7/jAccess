package jdbc;

import java.sql.SQLException;
import java.util.List;

public interface ArbeiterDAO {
	public Arbeiter findPersonBykuerzel(String kuerzel) throws SQLException;

	public List<Arbeiter> findTeam(String teamname, String kuerzel) throws SQLException;
}
