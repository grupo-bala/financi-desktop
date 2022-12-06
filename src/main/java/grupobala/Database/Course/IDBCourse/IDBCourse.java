package grupobala.Database.Course.IDBCourse;

import java.sql.SQLException;
import java.util.ArrayList;

import grupobala.Entities.Course.ICourse.ICourse;

public interface IDBCourse {
    public ArrayList<ICourse> getAll() throws SQLException;
}
