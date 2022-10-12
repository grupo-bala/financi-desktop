package grupobala.Database.Connection;

import grupobala.Database.Connection.IDBConnection.IDBConnection;
import grupobala.Database.Setup.Setup;
import java.sql.*;

public class DBConnection implements IDBConnection {

    private static boolean INSTANCIATED = false;
    private static Connection CONNECTION = null;

    public DBConnection() {
        if (!DBConnection.INSTANCIATED) {
            DBConnection.INSTANCIATED = true;

            try {
                DBConnection.CONNECTION =
                    DriverManager.getConnection(
                        "jdbc:postgresql://localhost:5432/financi?user=postgres&password=postgres"
                    );
            } catch (SQLException e) {
                DBConnection.CONNECTION = Setup.setup();
            }
        }
    }

    @Override
    public ResultSet executeQuery(String query) throws SQLException {
        Statement statement = DBConnection.CONNECTION.createStatement();

        ResultSet result = statement.executeQuery(query);

        statement.closeOnCompletion();

        return result;
    }

    @Override
    public int executeUpdate(String query) throws SQLException {
        Statement statement = DBConnection.CONNECTION.createStatement();

        int result = statement.executeUpdate(query);

        statement.close();

        return result;
    }
}
