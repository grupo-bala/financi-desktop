package grupobala.Controller.Goal;

import grupobala.Controller.Goal.IGoalController.IGoalController;
import grupobala.Database.Connection.DBConnection;
import grupobala.Database.Goal.DBGoal;
import grupobala.Database.Goal.IDBGoal.IDBGoal;
import grupobala.Database.User.DBUser;
import grupobala.Database.User.IDBUser.IDBUser;
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
        double idealValuePerMonth = calculateIdealValuePerMonth(
            objective,
            expectedDate
        );

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

    private double calculateIdealValuePerMonth(
        double objective,
        Calendar expectedDate
    ) {
        Calendar atualDate = Calendar.getInstance();
        int expectedMonth = expectedDate.get(Calendar.MONTH) + 1;
        int expectedYear = expectedDate.get(Calendar.YEAR);
        int atualMonth = atualDate.get(Calendar.MONTH) + 1;
        int atualYear = atualDate.get(Calendar.YEAR);

        if (expectedYear == atualYear) {
            return objective / (Math.abs(expectedMonth - atualMonth) + 1);
        }

        return (
            objective /
            (
                (
                    (12 - atualMonth) +
                    (12 * ((expectedYear - atualYear) - 1)) +
                    expectedMonth
                ) +
                1
            )
        );
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

    @Override
    public void editGoal(int userID, IGoal goal) throws Exception {
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(goal.getExpectedDate());

        try {
            goal.setIdealValuePerMonth(
                this.calculateIdealValuePerMonth(goal.getObjective(), calendar)
            );
            this.idbGoal.updateGoal(userID, goal);
        } catch (Exception error) {
            throw new Exception("Não foi possível editar a meta");
        }
    }

    @Override
    public void depositGoal(double value, IGoal goal) throws Exception {
        double newValue = goal.getAmountDeposited() + value;
        goal.setAmountDeposited(newValue);

        User user = new User();

        try {
            editGoal(user.getID(), goal);
            IDBUser idbUser = new DBUser(new DBConnection());
            idbUser.setUserBalance(user.getID(), (user.getValue() - value));
        } catch (Exception error) {
            throw new Exception(error.getMessage());
        }
    }
}
