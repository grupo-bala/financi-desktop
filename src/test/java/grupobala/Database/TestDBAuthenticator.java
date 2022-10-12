package grupobala.Database;

import static org.junit.jupiter.api.Assertions.assertTrue;

import grupobala.Database.Authenticator.DBAuthenticator;
import grupobala.Database.Authenticator.IDBAuthenticator.IDBAuthenticator;
import grupobala.Database.Connection.DBConnection;
import java.sql.*;
import org.junit.jupiter.api.Test;

public class TestDBAuthenticator {

    private IDBAuthenticator databaseAuthenticator = new DBAuthenticator(
        new DBConnection()
    );

    @Test
    public void testRegistration() throws SQLException {
        TestDBAuthenticator.truncateTablesForTest();

        boolean result =
            this.databaseAuthenticator.signUp("financi", "1234", "Financi", 0);

        assertTrue(result);
    }

    @Test
    public void testLogin() throws SQLException {
        TestDBAuthenticator.truncateTablesForTest();

        this.databaseAuthenticator.signUp("testLogin", "1234", "Login", 0);

        boolean result = this.databaseAuthenticator.login("testLogin", "1234");

        assertTrue(result);
    }

    @Test
    public void testRegistrationShouldFail() throws SQLException {
        TestDBAuthenticator.truncateTablesForTest();

        this.databaseAuthenticator.signUp(
                "testRegistrationShouldFail",
                "1234",
                "testRegistrationShouldFail",
                0
            );

        boolean result =
            this.databaseAuthenticator.signUp(
                    "testRegistrationShouldFail",
                    "1234",
                    "Financi",
                    0
                );

        assertTrue(!result);
    }

    @Test
    public void testLoginShouldFail() throws SQLException {
        TestDBAuthenticator.truncateTablesForTest();

        boolean result =
            this.databaseAuthenticator.login("usuarioNaoCadastrado", "1234");

        assertTrue(!result);
    }

    private static void truncateTablesForTest() throws SQLException {
        Connection connection = DriverManager.getConnection(
            "jdbc:postgresql://localhost:5432/financi?user=postgres&password=postgres"
        );

        Statement statement = connection.createStatement();

        String[] queries = {
            "TRUNCATE TABLE usuario CASCADE",
            "TRUNCATE TABLE meta CASCADE",
            "TRUNCATE TABLE aulaassistida CASCADE",
            "TRUNCATE TABLE aula CASCADE",
            "TRUNCATE TABLE movimentacao CASCADE",
        };

        for (String query : queries) {
            statement.executeUpdate(query);
        }

        connection.close();
    }
}
