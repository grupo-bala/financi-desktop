package grupobala.Controller;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Date;
import java.util.Calendar;

import org.junit.jupiter.api.Test;

import grupobala.Controller.Authentication.AuthenticationController;
import grupobala.Controller.Authentication.IAuthenticationController.IAuthenticationController;
import grupobala.Controller.Goal.GoalController;
import grupobala.Entities.Goal.IGoal.IGoal;
import grupobala.Entities.User.User;
import grupobala.SetupForTest.SetupForTest;

public class TestEditGoalController {

    private GoalController GoalController = new GoalController();
    private Calendar calendarDate = Calendar.getInstance();

    @Test
    public void testEditGoal() throws Exception {
        SetupForTest.truncateTables();
        IAuthenticationController authController = new AuthenticationController();

        authController.signUp("goal", "1234", "financi", 0);
        authController.signIn("goal", "1234");

        User user = new User();

        assertDoesNotThrow(() -> {
            this.GoalController.addGoal(user.getID(), "test", 1000, calendarDate);
            IGoal firstGoal = this.GoalController.getGoals().get(0);

            firstGoal.setTitle("NEW TEST");
            this.GoalController.editGoal(user.getID(), firstGoal);
        });

        new User().close();
    }

    @Test
    public void testShouldFailEditGoalUserIDNoneExistent() throws Exception {
        SetupForTest.truncateTables();
        IAuthenticationController authController = new AuthenticationController();

        authController.signUp("goal", "1234", "financi", 0);
        authController.signIn("goal", "1234");

        User user = new User();

        assertThrows(
            Exception.class,
            () -> {
                this.GoalController.addGoal(user.getID(), "test", 1000, calendarDate);
                IGoal firstGoal = this.GoalController.getGoals().get(0);
                GoalController.editGoal(-1, firstGoal);
            }
        );

        new User().close();
    }
}