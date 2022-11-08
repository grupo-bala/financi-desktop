package grupobala.Controller.Goal;

import java.util.Calendar;

import grupobala.Controller.Goal.IGoalController.IGoalController;
import grupobala.Database.Connection.DBConnection;
import grupobala.Database.Goal.DBGoal;
import grupobala.Database.Goal.IDBGoal.IDBGoal;


public class GoalController implements IGoalController{

    private IDBGoal idbGoal;

    public GoalController() {
        this.idbGoal = new DBGoal(new DBConnection());
    }

    @Override
    public void addGoal(int userID, String title, double objective, Calendar expectedDate, double idealValuePerMonth) throws Exception {
        try {
            idbGoal.addGoal(userID, title, objective, expectedDate, idealValuePerMonth);
        } catch (Exception error) {
            throw new Exception("Erro ao adicionar meta");
        }
    }
}
