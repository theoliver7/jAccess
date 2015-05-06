package jdbc;

import java.sql.SQLException;

public interface ArbeiterDAO {
	public Arbeiter findPersonBykuerzel(String kuerzel) throws SQLException;
}
