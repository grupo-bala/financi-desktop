package grupobala.Controller.Goal.IGoalController;

import grupobala.Entities.Goal.IGoal.IGoal;
import java.util.ArrayList;
import java.util.Calendar;

public interface IGoalController {
    public void addGoal(
        int userID,
        String title,
        double objective,
        Calendar expectedDate
    ) throws Exception;

    public ArrayList<IGoal> getGoals() throws Exception;

    public void removeGoal(
        int userID,
        int goalID,
        double objective,
        double userBalance
    ) throws Exception;

    public void editGoal(int userId, IGoal goal) throws Exception;
}
