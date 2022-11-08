package grupobala.Database.Goal.IDBGoal;

import grupobala.Entities.Goal.IGoal.IGoal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

public interface IDBGoal {
    public IGoal addGoal(
        int userID,
        String title,
        double objective,
        Calendar expectedDate,
        double idealValuePerMonth
    ) throws SQLException;

    public int removeGoal(int userID, int goalId) throws SQLException;

    public int updateGoal(int userID, IGoal goal) throws SQLException;

    public ArrayList<IGoal> getAllGoals(int userID) throws SQLException;
}
