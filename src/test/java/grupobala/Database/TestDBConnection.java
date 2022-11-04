package grupobala.Database;

import static org.junit.jupiter.api.Assertions.*;

import grupobala.Database.Connection.DBConnection;
import grupobala.Database.Connection.IDBConnection.IDBConnection;
import grupobala.SetupForTest.SetupForTest;
import java.sql.*;
import org.junit.jupiter.api.Test;

public class TestDBConnection {

    private static IDBConnection databaseConnection = new DBConnection();

    @Test
    public void testDBConnectionExecuteQuery() throws SQLException {
        SetupForTest.truncateTables();
        SetupForTest.addFinanciUser();

        String query = "SELECT nome FROM usuario WHERE nomeusuario = 'financi'";

        ResultSet result = TestDBConnection.databaseConnection.executeQuery(
            query
        );

        boolean expected = true;
        boolean name = result.isBeforeFirst();

        result.close();

        assertEquals(expected, name);
    }

    @Test
    public void testDBConnectionExecuteQueryShouldFail() throws SQLException {
        SetupForTest.truncateTables();
        SetupForTest.addFinanciUser();

        String query =
            "SELECT nome FROM usuario WHERE nomeusuario = 'nomeInexistente'";

        ResultSet result = TestDBConnection.databaseConnection.executeQuery(
            query
        );

        boolean expected = false;
        boolean queryResult = result.isBeforeFirst();

        result.close();

        assertEquals(expected, queryResult);
    }

    @Test
    public void testDBConnectionExecuteUpdate() throws SQLException {
        SetupForTest.truncateTables();
        SetupForTest.addFinanciUser();

        String query =
            "UPDATE usuario SET nome = 'financiatualizado' WHERE nomeusuario = 'financi'";

        int expected = 1;
        int updates = TestDBConnection.databaseConnection.executeUpdate(query);

        assertEquals(expected, updates);
    }

    @Test
    public void testDBConnectionExecuteUpdateShouldFail() throws SQLException {
        SetupForTest.truncateTables();
        SetupForTest.addFinanciUser();

        String query =
            "UPDATE usuario SET nome = 'financiatualizado' WHERE nomeusuario = 'usuarioInexistente'";

        int expected = 0;
        int updates = TestDBConnection.databaseConnection.executeUpdate(query);

        assertEquals(expected, updates);
    }
}
