package grupobala.Database;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.SQLException;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import grupobala.Database.DBController.DBController;

public class TestDBController {

    @Test
    @Order(1)
    public void testRegistration() throws SQLException {
        DBController db = new DBController();

        db.dropEverything();

        boolean result = db.signUp("financi", "1234", "Financi", 0);

        assertTrue(result);
    }

    @Test
    public void testLogin() throws Exception {
        DBController db = new DBController();
        db.dropEverything();
        db.signUp("testLogin", "1234", "Login", 0);
        boolean result = db.login("testLogin", "1234");

        assertTrue(result);
    }

    @Test
    public void testRegistrationShouldFail() throws Exception {
        DBController db = new DBController();
        db.dropEverything();
        db.signUp("testRegistrationShouldFail", "1234", "testRegistrationShouldFail", 0);
        boolean result = db.signUp("testRegistrationShouldFail", "1234", "Financi", 0);

        assertTrue(!result);
    }

    @Test
    public void testLoginShouldFail() throws SQLException {
        DBController db = new DBController();
        db.dropEverything();
        boolean result = db.login("usuarioNaoCadastrado", "1234");

        assertTrue(!result);
    }
}
