package grupobala.Database;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import grupobala.Database.Authenticator.DBAuthenticator;
import grupobala.Database.Authenticator.IDBAuthenticator.IDBAuthenticator;
import grupobala.Database.Connection.DBConnection;
import grupobala.Entities.User.IUser.IUser;
import grupobala.Entities.User.User;
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
            this.databaseAuthenticator.signUp(
                    "financi",
                    "Financi@123",
                    "Financi",
                    0
                );

        assertTrue(result);
    }

    @Test
    public void testLogin() throws SQLException {
        SetupForTest.truncateTables();

        this.databaseAuthenticator.signUp(
                "testLogin",
                "Financi@123",
                "Login",
                0
            );

        String expectedName = "Login";
        IUser result =
            this.databaseAuthenticator.login("testLogin", "Financi@123");

        assertEquals(expectedName, result.getName());

        new User().close();
    }

    @Test
    public void testRegistrationShouldFail() throws SQLException {
        SetupForTest.truncateTables();

        this.databaseAuthenticator.signUp(
                "testRegistrationShouldFail",
                "Financi@123",
                "testRegistrationShouldFail",
                0
            );

        boolean result =
            this.databaseAuthenticator.signUp(
                    "testRegistrationShouldFail",
                    "Financi@123",
                    "Financi",
                    0
                );

        assertTrue(!result);
    }

    @Test
    public void testLoginShouldFail() throws SQLException {
        SetupForTest.truncateTables();

        Exception exception = assertThrows(
            SQLException.class,
            () -> {
                this.databaseAuthenticator.login(
                        "usuarioNaoCadastrado",
                        "Financi@123"
                    );
            }
        );

        String expected = "Usuário não existe";
        String result = exception.getMessage();

        assertEquals(expected, result);
    }
}
