package grupobala.Database;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import grupobala.Database.Connection.DBConnection;
import grupobala.Database.Course.DBCourse;
import grupobala.Database.Course.IDBCourse.IDBCourse;
import grupobala.Entities.Course.ICourse.ICourse;
import grupobala.SetupForTest.SetupForTest;

public class TestDBCourse {
    private IDBCourse databaseCourse = new DBCourse(
        new DBConnection()
    );

    @Test
    public void testGetAll() throws SQLException {
        SetupForTest.truncateTables();

        ArrayList<ICourse> courses = this.databaseCourse.getAll();

        assertNotEquals(0, courses.size());
    }
}
