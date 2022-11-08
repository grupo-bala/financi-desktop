package grupobala.Controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import grupobala.Controller.Goal.GoalController;
import grupobala.Entities.Goal.IGoal.IGoal;
import grupobala.Entities.User.User;
import grupobala.SetupForTest.SetupForTest;
import org.junit.jupiter.api.Test;

public class TestRemoveGoalController {
    private GoalController goalController = new GoalController();

    @Test
    public void testRemoveGoal() throws Exception {
        SetupForTest.truncateTables();
        int financiUserID = SetupForTest.addFinanciUser();
        IGoal goal = SetupForTest.addDefaultGoal(financiUserID);
        goalController.removeGoal(financiUserID, goal.getID(), goal.getAmountDeposited(), 
        new User("financi", "Financi", 0, 0).getValue());

        new User().close();
    
    }


    @Test
    public void removeGoalShouldFailNonexistentGoalID() throws Exception {
        SetupForTest.truncateTables();
        int financiUserID = SetupForTest.addFinanciUser();
        Exception exception = assertThrows(
            Exception.class,
            () -> {
                goalController.removeGoal(
                    financiUserID,
                    -1,
                    1.10,
                    1.10
                );
            }
        );
        String expected = "Não foi possível apagar a meta";
        String result = exception.getMessage();
        assertEquals(expected, result);
    }
}
