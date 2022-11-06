package grupobala.Database;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import grupobala.Database.Connection.DBConnection;
import grupobala.Database.Goal.DBGoal;
import grupobala.Database.Goal.IDBGoal.IDBGoal;
import grupobala.Entities.Goal.Goal;
import grupobala.Entities.Goal.IGoal.IGoal;
import grupobala.SetupForTest.SetupForTest;
import java.sql.SQLException;
import java.util.Calendar;
import org.junit.jupiter.api.Test;

public class TestDBGoal {

    private IDBGoal goalDB = new DBGoal(new DBConnection());

    @Test
    public void addGoal() throws SQLException {
        SetupForTest.truncateTables();
        int financiUserID = SetupForTest.addFinanciUser();

        Calendar calendar = Calendar.getInstance();
        calendar.set(2022, 9, 21);

        IGoal newGoal =
            this.goalDB.addGoal(
                    financiUserID,
                    "Teste",
                    1000.0,
                    calendar,
                    123.0
                );

        assertNotNull(newGoal);
    }

    @Test
    public void addGoalShouldFailRepeatedGoal() throws SQLException {
        SetupForTest.truncateTables();
        int financiUserID = SetupForTest.addFinanciUser();

        Calendar calendar = Calendar.getInstance();
        calendar.set(2022, 9, 21);

        this.goalDB.addGoal(financiUserID, "Teste", 1000.0, calendar, 123.0);

        Exception exception = assertThrows(
            SQLException.class,
            () -> {
                this.goalDB.addGoal(
                        financiUserID,
                        "Teste",
                        1000.0,
                        calendar,
                        123.0
                    );
            }
        );

        String expected = "Essa meta já existe";

        assertNotNull(expected, exception.getMessage());
    }

    @Test
    public void removeGoal() throws SQLException {
        SetupForTest.truncateTables();
        int financiUserID = SetupForTest.addFinanciUser();
        IGoal goal = SetupForTest.addDefaultGoal(financiUserID);

        int updates = this.goalDB.removeGoal(financiUserID, goal.getID());
        int expected = 1;

        assertEquals(expected, updates);
    }

    @Test
    public void removeGoalShouldFailNonexistentGoalID() throws SQLException {
        SetupForTest.truncateTables();
        int financiUserID = SetupForTest.addFinanciUser();

        Exception exception = assertThrows(
            SQLException.class,
            () -> {
                this.goalDB.removeGoal(financiUserID, -1);
            }
        );

        String expected = "Meta não existe";

        assertEquals(expected, exception.getMessage());
    }

    @Test
    public void updateGoal() throws SQLException {
        SetupForTest.truncateTables();
        int financiUserID = SetupForTest.addFinanciUser();
        IGoal goal = SetupForTest.addDefaultGoal(financiUserID);

        goal.setTitle("Teste update");

        int updates = this.goalDB.updateGoal(financiUserID, goal);
        int expectedUpdates = 1;

        assertEquals(expectedUpdates, updates);
    }

    @Test
    public void updateGoalShouldFailNonexistentGoalID() throws SQLException {
        SetupForTest.truncateTables();
        int financiUserID = SetupForTest.addFinanciUser();

        Calendar calendar = Calendar.getInstance();
        calendar.set(2022, 9, 21);

        IGoal goal = new Goal(-1, "Teste", 1000, calendar.getTime(), 123);

        Exception exception = assertThrows(
            SQLException.class,
            () -> {
                this.goalDB.updateGoal(financiUserID, goal);
            }
        );

        String expected = "Meta não existe";

        assertEquals(expected, exception.getMessage());
    }
}
