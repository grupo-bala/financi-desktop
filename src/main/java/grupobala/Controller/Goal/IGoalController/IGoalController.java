package grupobala.Controller.Goal.IGoalController;

import java.util.ArrayList;

import grupobala.Entities.Goal.IGoal.IGoal;

public interface IGoalController {
    public ArrayList<IGoal> getGoals() throws Exception;
}
