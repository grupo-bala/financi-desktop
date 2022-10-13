package grupobala.Controller;

import grupobala.Controller.Authentication.AuthenticationController;
import grupobala.SetupForTest.SetupForTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestAuthenticationController {

    private AuthenticationController authController = new AuthenticationController();

    @Test
    public void testSignInUser() throws Exception {
        SetupForTest.truncateTables();

        authController.signUp("financi", "1234", "Financi", 0);
        authController.signIn("financi", "1234");
    }

    @Test
    public void testSignUpUser() throws Exception {
        SetupForTest.truncateTables();

        authController.signUp("financi", "1234", "Financi", 0);
    }

    @Test
    public void testSignInShouldFail() throws Exception {
        SetupForTest.truncateTables();

        Assertions.assertThrows(
            Exception.class,
            () -> {
                authController.signIn("financi", "4321");
            }
        );
    }

    @Test
    public void testSignUpShouldFail() throws Exception {
        SetupForTest.truncateTables();

        authController.signUp("financi", "1234", "Financi", 0);

        Assertions.assertThrows(
            Exception.class,
            () -> {
                authController.signUp("financi", "1234", "Financi", 0);
            }
        );
    }
}
