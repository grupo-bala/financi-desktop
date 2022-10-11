package grupobala.Database;

import static org.junit.jupiter.api.Assertions.assertTrue;

import grupobala.Database.DBController.DBController;
import java.sql.*;
import java.sql.SQLException;
import org.junit.jupiter.api.Test;

public class TestDBController {

    @Test
    public void testRegistration() throws SQLException {
        DBController db = new DBController();

        TestDBController.truncateTablesForTest();

        boolean result = db.signUp("financi", "1234", "Financi", 0);

        assertTrue(result);
    }

    @Test
    public void testLogin() throws SQLException {
        DBController db = new DBController();

        TestDBController.truncateTablesForTest();

        db.signUp("testLogin", "1234", "Login", 0);

        boolean result = db.login("testLogin", "1234");

        assertTrue(result);
    }

    @Test
    public void testRegistrationShouldFail() throws SQLException {
        DBController db = new DBController();

        TestDBController.truncateTablesForTest();

        db.signUp(
            "testRegistrationShouldFail",
            "1234",
            "testRegistrationShouldFail",
            0
        );

        boolean result = db.signUp(
            "testRegistrationShouldFail",
            "1234",
            "Financi",
            0
        );

        assertTrue(!result);
    }

    @Test
    public void testLoginShouldFail() throws SQLException {
        DBController db = new DBController();

        TestDBController.truncateTablesForTest();

        boolean result = db.login("usuarioNaoCadastrado", "1234");

        assertTrue(!result);
    }

    private static void truncateTablesForTest() throws SQLException {
        Connection rootConnection = DriverManager
            .getConnection(
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

        rootConnection.close();
    }
}
