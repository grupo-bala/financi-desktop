package grupobala.Database.Connection.IDBConnection;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface IDBConnection {
    public ResultSet executeQuery(String query) throws SQLException;

    public int executeUpdate(String query) throws SQLException;
}
