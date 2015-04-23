package jdbc;

import java.sql.SQLException;

public interface ArbeiterDAO {
	public Arbeiter findPersonBykuerzel() throws SQLException;
}
