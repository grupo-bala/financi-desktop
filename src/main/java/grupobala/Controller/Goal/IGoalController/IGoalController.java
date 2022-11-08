package grupobala.Controller.Goal.IGoalController;

import java.util.ArrayList;
import grupobala.Entities.Goal.IGoal.IGoal;
import java.util.Calendar;

public interface IGoalController {
    public void addGoal(
        int userID,
        String title,
        double objective,
        Calendar expectedDate,
        double idealValuePerMonth
    ) throws Exception;
    
    public ArrayList<IGoal> getGoals() throws Exception;
}
