package grupobala.Controller.Goal;

import grupobala.Controller.Goal.IGoalController.IGoalController;
import grupobala.Database.Connection.DBConnection;
import grupobala.Database.Goal.DBGoal;
import grupobala.Database.Goal.IDBGoal.IDBGoal;
import grupobala.Database.User.DBUser;
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
        Calendar expectedDate
    ) throws Exception {
        double idealValuePerMonth = calculateIdealValuePerMonth(objective, expectedDate);
        
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


    private double calculateIdealValuePerMonth(double objective, Calendar expectedDate) {
        Calendar atualDate = Calendar.getInstance();
        int expectedMonth = expectedDate.get(Calendar.MONTH);
        int expectedYear = expectedDate.get(Calendar.YEAR);
        int atualMonth = atualDate.get(Calendar.MONTH);
        int atualYear = atualDate.get(Calendar.YEAR);

        if (expectedYear == atualYear) {
            return objective / (Math.abs(expectedMonth - atualMonth) + 1);
        }

        return objective / ((expectedMonth * (expectedYear - atualYear) * 12 - atualMonth) + 1);
    }

    @Override
    public void removeGoal(
        int userID,
        int goalID,
        double amountDeposited,
        double userBalance
    ) throws Exception {
        try {
            idbGoal.removeGoal(userID, goalID);
            DBUser dbUser = new DBUser(new DBConnection());
            dbUser.setUserBalance(userID, (userBalance + amountDeposited));
        } catch (Exception error) {
            throw new Exception("Não foi possível apagar a meta");
        }
    }
}
