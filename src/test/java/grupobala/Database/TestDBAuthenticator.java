package grupobala.Database;

import static org.junit.jupiter.api.Assertions.assertTrue;

import grupobala.Database.Authenticator.DBAuthenticator;
import grupobala.Database.Authenticator.IDBAuthenticator.IDBAuthenticator;
import grupobala.Database.Connection.DBConnection;
import grupobala.SetupForTest.SetupForTest;
import java.sql.*;
import org.junit.jupiter.api.Test;

public class TestDBAuthenticator {

    private IDBAuthenticator databaseAuthenticator = new DBAuthenticator(
        new DBConnection()
    );

    @Test
    public void testRegistration() throws SQLException {
        SetupForTest.truncateTables();

        boolean result =
            this.databaseAuthenticator.signUp("financi", "1234", "Financi", 0);

        assertTrue(result);
    }

    @Test
    public void testLogin() throws SQLException {
        SetupForTest.truncateTables();

        this.databaseAuthenticator.signUp("testLogin", "1234", "Login", 0);

        boolean result = this.databaseAuthenticator.login("testLogin", "1234");

        assertTrue(result);
    }

    @Test
    public void testRegistrationShouldFail() throws SQLException {
        SetupForTest.truncateTables();

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
        SetupForTest.truncateTables();

        boolean result =
            this.databaseAuthenticator.login("usuarioNaoCadastrado", "1234");

        assertTrue(!result);
    }
}
