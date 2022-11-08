package grupobala.Controller.Goal;

import java.util.ArrayList;

import grupobala.Controller.Goal.IGoalController.IGoalController;
import grupobala.Database.Connection.DBConnection;
import grupobala.Database.Goal.DBGoal;
import grupobala.Entities.Goal.IGoal.IGoal;
import grupobala.Entities.User.User;

public class GoalController implements IGoalController {
    public ArrayList<IGoal> getGoals() throws Exception {
        DBGoal dbGoal = new DBGoal(new DBConnection());
        ArrayList<IGoal> goals = dbGoal.getAllGoals(new User().getID());
        
        return goals;
    }
}
