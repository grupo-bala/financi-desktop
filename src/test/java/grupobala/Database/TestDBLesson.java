package grupobala.Database;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import grupobala.Controller.Authentication.AuthenticationController;
import grupobala.Database.Connection.DBConnection;
import grupobala.Database.Lesson.DBLesson;
import grupobala.Database.Lesson.IDBLesson.IDBLesson;
import grupobala.Entities.Lesson.ILesson.ILesson;
import grupobala.SetupForTest.SetupForTest;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Locale;
import org.junit.jupiter.api.Test;

public class TestDBLesson {

    private IDBLesson databaseLesson = new DBLesson(new DBConnection());

    @Test
    public void testGetAll() throws SQLException, Exception {
        SetupForTest.truncateTables();
        AuthenticationController authController = new AuthenticationController();
        authController.signUp("financi", "Financi@123", "Financi", 0);
        authController.signIn("financi", "Financi@123");

        ArrayList<ILesson> lessons = this.databaseLesson.getAll(1);

        assertNotEquals(0, lessons.size());
    }

    @Test
    public void testUpdateWatchedAdd() throws SQLException {
        SetupForTest.truncateTables();
        int userId = SetupForTest.addFinanciUser();

        assertDoesNotThrow(() -> {
            this.databaseLesson.updateWatched(true, 1, userId);
        });
    }

    @Test
    public void testUpdateWatchedRemove() throws SQLException {
        SetupForTest.truncateTables();
        int userId = SetupForTest.addFinanciUser();

        String query = String.format(
            Locale.US,
            "INSERT INTO aulaassistida(idusuario, idaula) VALUES (%d, %d)",
            userId,
            1
        );

        new DBConnection().executeUpdate(query);

        assertDoesNotThrow(() -> {
            this.databaseLesson.updateWatched(false, 1, userId);
        });
    }
}
