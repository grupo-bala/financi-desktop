package grupobala.Controller;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import grupobala.Controller.Authentication.AuthenticationController;
import grupobala.Controller.Authentication.IAuthenticationController.IAuthenticationController;
import grupobala.Controller.Goal.GoalController;
import grupobala.Controller.Goal.IGoalController.IGoalController;
import grupobala.Entities.User.User;
import grupobala.SetupForTest.SetupForTest;
import java.util.Calendar;
import org.junit.jupiter.api.Test;

public class TestAddGoalController {

    private IAuthenticationController authenticationController = new AuthenticationController();
    private IGoalController iGoalController = new GoalController();

    @Test
    public void testAddGoal() throws Exception {
        SetupForTest.truncateTables();
        authenticationController.signUp("financi12", "123", "financi", 100);
        authenticationController.signIn("financi12", "123");

        assertDoesNotThrow(() -> {
            iGoalController.addGoal(
                new User().getID(),
                "ps5",
                5000,
                Calendar.getInstance(),
                150
            );
        });

        new User().close();
    }

    @Test
    public void testAddGoalFailUserIDNoneExistent() throws Exception {
        SetupForTest.truncateTables();

        assertThrows(
            Exception.class,
            () -> {
                iGoalController.addGoal(
                    -1,
                    "ps5",
                    5000,
                    Calendar.getInstance(),
                    150
                );
            }
        );
    }
}
