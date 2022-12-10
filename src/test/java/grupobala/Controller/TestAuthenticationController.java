package grupobala.Controller;

import grupobala.Controller.Authentication.AuthenticationController;
import grupobala.Entities.User.User;
import grupobala.SetupForTest.SetupForTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestAuthenticationController {

    private AuthenticationController authController = new AuthenticationController();

    @Test
    public void testSignInUser() throws Exception {
        SetupForTest.truncateTables();

        authController.signUp("financi", "Financi@123", "Financi", 0);
        authController.signIn("financi", "Financi@123").close();
    }

    @Test
    public void testSignUpUser() throws Exception {
        SetupForTest.truncateTables();

        authController.signUp("financi", "Financi@123", "Financi", 0);
    }

    @Test
    public void testSignInShouldFail() throws Exception {
        SetupForTest.truncateTables();

        Assertions.assertThrows(
            Exception.class,
            () -> {
                authController.signIn("financi", "4321").close();
            }
        );
    }

    @Test
    public void testSignUpShouldFail() throws Exception {
        SetupForTest.truncateTables();

        authController.signUp("financi", "Financi@123", "Financi", 0);

        Assertions.assertThrows(
            Exception.class,
            () -> {
                authController.signUp("financi", "Financi@123", "Financi", 0);
            }
        );
    }
}
