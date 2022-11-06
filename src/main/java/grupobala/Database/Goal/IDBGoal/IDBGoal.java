package grupobala.Database.Goal.IDBGoal;

import java.sql.SQLException;
import java.util.Calendar;

import grupobala.Entities.Goal.IGoal.IGoal;

public interface IDBGoal {
    public IGoal addGoal(int userID, String title, double objective, Calendar expectedDate, double idealValuePerMonth) throws SQLException;
    public int removeGoal(int userID, int goalId) throws SQLException;
    public int updateGoal(int userID, IGoal goal) throws SQLException;
}
