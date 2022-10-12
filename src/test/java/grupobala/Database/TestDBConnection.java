package grupobala.Database;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.*;

import org.junit.jupiter.api.Test;

import grupobala.Database.Connection.DBConnection;
import grupobala.Database.Connection.IDBConnection.IDBConnection;

public class TestDBConnection {
    private static IDBConnection databaseConnection = new DBConnection();

    @Test
    public void testDBConnectionExecuteQuery() throws SQLException {
        TestDBConnection.setupDBForTest();

        String query = "SELECT nome FROM usuario WHERE nomeusuario = 'financi'";

        ResultSet result = TestDBConnection.databaseConnection.executeQuery(query);

        boolean expected = true;
        boolean name = result.isBeforeFirst();

        result.close();

        assertEquals(expected, name);
    }

    @Test
    public void testDBConnectionExecuteQueryShouldFail() throws SQLException {
        TestDBConnection.setupDBForTest();

        String query = "SELECT nome FROM usuario WHERE nomeusuario = 'nomeInexistente'";

        ResultSet result = TestDBConnection.databaseConnection.executeQuery(query);

        boolean expected = false;
        boolean queryResult = result.isBeforeFirst();

        result.close();

        assertEquals(expected, queryResult);
    }

    @Test
    public void testDBConnectionExecuteUpdate() throws SQLException {
        TestDBConnection.setupDBForTest();

        String query = "UPDATE usuario SET nome = 'financiatualizado' WHERE nomeusuario = 'financi'";

        int expected = 1;
        int updates = TestDBConnection.databaseConnection.executeUpdate(query);

        assertEquals(expected, updates);
    }

    @Test
    public void testDBConnectionExecuteUpdateShouldFail() throws SQLException {
        TestDBConnection.setupDBForTest();

        String query = "UPDATE usuario SET nome = 'financiatualizado' WHERE nomeusuario = 'usuarioInexistente'";

        int expected = 0;
        int updates = TestDBConnection.databaseConnection.executeUpdate(query);

        assertEquals(expected, updates);
    }

    private static void setupDBForTest() throws SQLException {
        Connection rootConnection = DriverManager.getConnection(
            "jdbc:postgresql://localhost:5432/financi?user=postgres&password=postgres"
        );

        Statement statement = rootConnection.createStatement();

        String[] queries = {
            "TRUNCATE TABLE usuario CASCADE",
            "TRUNCATE TABLE meta CASCADE",
            "TRUNCATE TABLE aulaassistida CASCADE",
            "TRUNCATE TABLE aula CASCADE",
            "TRUNCATE TABLE movimentacao CASCADE",
            "TRUNCATE TABLE categoria CASCADE",
        };

        for (String query : queries) {
            statement.executeUpdate(query);
        }

        String query = "INSERT INTO usuario(nome, nomeusuario, senha, rendafixa) VALUES ('Financi', 'financi', '1234', 1000)";

        statement.executeUpdate(query);

        rootConnection.close();
    }
}
