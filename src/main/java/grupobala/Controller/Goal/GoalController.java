package grupobala.Controller.Goal;

import grupobala.Controller.Goal.IGoalController.IGoalController;
import grupobala.Database.Connection.DBConnection;
import grupobala.Database.Goal.DBGoal;
import grupobala.Database.Goal.IDBGoal.IDBGoal;
import grupobala.Entities.Goal.IGoal.IGoal;
import grupobala.Entities.User.User;

import java.util.ArrayList;
import java.util.Calendar;

public class GoalController implements IGoalController {

    private IDBGoal idbGoal;

    public GoalController() {
        this.idbGoal = new DBGoal(new DBConnection());
    }

    @Override
    public ArrayList<IGoal> getGoals() throws Exception {
        ArrayList<IGoal> goals = this.idbGoal.getAllGoals(new User().getID());
        return goals;
    }

    @Override
    public void addGoal(
        int userID,
        String title,
        double objective,
        Calendar expectedDate,
        double idealValuePerMonth
    ) throws Exception {
        try {
            idbGoal.addGoal(
                userID,
                title,
                objective,
                expectedDate,
                idealValuePerMonth
            );
        } catch (Exception error) {
            throw new Exception("Erro ao adicionar meta");
        }
    }
}
