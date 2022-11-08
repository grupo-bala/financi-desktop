package grupobala.Controller.Goal.IGoalController;

import java.util.Calendar;

public interface IGoalController {
    public void addGoal(
        int userID,
        String title,
        double objective,
        Calendar expectedDate,
        double idealValuePerMonth
    ) throws Exception;
}
