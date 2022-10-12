package grupobala.Controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import grupobala.Controller.Authentication.AuthenticationController;
import grupobala.Database.TestDBTransaction;

public class TestAuthenticationController {

    private AuthenticationController authController = new AuthenticationController();

    @Test
    public void testSignInUser() throws Exception {
        TestDBTransaction.setupDBForTest();
        authController.signUp("financi2", "1234", "Financi", 0);
        authController.signIn("financi2", "1234");
    }

    @Test
    public void testSignUpUser() throws Exception {
        TestDBTransaction.setupDBForTest();
        authController.signUp("financi2", "1234", "Financi", 0);
    }

    @Test
    public void testSignInShouldFail() throws Exception {
        TestDBTransaction.setupDBForTest();
        Assertions.assertThrows(Exception.class, () -> {
            authController.signIn("financi", "4321");
        });
    }

    @Test
    public void testSignUpShouldFail() throws Exception {
        TestDBTransaction.setupDBForTest();
        Assertions.assertThrows(Exception.class, () -> {
            authController.signUp("financi", "1234", "Financi", 0);
        });
    }
}
