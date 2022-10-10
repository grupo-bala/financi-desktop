package grupobala.Database;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import grupobala.Database.DBController.DBController;

public class TestDBController {

    @Test
    @Order(1)
    public void testRegistration() throws Exception {
        DBController db = new DBController();

        db.dropEverything();

        boolean result = db.signUp("financi", "1234", "Financi", 0);

        assertTrue(result);
    }

    @Test
    @Order(2)
    public void testLogin() throws Exception {
        DBController db = new DBController();

        boolean result = db.login("financi", "1234");

        assertTrue(result);
    }

    @Test
    public void testRegistrationShouldFail() throws Exception {
        DBController db = new DBController();

        boolean result = db.signUp("financi", "1234", "Financi", 0);

        assertTrue(!result);
    }

    @Test
    public void testLoginShouldFail() {
        DBController db = new DBController();

        boolean result = db.login("usuarioNaoCadastrado", "1234");

        assertTrue(!result);
    }
}
